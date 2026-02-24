package com.devlog.admin.mapper.content;

import com.devlog.admin.dto.content.response.ContentResDto;
import com.devlog.core.common.enumulation.SearchType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ContentStatisticsMapper {
    List<ContentResDto> selectContentList(RowBounds rowBounds);

    Optional<ContentResDto> selectContent(Long ctntNo);

    List<ContentResDto> selectSearchContentList(SearchType type, String param, RowBounds rowBounds);
}
