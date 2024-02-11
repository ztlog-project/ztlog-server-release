package com.devlog.api.service.tag;

import com.devlog.api.service.content.dto.ContentListResDto;
import com.devlog.api.service.tag.dto.TagListResDto;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.domain.entity.content.ContentEntity;
import com.devlog.core.domain.repository.content.ContentTagRepository;
import com.devlog.core.domain.repository.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    private final ContentTagRepository contentTagRepository;

    /**
     * 태그 목록 조회하기
     *
     * @return 태그 리스트
     */
    public List<TagListResDto> getTagsList() {
        List<TagListResDto> list = new ArrayList<>();

        while (this.tagRepository.findAll().iterator().hasNext()) {
            final var dto = TagListResDto.builder().build();
            BeanUtils.copyProperties(this.tagRepository.findAll().iterator().next(), dto);
            list.add(dto);
        }
        return list;
    }

    /**
     * 태그 게시물 목록 모회하기
     *
     * @param tagNo 태그 번호
     * @param page  페이지 번호 (기본값 = 1)
     * @return 태그 게시물 리스트
     */
    public ContentListResDto getTagsSearchList(Integer tagNo, Integer page) {
        Pageable pageable = PageRequest.of(page, CommonConstants.PAGE_SIZE);
        List<ContentListResDto.ContentMainDto> list = new ArrayList<>();

        List<ContentEntity> entityList = this.contentTagRepository.findAllByTagNo(Long.valueOf(tagNo));

        for (ContentEntity entity : entityList) {
            final var dto = ContentListResDto.ContentMainDto.builder().build();
            BeanUtils.copyProperties(entity, dto);
            dto.setTags(this.contentTagRepository.findTagNameListByCtntNo(entity.getCtntNo()));
            list.add(dto);
        }

        return new ContentListResDto(list, (long) list.size());
    }
}
