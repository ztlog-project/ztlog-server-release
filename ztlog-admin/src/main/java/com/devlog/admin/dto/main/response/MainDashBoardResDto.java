package com.devlog.admin.dto.main.response;

import com.devlog.admin.dto.main.request.MainStatisticsDto;
import lombok.*;

@Getter
@NoArgsConstructor // 추가
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class MainDashBoardResDto {

    private long totalPostCount;
    private long totalTagCount;
    private long totalViewCount;
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
