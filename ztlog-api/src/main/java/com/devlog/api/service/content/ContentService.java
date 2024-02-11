package com.devlog.api.service.content;

import com.devlog.api.service.content.dto.ContentResDto;
import com.devlog.api.service.content.dto.ContentListResDto;
import com.devlog.api.service.tag.dto.TagInfoResDto;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.util.PageUtils;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.domain.entity.content.ContentEntity;
import com.devlog.core.domain.repository.content.ContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        Page<ContentEntity> contentEntities = contentRepository.findAll(PageUtils.getPageable(page));

        // setting content entity -> dto list
        contentEntities.getContent().forEach(content -> {
            final var dto = ContentListResDto.ContentMainDto.builder().build();
            BeanUtils.copyProperties(content, dto);

            List<TagInfoResDto> tags = content.getContentTags().stream().map(contentTags -> TagInfoResDto.builder()
                    .tagNo(contentTags.getTags().getTagNo())
                    .tagName(contentTags.getTags().getTagName())
                    .build()).collect(Collectors.toList());
            dto.setTags(tags);
            list.add(dto);
        });

        return new ContentListResDto(list, Long.valueOf(contentEntities.getTotalElements()).intValue());
    }

    /**
     * 컨텐츠 상세 조회하기
     *
     * @param ctntNo 컨텐츠 번호
     * @return 컨텐츠 반환
     */
    public ContentResDto getContentInfo(Integer ctntNo) {
        ContentEntity content = contentRepository.findById(Long.valueOf(ctntNo))
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage()));

        final var dto = ContentResDto.builder().build();
        BeanUtils.copyProperties(content, dto);

        // setting content tags
        List<TagInfoResDto> tags = content.getContentTags().stream().map(contentTags -> TagInfoResDto.builder()
                .tagNo(contentTags.getTags().getTagNo())
                .tagName(contentTags.getTags().getTagName())
                .build()).collect(Collectors.toList());
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
    public ContentListResDto searchContentsInfo(String param, int page) {
        List<ContentListResDto.ContentMainDto> list = new ArrayList<>();

        // select content list
        Page<ContentEntity> contentEntities = contentRepository.findAllByCtntTitleContaining(param, PageUtils.getPageable(page));

        // setting content entity -> dto list
        contentEntities.getContent().forEach(content -> {
            final var dto = ContentListResDto.ContentMainDto.builder().build();
            BeanUtils.copyProperties(content, dto);

            List<TagInfoResDto> tags = content.getContentTags().stream().map(contentTags -> TagInfoResDto.builder()
                    .tagNo(contentTags.getTags().getTagNo())
                    .tagName(contentTags.getTags().getTagName())
                    .build()).collect(Collectors.toList());
            dto.setTags(tags);
            list.add(dto);
        });

        return new ContentListResDto(list, Long.valueOf(contentEntities.getTotalElements()).intValue());
    }

}
