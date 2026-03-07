package com.devlog.admin.service.stats;

import com.devlog.admin.dto.stats.request.CommentStatsReqDto;
import com.devlog.admin.dto.stats.response.GiscusResDto;
import com.devlog.admin.mapper.stats.CommentStatsMapper;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.common.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentStatsService {

    private final WebClient webClient; // Bean 등록 권장
    private final CommentStatsMapper replyStatsMapper;
    private final DateUtils dateUtils;

    @Value("${github.url}")
    private String githubUrl;

    @Value("${github.token}")
    private String githubToken;

    @Value("${github.user.name}")
    private String userName;

    @Value("${github.user.comment-repo}")
    private String commentRepo;


    public void syncCommentStats(CommentStatsReqDto reqDto) {
        replyStatsMapper.updateCommentCount(reqDto.getCtntNo(), reqDto.getCommentCnt());

    }

    // @Scheduled(cron = "0 0 2 * * *")
    public void syncAllComments() {
        log.info(">>> Giscus 댓글 동기화 배치 시작");
        List<GiscusResDto.Node> nodes = fetchGiscusNodes();

        // 1. 스트림 안에서 변환과 필터링을 한꺼번에 처리 (가장 권장되는 방식)
        List<CommentStatsReqDto> updateList = nodes.stream()
                .map(this::mapToReplyStatsDto)
                .flatMap(Optional::stream) // ID 추출 성공한 것만 통과
                .toList();

        // 2. DB 업데이트
        updateList.forEach(dto -> replyStatsMapper.upsertReplyCount(dto.getCtntNo(), dto.getReplyCnt()));

        log.info(">>> 총 {}건 동기화 완료", updateList.size());
    }

    // [중요] 누락되었던 데이터 호출 메서드
    private List<GiscusResDto.Node> fetchGiscusNodes() {
        String query = String.format("{ \"query\": \"query { repository(owner: \\\"%s\\\", name: \\\"%s\\\") { discussions(first: 100) { nodes { title comments { totalCount } } } } }\" }",
                userName, commentRepo);

        return webClient.post()
                .uri(githubUrl)
                .header(HttpHeaders.AUTHORIZATION, "bearer " + githubToken)
                .bodyValue(query)
                .retrieve()
                .bodyToMono(GiscusResDto.class)
                .blockOptional()
                .map(GiscusResDto::getNodes) // DTO에 작성하신 getNodes() 호출
                .orElse(Collections.emptyList());
    }

    private Optional<CommentStatsReqDto> mapToReplyStatsDto(GiscusResDto.Node node) {
        // 추출 로직은 서비스 내부 private 메서드로 관리
        Matcher matcher = CommonConstants.POST_ID_PATTERN.matcher(node.getTitle());
        if (matcher.find()) {
            return Optional.of(CommentStatsReqDto.of(
                    Long.parseLong(matcher.group(1)),
                    node.getComments().getTotalCount()
            ));
        }
        return Optional.empty();
    }

    public CommentStatsReqDto getRealTimeCommentStats(Long ctntNo) {
        return null;
    }
}
