package com.devlog.admin.mapper;

import com.devlog.admin.vo.tags.TagsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagsMapper {

    TagsVo selectTagsByNo(Long tagsNo);

    TagsVo selectTagsByName(String tagName);

    List<TagsVo> selectTagsList();

    void insertTagsMaster(TagsVo tagsVo);

    void updateTagsMaster(TagsVo tagsVo);

    void deleteContentTags(Long ctntNo);

    void deleteTagsMaster(Long tagNo);

}
