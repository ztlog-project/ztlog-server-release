package com.devlog.admin.mapper.main;

import com.devlog.admin.dto.main.request.MainStatisticsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MainDashBoardMapper {
    Optional<MainStatisticsDto> selectMainStatistics();
}
