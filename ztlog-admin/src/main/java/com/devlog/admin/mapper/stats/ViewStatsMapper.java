package com.devlog.admin.mapper.stats;

import com.devlog.admin.dto.stats.response.DailyStatsDto;
import com.devlog.admin.dto.stats.request.ViewRawDataReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface ViewStatsMapper {

//    List<Map<String, Object>> selectDailyViewCounts(@Param("date") LocalDate date);

    void upsertDailyViewStats(DailyStatsDto dailyStatsDto);

    void updateCumulativeViewStats(@Param("ctntNo") Long ctntNo, @Param("viewCnt") long viewCnt);

    void insertViewRawLogs(@Param("list") List<ViewRawDataReqDto> list);
}
