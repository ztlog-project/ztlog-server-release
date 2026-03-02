package com.devlog.admin.mapper.stats;

import com.devlog.admin.dto.stats.request.MainStatisticsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MainDashBoardMapper {
    Optional<MainStatisticsDto> selectMainStatistics();
}
