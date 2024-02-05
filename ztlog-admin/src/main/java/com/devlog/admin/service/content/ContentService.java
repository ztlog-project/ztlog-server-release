package com.devlog.admin.service.content;

import com.devlog.admin.dto.content.request.ContentInfoReqDto;
import com.devlog.admin.mapper.ContentMapper;
import com.devlog.admin.mapper.TagsMapper;
import com.devlog.core.config.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentService {

    private final ContentMapper contentMapper;

    private final TagsMapper tagMapper;

    // TODO :

    public Object getContentsList(Integer page) {
        return null;
    }

    public Object getContentInfo(Integer ctntNo) throws DataNotFoundException  {
//        ContentVo contentVo = this.contentMapper.select
        return null;
    }


    public void createContentInfo(ContentInfoReqDto reqVo) {

    }

    public void updateContentInfo(ContentInfoReqDto reqVo) throws DataNotFoundException {

    }

    public void deleteContentInfo(Long ctntNo) throws DataNotFoundException {
        // check content exist

        // contents delete
        this.contentMapper.deleteContentMaster(ctntNo);
        this.contentMapper.deleteContentDetail(ctntNo);

        // tag delete
        this.tagMapper.deleteContentTags(ctntNo);
    }
}
