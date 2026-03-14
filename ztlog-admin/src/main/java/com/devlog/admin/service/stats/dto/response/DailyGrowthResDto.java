package com.devlog.admin.service.stats.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class DailyGrowthResDto {

    private LocalDate statDate;     // 통계 날짜
    private long totalViewCnt;      // 해당 날짜 전체 조회수 합계
    private Long dailyGrowth;       // 전일 대비 조회수 증가량 (첫 날은 null)
}
