package com.devlog.admin.service.tags;

import com.devlog.admin.dto.tag.request.TagsInfoReqDto;
import com.devlog.admin.dto.tag.response.TagsInfoResDto;
import com.devlog.admin.mapper.TagsMapper;
import com.devlog.admin.vo.tags.TagsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TagsService {

    private final TagsMapper tagsMapper;

    /**
     * 태그 목록 조회
     *
     * @return 태그 리스트
     */
    public List<TagsInfoResDto> getTagsList() {
        List<TagsInfoResDto> list = new ArrayList<>();

        this.tagsMapper.selectTagsList().forEach(vo -> {
            TagsInfoResDto resDto = TagsInfoResDto.builder().build();
            BeanUtils.copyProperties(resDto, vo);
            list.add(resDto);
        });

        return list;
    }

    public void createTagsInfo(TagsInfoReqDto reqDto) {

    }

    public void updateTagsInfo(TagsInfoReqDto reqDto) {

    }

    public void deleteTagsInfo(Long tagsNo) {

    }

}
