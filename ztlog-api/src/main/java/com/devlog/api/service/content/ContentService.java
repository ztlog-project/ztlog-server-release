package com.devlog.api.service.content;

import com.devlog.api.service.content.dto.ContentInfoResDto;
import com.devlog.api.service.content.dto.ContentListResDto;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.domain.entity.content.ContentDtlEntity;
import com.devlog.core.domain.entity.content.ContentEntity;
import com.devlog.core.domain.repository.content.ContentDtlRepository;
import com.devlog.core.domain.repository.content.ContentRepository;
import com.devlog.core.domain.repository.content.ContentTagRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    private final ContentDtlRepository contentDtlRepository;

    private final ContentTagRepository contentTagRepository;

    /**
     * 컨텐츠 목록 조회하기
     *
     * @param page 페이지 번호 (기본값 = 1)
     * @return 컨텐츠 리스트 반환
     */
    public ContentListResDto getContentsList(int page) {
        Pageable pageable = PageRequest.of(page, CommonConstants.PAGE_SIZE);
        List<ContentListResDto.ContentMainDto> list = new ArrayList<>();

        //
        Page<ContentEntity> contentEntityPage = this.contentRepository.findAll(pageable);

        for (ContentEntity entity : contentEntityPage.stream().toList()) {
            final var dto = ContentListResDto.ContentMainDto.builder().build();
            BeanUtils.copyProperties(entity, dto);
            dto.setTags(this.contentTagRepository.findTagNameListByCtntNo(entity.getCtntNo()));

            list.add(dto);
        }

        return new ContentListResDto(list, contentEntityPage.getTotalElements());
    }

    /**
     * 컨텐츠 상세 조회하기
     *
     * @param ctntNo 컨텐츠 번호
     * @return 컨텐츠 반환
     */
    public ContentInfoResDto getContentInfo(Integer ctntNo) {
        ContentDtlEntity entity = this.contentDtlRepository.findById(Long.valueOf(ctntNo))
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage()));

        final var dto = ContentInfoResDto.builder().build();
        BeanUtils.copyProperties(entity, dto);
        dto.setTags(this.contentTagRepository.findTagNameListByCtntNo(entity.getCtntNo()));

        return dto;
    }

    /**
     * 컨텐츠 검색하기
     *
     * @param param 검색 키워드
     * @param page 페이지 번호 (기본값 = 1)
     * @return 검색한 키워드 관련 리스트 반환
     */
    public ContentListResDto searchContentsInfo(String param, int page) {
        Pageable pageable = PageRequest.of(page, CommonConstants.PAGE_SIZE);
        List<ContentListResDto.ContentMainDto> list = new ArrayList<>();

        Page<ContentEntity> contentEntityPage = this.contentRepository.findAllByCtntTitleContaining(param, pageable);

        for (ContentEntity entity : contentEntityPage.stream().toList()) {
            final var dto = ContentListResDto.ContentMainDto.builder().build();
            BeanUtils.copyProperties(entity, dto);
            dto.setTags(this.contentTagRepository.findTagNameListByCtntNo(entity.getCtntNo()));

            list.add(dto);
        }

        return new ContentListResDto(list, contentEntityPage.getTotalElements());
    }

    /**
     * 컨텐츠 리스트 반환
     *
     * @param list DTO 리스트
     * @param contentEntityPage Page 타입 리스트
     * @return ContentListResDto 객체 반환
     */
    @NotNull
    private ContentListResDto getContentMainList(List<ContentListResDto.ContentMainDto> list, Page<ContentEntity> contentEntityPage) {
        contentEntityPage.stream().toList().forEach(entity -> {
            final var dto = ContentListResDto.ContentMainDto.builder().build();
            BeanUtils.copyProperties(entity, dto);
            dto.setTags(this.contentTagRepository.findTagNameListByCtntNo(entity.getCtntNo()));

            list.add(dto);
        });

        return new ContentListResDto(list, contentEntityPage.getTotalElements());
    }
}
