package com.devlog.admin.mapper;

import com.devlog.admin.vo.content.ContentVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContentMapper {

    void insertContentMaster(ContentVo contentVo);

    void insertContentDetail(ContentVo contentVo);

    void updateContentMaster(ContentVo contentVo);

    void updateContentDetail(ContentVo contentVo);

    void deleteContentMaster(Long ctntNo);

    void deleteContentDetail(Long ctntNo);

}
