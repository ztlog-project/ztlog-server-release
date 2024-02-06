package com.devlog.admin.mapper;

import com.devlog.admin.vo.content.ContentSearchVo;
import com.devlog.admin.vo.content.ContentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper {

    List<ContentVo> selectContentList(ContentSearchVo searchVo);

    ContentVo selectContentByCtntNo(Long ctntNo);

    void insertContentMaster(ContentVo contentVo);

    void insertContentDetail(ContentVo contentVo);

    void updateContentMaster(ContentVo contentVo);

    void updateContentDetail(ContentVo contentVo);

    void deleteContentMaster(Long ctntNo);

    void deleteContentDetail(Long ctntNo);

}
