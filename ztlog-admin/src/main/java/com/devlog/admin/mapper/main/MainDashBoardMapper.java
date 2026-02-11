package com.devlog.admin.mapper.main;

import com.devlog.admin.dto.main.request.MainDashBoardDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainDashBoardMapper {
    MainDashBoardDto selectMainStatisticInfo();
}
