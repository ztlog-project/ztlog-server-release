package com.devlog.admin.component;

import com.devlog.admin.dto.stats.response.GiscusDataResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class GiscusComponent {

    private final WebClient webClient;

    @Value("${github.url}")
    private String githubUrl;

    @Value("${github.token}")
    private String githubToken;

    @Value("${github.user.name}")
    private String userName;

    @Value("${github.user.comment-repo}")
    private String commentRepo;

    /**
     * 특정 포스트(Discussion)의 실시간 댓글 총합 get
     */
    public GiscusDataResDto.Node fetchCommentCount(Long pagePath) {
        // 1. GraphQL 쿼리 본문 작성 (Discussion 번호 기반), pagePath가 Discussion 번호(ID) 역할을 한다고 가정
        String query = String.format(
                "{ \"query\": \"query { repository(owner: \\\"%s\\\", name: \\\"%s\\\") { discussion(number: %d) { comments { totalCount } } } }\" }",
                userName, commentRepo, pagePath
        );

        // 2. WebClient로 POST 요청 전송 및 응답 block 처리 (동기식 get)
        Optional<GiscusDataResDto> response =  webClient.post()
                .uri(githubUrl)
                .header(HttpHeaders.AUTHORIZATION, "bearer " + githubToken)
                .bodyValue(query)
                .retrieve()
                .bodyToMono(GiscusDataResDto.class)
                .blockOptional();

        return response.map(giscusResDto -> giscusResDto.getNodes().get(0)).orElse(null);
    }

    public List<GiscusDataResDto.Node> fetchAllCommentCount() {
        String query = String.format("{ \"query\": \"query { repository(owner: \\\"%s\\\", name: \\\"%s\\\") { discussions(first: 100) { nodes { title comments { totalCount } } } } }\" }",
                userName, commentRepo);

        return webClient.post()
                .uri(githubUrl)
                .header(HttpHeaders.AUTHORIZATION, "bearer " + githubToken)
                .bodyValue(query)
                .retrieve()
                .bodyToMono(GiscusDataResDto.class)
                .blockOptional()
                .map(GiscusDataResDto::getNodes) // DTO에 작성하신 getNodes() 호출
                .orElse(Collections.emptyList());
    }

}
