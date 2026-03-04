package com.devlog.admin.service.stats;

import com.devlog.admin.dto.stats.response.GiscusResponse;
import com.devlog.admin.mapper.stats.ReplyStatsMapper;
import com.devlog.core.common.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReplyStatsSummaryService {

    private final WebClient webClient; // Bean 등록 권장
    private final ReplyStatsMapper replyStatsMapper;


//    @Value("${github.token}")
    private String githubToken;

    // 정규식 패턴 미리 컴파일 (성능 최적화)
    private static final Pattern POST_ID_PATTERN = Pattern.compile("/contents/(\\d+)");

    private final DateUtils dateUtils;

    /**
     * 전체 컨텐츠의 댓글수를 외부 서비스와 동기화하여 집계
     */
//    @Scheduled(cron = "0 0 2 * * *")
//    public void summarizeReplyStats() {
//        // 1. 동기화가 필요한 컨텐츠 리스트 조회 (필요 시)
//        // 2. 외부 API를 통해 각 컨텐츠의 최신 댓글 수 획득
//        // 3. DB 업데이트
//
//        // 예시: 특정 로직에 의해 추출된 ctntNo 리스트를 순회
//        for (Long ctntNo : targetCtntNos) {
//            int currentReplyCount = replyApiClient.getReplyCount(ctntNo);
//            replyStatsMapper.upsertReplyCount(ctntNo, currentReplyCount);
//        }
//    }

//    /**
//     * 특정 컨텐츠의 댓글수만 실시간 동기화 (Webhook 또는 수동 호출용)
//     */
//    @Transactional
//    public void summarizeReplyStatsByContent(Long ctntNo, int replyCnt) {
//        replyStatsMapper.upsertReplyCount(ctntNo, replyCnt);
//    }

    // @Scheduled(cron = "0 0 2 * * *")
    public void saveReplyStatsSummaryByContent(Long ctntNo, int replyCnt) {
        replyStatsMapper.upsertReplyCount(ctntNo, replyCnt);

    }

    public void syncCommentCount(GiscusResponse response) {
        // 1. GitHub GraphQL API 호출 (앞서 테스트한 JSON Body 전송)
        // 2. response 받아오기
        // 3. batchService.syncCommentCounts(response);
        List<GiscusResponse.Node> nodes = response.getData().getRepository().getDiscussions().getNodes();

        for (GiscusResponse.Node node : nodes) {
            String title = node.getTitle(); // 예: "http://ztlog.io/contents/10"
            int count = node.getComments().getTotalCount();

            // 정규식: /contents/ 뒤의 숫자(\d+)를 캡처
            Pattern pattern = Pattern.compile("/contents/(\\d+)");
            Matcher matcher = pattern.matcher(title);

            if (matcher.find()) {
                Long postId = Long.parseLong(matcher.group(1)); // 첫 번째 괄호 안의 숫자(10) 추출

                // MyBatis 업데이트 호출
                replyStatsMapper.updateCommentCount(postId, count);
            }
        }
    }



    @Transactional
    public void syncAllComments() {
        log.info(">>> Giscus 댓글 동기화 배치 시작");

        try {
            // 1. GitHub GraphQL API 호출
            String query = "{ \"query\": \"query { repository(owner: \\\"ztlog-project\\\", name: \\\"ztlog-comment\\\") { discussions(first: 100) { nodes { title comments { totalCount } } } } }\" }";

            GiscusResponse response = webClient.post()
                    .uri("https://api.github.com")
                    .header(HttpHeaders.AUTHORIZATION, "bearer " + githubToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(query)
                    .retrieve()
                    .bodyToMono(GiscusResponse.class)
                    .block(); // 배치이므로 동기 처리

            if (response == null || response.getData() == null) {
                log.warn("API 응답 데이터가 비어있습니다.");
                return;
            }

            // 2. 데이터 파싱 및 DB 업데이트
            List<GiscusResponse.Node> nodes = response.getData().getRepository().getDiscussions().getNodes();
            int updateCount = 0;

            for (GiscusResponse.Node node : nodes) {
                Matcher matcher = POST_ID_PATTERN.matcher(node.getTitle());

                if (matcher.find()) {
                    Long postId = Long.parseLong(matcher.group(1));
                    int commentCount = node.getComments().getTotalCount();

                    // 3. MyBatis 업데이트 실행
                    replyStatsMapper.updateCommentCount(postId, commentCount);
                    updateCount++;
                }
            }

            log.info(">>> 동기화 완료: {}개의 포스트 업데이트됨", updateCount);

        } catch (Exception e) {
            log.error(">>> 배치 작업 중 오류 발생: {}", e.getMessage(), e);
            throw e; // 트랜잭션 롤백 필요 시
        }
    }
}
