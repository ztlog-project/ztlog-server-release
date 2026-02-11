package com.devlog.admin.mapper.main;

import com.devlog.admin.service.main.dto.MainDashBoardDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MainDashBoardMapper {
    MainDashBoardDto getMainStatusInfo();
}
