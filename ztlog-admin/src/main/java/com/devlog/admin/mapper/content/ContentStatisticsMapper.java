package com.devlog.admin.mapper.content;

import com.devlog.admin.dto.content.response.ContentResDto;
import com.devlog.core.common.enumulation.SearchType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ContentStatisticsMapper {
    Integer selectCountContentList();

    List<ContentResDto> selectContentList(RowBounds rowBounds);

    Integer selectCountSearchContentList(SearchType type, String param);

    List<ContentResDto> selectSearchContentList(@Param("type") SearchType type, @Param("param") String param, RowBounds rowBounds);

    Optional<ContentResDto> selectContent(Long ctntNo);
}
