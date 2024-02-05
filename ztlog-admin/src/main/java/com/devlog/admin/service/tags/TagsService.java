package com.devlog.admin.service.tags;

import com.devlog.admin.dto.tag.request.TagsInfoReqDto;
import com.devlog.admin.mapper.TagsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagsService {

    private final TagsMapper tagsMapper;

    public Object getTagsList() {
        return null;
    }

    public void createTagsInfo(TagsInfoReqDto reqDto) {

    }

    public void updateTagsInfo(TagsInfoReqDto reqDto) {

    }

    public void deleteTagsInfo(Long tagsNo) {

    }

}
