package com.devlog.admin.mapper.content;

import com.devlog.admin.dto.content.response.ContentResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentStatisticsMapper {
    List<ContentResDto> selectContentList();
}
