package com.devlog.admin.dto.stats.response;

import com.google.api.services.searchconsole.v1.model.ApiDataRow;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class DailyStatsDto {

    private String pageUrl;     // 페이지 주소 (Dimensions: page)
    private Long clicks;        // 클릭수
    private Long impressions;   // 노출수
    private Double ctr;         // 클릭률 (CTR)
    private Double position;    // 평균 순위

    public static DailyStatsDto of(ApiDataRow row) {
        return DailyStatsDto.builder()
                .pageUrl(row.getKeys().isEmpty() ? "unknown" : row.getKeys().get(0))
                .clicks(row.getClicks().longValue())
                .impressions(row.getImpressions().longValue())
                .ctr(row.getCtr())
                .position(row.getPosition())
                .build();


    }
}
