package com.devlog.api.service.content;

import com.devlog.api.service.content.dto.ContentInfoResDto;
import com.devlog.api.service.content.dto.ContentListResDto;
import com.devlog.core.common.constants.CommonConstants;
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
     * @param page
     * @return
     */
    public ContentListResDto getContentsListInfo(int page) {
        Pageable pageable = PageRequest.of(page, CommonConstants.PAGE_SIZE);
        List<ContentListResDto.ContentMainDto> list = new ArrayList<>();

        Page<ContentEntity> contentEntityPage = this.contentRepository.findAll(pageable);

        return getContentMainList(list, contentEntityPage);
    }

    /**
     * @param ctntNo
     * @return
     * @throws DataNotFoundException
     */
    public ContentInfoResDto getContentInfo(Integer ctntNo) throws DataNotFoundException {
        ContentDtlEntity entity = this.contentDtlRepository.findById(Long.valueOf(ctntNo))
                .orElseThrow(() -> new DataNotFoundException(CommonConstants.BAD_REQUEST));

        final var dto = ContentInfoResDto.builder().build();
        BeanUtils.copyProperties(entity, dto);
        dto.setTags(this.contentTagRepository.findTagNameListByCtntNo(entity.getCtntNo()));

        return dto;
    }

    /**
     *
     * @param param
     * @param page
     * @return
     */
    public ContentListResDto searchContentsInfo(String param, int page) {
        Pageable pageable = PageRequest.of(page, CommonConstants.PAGE_SIZE);
        List<ContentListResDto.ContentMainDto> list = new ArrayList<>();

        Page<ContentEntity> contentEntityPage = this.contentRepository.findAllByCtntTitleContaining(param, pageable);

        return getContentMainList(list, contentEntityPage);
    }

    /**
     *
     * @param list
     * @param contentEntityPage
     * @return
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
