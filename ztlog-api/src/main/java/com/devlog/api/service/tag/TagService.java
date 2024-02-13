package com.devlog.api.service.tag;

import com.devlog.api.service.content.dto.ContentListResDto;
import com.devlog.api.service.tag.dto.TagResDto;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.util.PageUtils;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.entity.content.Content;
import com.devlog.core.entity.content.ContentTag;
import com.devlog.core.entity.tag.Tag;
import com.devlog.core.repository.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    /**
     * 태그 목록 조회하기
     *
     * @return 태그 리스트
     */
    public List<TagResDto> getTagList() {
        return tagRepository.findAll().stream()
                .map(TagResDto::of)
                .collect(Collectors.toList());
    }

    /**
     * 태그 게시물 목록 조회하기
     *
     * @param tagNo 태그 번호
     * @param page  페이지 번호 (기본값 = 1)
     * @return 태그 게시물 리스트
     */
    public ContentListResDto getTagContentList(Integer tagNo, Integer page) {
        Tag tag = tagRepository.findById(Long.valueOf(tagNo))
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage()));

        List<Content> contents = tag.getContentTags().stream()
                .map(ContentTag::getContents)
                .collect(Collectors.toList());

        int start = PageUtils.getStartIdx(page);
        int end = Math.min(PageUtils.getEndIdx(page, start), contents.size());

        return ContentListResDto.of(contents.subList(start, end));
    }
}
