package com.devlog.admin.dto.stats.response;

import com.google.api.services.searchconsole.v1.model.ApiDataRow;
import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class DailyStatsResDto {

    private LocalDate statDt;   // 통계 날짜 (STAT_DT)
    private Long ctntNo;        // 컨텐츠 번호 (CTNT_NO)
    private String pageUrl;     // 페이지 주소 (Dimensions: page)
    private Long viewCnt;       // 조회수 (VIEW_CNT)
    private Long impressions;   // 노출수
    private Double ctr;         // 클릭률 (CTR)
    private Double position;    // 평균 순위

    public static DailyStatsResDto of(ApiDataRow row, LocalDate statDt, String pageUrl, Long ctntNo) {
        return DailyStatsResDto.builder()
                .statDt(statDt)
                .ctntNo(ctntNo)
                .pageUrl(pageUrl)
                .viewCnt(row.getClicks().longValue())
                .impressions(row.getImpressions().longValue())
                .ctr(row.getCtr())
                .position(row.getPosition())
                .build();
    }
}
