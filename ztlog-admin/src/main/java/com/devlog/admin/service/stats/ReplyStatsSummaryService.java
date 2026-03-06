package com.devlog.admin.service.stats;

import com.devlog.admin.dto.stats.response.GiscusResponse;
import com.devlog.admin.dto.stats.response.ReplyStatsResDto;
import com.devlog.admin.mapper.stats.ReplyStatsMapper;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.common.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    private final DateUtils dateUtils;

    @Value("${github.url}")
    private String githubUrl;

    @Value("${github.token}")
    private String githubToken;

    @Value("${github.user.name}")
    private String userName;

    @Value("${github.user.comment-repo}")
    private String commentRepo;


    public void saveReplyStatsSummaryByContent(Long ctntNo, int replyCnt) {
        replyStatsMapper.updateReplyCount(ctntNo, replyCnt);

    }

    // @Scheduled(cron = "0 0 2 * * *")
    @Transactional
    public void syncAllComments() {
        log.info(">>> Giscus 댓글 동기화 배치 시작");


        String query = String.format("{ \"query\": \"query { repository(owner: \\\"%s\\\", name: \\\"%s\\\") { discussions(first: 100) { nodes { title comments { totalCount } } } } }\" }", userName, commentRepo);

        webClient.post()
                .uri(githubUrl)
                .header(HttpHeaders.AUTHORIZATION, "bearer " + githubToken)
                .bodyValue(query)
                .retrieve()
                .bodyToMono(GiscusResponse.class)
                .blockOptional()
                .map(res -> res.getData().getRepository().getDiscussions().getNodes())
                .ifPresent(nodes -> nodes.forEach(node -> {
                    Matcher matcher = CommonConstants.POST_ID_PATTERN.matcher(node.getTitle());
                    if (matcher.find()) {
                        Long ctntNo = Long.parseLong(matcher.group(1));
                        int replyCnt = node.getComments().getTotalCount();

                        // 존재하면 UPDATE, 없으면 INSERT (한 번의 DB 호출)
                        replyStatsMapper.upsertReplyCount(ctntNo, replyCnt);
                        ReplyStatsResDto dto = ReplyStatsResDto.of(ctntNo, replyCnt);
                        log.info("response :: ctntNo: {} - coutn: {}", dto.getCtntNo(), dto.getReplyCnt());
                    }
                }));



//        GiscusResponse response = webClient.post()
//                .uri(githubUrl)
//                .header(HttpHeaders.AUTHORIZATION, "bearer " + githubToken)
//                .bodyValue(query)
//                .retrieve()
//                .bodyToMono(GiscusResponse.class)
//                .block();
//        if (response == null || response.getData() == null) return;
//
//        response.getData().getRepository().getDiscussions().getNodes().stream()
//                .map(node -> new Object[]{CommonConstants.POST_ID_PATTERN.matcher(node.getTitle()), node.getComments().getTotalCount()})
//                .filter(arr -> ((Matcher) arr[0]).find())
//                .forEach(arr -> {
//                    Long ctntNo = Long.parseLong(((Matcher) arr[0]).group(1));
//                    int replyCnt = (int) arr[1];
//                    // 단일 upsert 쿼리로 호출 (기존 exist 확인 절차 삭제)
//                    replyStatsMapper.upsertReplyCount(ctntNo, replyCnt);
//
//                    ReplyStatsResDto dto = ReplyStatsResDto.of(ctntNo, replyCnt);
//
//                    log.info("response : {}", dto.toString());
//                });

        log.info(">>> 동기화 완료");
    }
}
