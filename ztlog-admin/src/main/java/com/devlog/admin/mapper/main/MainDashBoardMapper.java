package com.devlog.admin.mapper.main;

import com.devlog.admin.dto.main.request.MainStatisticsDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainDashBoardMapper {
    MainStatisticsDto selectMainStatistics();
}
