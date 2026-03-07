package com.devlog.admin.mapper.stats;

import com.devlog.admin.dto.stats.response.DailyStatsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface ViewStatsMapper {

    // 1. 전날 로그 집계
    List<Map<String, Object>> selectDailyViewCounts(@Param("date") LocalDate date);

    // 2. 일별 통계 반영 (ON DUPLICATE KEY UPDATE)
    void upsertDailyViewStats(DailyStatsDto dailyStatsDto);

    void upsertTotalViewStats(@Param("ctntNo") Long ctntNo, @Param("viewCnt") Long viewCnt);

    void updateCumulativeViewStats(@Param("ctntNo") Long ctntNo, @Param("viewCnt") int viewCnt);

    Long getTotalViewCountFromLog(Long ctntNo);
}
