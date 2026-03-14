package com.devlog.admin.mapper.stats;

import com.devlog.admin.service.stats.dto.response.DailyGrowthResDto;
import com.devlog.admin.service.stats.dto.response.DailyStatsResDto;
import com.devlog.admin.service.stats.dto.request.ViewRawDataReqDto;
import com.devlog.admin.service.stats.dto.response.ViewRankingResDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ViewStatsMapper {

    void upsertDailyViewStats(DailyStatsResDto dailyStatsResDto);

    void updateCumulativeViewStats(@Param("ctntNo") Long ctntNo, @Param("viewCnt") long viewCnt);

    void insertViewRawLogs(@Param("list") List<ViewRawDataReqDto> list);

    List<DailyGrowthResDto> selectDailyGrowthStats(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<ViewRankingResDto> selectViewRankingStats();
}
