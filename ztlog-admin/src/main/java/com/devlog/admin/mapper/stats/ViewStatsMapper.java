package com.devlog.admin.mapper.stats;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface ViewStatsMapper {

    // 1. 전날 로그 집계
    List<Map<String, Object>> selectDailyViewCounts(@Param("targetDate") LocalDate targetDate);

    // 2. 일별 통계 반영 (ON DUPLICATE KEY UPDATE)
    void upsertDailyViewStats(@Param("targetDate") LocalDate targetDate, @Param("ctntNo") Long ctntNo, @Param("viewCnt") int viewCnt);

    // 3. 누적 조회수 합산
    void updateCumulativeViewStats(@Param("ctntNo") Long ctntNo, @Param("viewCnt") int viewCnt);




}
