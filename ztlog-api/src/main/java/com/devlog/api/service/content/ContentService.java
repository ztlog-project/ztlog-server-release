package com.devlog.api.service.content;

import com.devlog.api.service.content.dto.ContentResDto;
import com.devlog.api.service.content.dto.ContentListResDto;
import com.devlog.api.service.tag.dto.TagResDto;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.util.PageUtils;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.entity.content.Content;
import com.devlog.core.entity.content.ContentTag;
import com.devlog.core.repository.content.ContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    /**
     * 컨텐츠 목록 조회하기
     *
     * @param page 페이지 번호 (기본값 = 1)
     * @return 컨텐츠 리스트 반환
     */
    public ContentListResDto getContentsList(int page) {
        List<ContentListResDto.ContentMainDto> list = new ArrayList<>();
        // select content list
        Page<Content> contentPage = contentRepository.findAll(PageUtils.getPageable(page));
        // setting content entity -> dto list
        return getContentListToResDto(list, contentPage);
    }

    /**
     * 컨텐츠 상세 조회하기
     *
     * @param ctntNo 컨텐츠 번호
     * @return 컨텐츠 반환
     */
    public ContentResDto getContentsDetail(Integer ctntNo) {
        Content content = contentRepository.findById(Long.valueOf(ctntNo))
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage()));

        final var dto = ContentResDto.builder().build();
        BeanUtils.copyProperties(content, dto);

        // setting content tags
        List<TagResDto> tags = content.getContentTags()
                .stream().map(contentTags -> TagResDto.builder()
                        .tagNo(contentTags.getTags().getTagNo())
                        .tagName(contentTags.getTags().getTagName())
                        .build()
                ).collect(Collectors.toList());
        dto.setTags(tags);

        return dto;
    }

    /**
     * 컨텐츠 검색하기
     *
     * @param param 검색 키워드
     * @param page  페이지 번호 (기본값 = 1)
     * @return 검색한 키워드 관련 리스트 반환
     */
    public ContentListResDto searchContentList(String param, int page) {
        List<ContentListResDto.ContentMainDto> list = new ArrayList<>();
        // select content list
        Page<Content> contentPage = contentRepository.findAllByCtntTitleContaining(param, PageUtils.getPageable(page));
        // setting content entity -> dto list
        return getContentListToResDto(list, contentPage);
    }

    @NotNull
    private ContentListResDto getContentListToResDto(List<ContentListResDto.ContentMainDto> list, Page<Content> contentEntityPage) {
        // extract method : get lst
        for (Content content : contentEntityPage.getContent()) {
            final var dto = ContentListResDto.ContentMainDto.builder().build();
            BeanUtils.copyProperties(content, dto);

            List<TagResDto> tags = new ArrayList<>();
            for (ContentTag contentTag : content.getContentTags()) {
                TagResDto build = TagResDto.builder()
                        .tagNo(contentTag.getTags().getTagNo())
                        .tagName(contentTag.getTags().getTagName())
                        .build();
                tags.add(build);
            }
            dto.setTags(tags);
            list.add(dto);
        }

        return new ContentListResDto(list, Long.valueOf(contentEntityPage.getTotalElements()).intValue());
    }

}
