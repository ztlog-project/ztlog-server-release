package com.devlog.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagsMapper {

    void deleteContentTags(Long ctntNo);

}
