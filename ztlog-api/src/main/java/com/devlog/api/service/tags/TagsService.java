package com.devlog.api.service.tags;

import com.devlog.api.service.content.dto.ContentListResDto;
import com.devlog.api.service.tags.dto.TagListResDto;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.domain.entity.content.ContentEntity;
import com.devlog.core.domain.repository.content.ContentTagRepository;
import com.devlog.core.domain.repository.tags.TagsRepository;
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
public class TagsService {

    private final TagsRepository tagsRepository;

    private final ContentTagRepository contentTagRepository;

    /**
     *
     * @return
     */
    public List<TagListResDto> getTagsListInfo() {
        List<TagListResDto> list = new ArrayList<>();

        while (this.tagsRepository.findAll().iterator().hasNext() ) {
            final var dto = TagListResDto.builder().build();
            BeanUtils.copyProperties(this.tagsRepository.findAll().iterator().next(), dto);
            list.add(dto);
        }
        return list;
    }

    /**
     *
     * @param tagNo
     * @param page
     * @return
     */
    public ContentListResDto getTagsContentsListInfo(Integer tagNo, Integer page) {
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
