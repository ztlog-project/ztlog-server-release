package com.devlog.admin.mapper.tag;

import com.devlog.admin.dto.tag.response.TagCountResDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface TagMapper {
    List<TagCountResDto> selectTagList(RowBounds rowBounds);
}
