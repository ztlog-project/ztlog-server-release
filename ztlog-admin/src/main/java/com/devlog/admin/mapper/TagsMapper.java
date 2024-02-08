package com.devlog.admin.mapper;

import com.devlog.admin.vo.tags.TagsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagsMapper {

    List<TagsVo> selectTagsList();

    void deleteContentTags(Long ctntNo);

}
