package com.devlog.admin.dto.main.response;

import com.devlog.admin.dto.main.request.MainStatisticsDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor // 추가
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class MainDashBoardResDto {

    @Schema(description = "총 게시물 갯수")
    private long totalPostCount;

    @Schema(description = "총 태그 갯수")
    private long totalTagCount;

    @Schema(description = "총 조회수")
    private long totalViewCount;

    @Schema(description = "총 댓글 수")
    private long totalCommentCount;

    public static MainDashBoardResDto of(MainStatisticsDto statistics) {
        return MainDashBoardResDto.builder()
                .totalPostCount(statistics.getTotalPostCount())
                .totalTagCount(statistics.getTotalTagCount())
                .totalViewCount(statistics.getTotalViewCount())
                .totalCommentCount(statistics.getTotalCommentCount())
                .build();
    }

}
