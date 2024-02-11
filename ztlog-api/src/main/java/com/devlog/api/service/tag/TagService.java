package com.devlog.api.service.tag;

import com.devlog.api.service.content.dto.ContentListResDto;
import com.devlog.api.service.tag.dto.TagResDto;
import com.devlog.api.service.tag.dto.TagListResDto;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.util.PageUtils;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.domain.entity.tag.TagEntity;
import com.devlog.core.domain.repository.content.ContentRepository;
import com.devlog.core.domain.repository.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    private final ContentRepository contentRepository;

    /**
     * 태그 목록 조회하기
     *
     * @return 태그 리스트
     */
    public List<TagListResDto> getTagsList() {
        // select list -> loop -> entity to dto
        return tagRepository.findAll().stream()
                .map(tag -> TagListResDto.builder()
                        .tagNo(tag.getTagNo())
                        .tagName(tag.getTagName())
                        .count(tag.getContentTags().size())
                        .build()
                ).collect(Collectors.toList());
    }

    /**
     * 태그 게시물 목록 조회하기
     *
     * @param tagNo 태그 번호
     * @param page  페이지 번호 (기본값 = 1)
     * @return 태그 게시물 리스트
     */
    public ContentListResDto getTagsSearchList(Integer tagNo, Integer page) {
        List<ContentListResDto.ContentMainDto> list = new ArrayList<>();

        // select tag entity
        TagEntity tagEntity = tagRepository.findById(Long.valueOf(tagNo))
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage()));

        // content entity -> dto
        tagEntity.getContentTags().stream()
                .map(contentTags -> contentRepository.findById(contentTags.getContents().getCtntNo())
                        .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage()))
                ).forEach(content -> {
                    final var dto = ContentListResDto.ContentMainDto.builder().build();
                    BeanUtils.copyProperties(content, dto);

                    // setting content tags
                    List<TagResDto> tags = content.getContentTags().stream()
                            .map(tagsEntity -> TagResDto.builder()
                                    .tagNo(tagsEntity.getTags().getTagNo())
                                    .tagName(tagsEntity.getTags().getTagName())
                                    .build()
                            ).collect(Collectors.toList());
                    dto.setTags(tags);
                    list.add(dto);
                });

        // paging
        PageRequest pageRequest = PageUtils.getPageable(page);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), list.size());

        return new ContentListResDto(list.subList(start, end), list.size());
    }
}
