package com.devlog.admin.mapper.stats;

import com.devlog.admin.service.stats.dto.request.MainStatsReqsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MainStatsMapper {
    Optional<MainStatsReqsDto> selectMainStatistics();
}
