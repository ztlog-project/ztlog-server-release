package com.devlog.admin.service.content;

import com.devlog.admin.mapper.content.ContentStatisticsMapper;
import com.devlog.admin.service.content.dto.ContentReqDto;
import com.devlog.admin.service.content.dto.ContentResDto;
import com.devlog.admin.service.content.dto.ContentListResDto;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.utils.PageUtils;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.entity.content.Content;
import com.devlog.core.entity.content.ContentDetail;
import com.devlog.core.entity.content.ContentTag;
import com.devlog.core.entity.tag.Tag;
import com.devlog.core.repository.content.ContentDtlRepository;
import com.devlog.core.repository.content.ContentRepository;
import com.devlog.core.repository.content.ContentTagRepository;
import com.devlog.core.repository.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ContentService {

    // repository
    private final ContentRepository contentRepository;
    private final ContentDtlRepository contentDtlRepository;
    private final ContentTagRepository contentTagRepository;
    private final TagRepository tagRepository;

    // mapper
    private final ContentStatisticsMapper contentStatisticsMapper;

    /**
     * 컨텐츠 리스트 조회하기
     *
     * @param page 페이지 번호
     * @return 컨텐츠 리스트
     */
    public ContentListResDto getContentList(Integer page) {
        Page<Content> contentPage = contentRepository.findAll(PageUtils.getPageable(page));
        return ContentListResDto.of(contentPage.getContent());
    }

    /**
     * 컨텐츠 상세 조회하기
     *
     * @param ctntNo 컨텐츠 번호
     * @return 컨텐츠 객체
     */
    public ContentResDto getContentDetail(Long ctntNo){
        final var content = contentRepository.findById(ctntNo)
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage()));
        return ContentResDto.of(content);
    }

    /**
     * 컨텐츠 등록하기
     *
     * @param reqDto 컨텐츠 요청 객체
     */
    public void createContentDetail(ContentReqDto reqDto) {
        ContentDetail contentDetail = ContentDetail.created(reqDto.getTitle(), reqDto.getBody(), CommonConstants.ADMIN_NAME);
        Content content = Content.created(contentDetail);

        this.contentRepository.save(content);

        // content - tag
        List<ContentTag> contentTags = new ArrayList<>();
        // 새 태그 등록 (없으면 생성)
        reqDto.getTags().forEach(tagReqDto -> {
            final var tag = tagRepository.findById(tagReqDto.getTagNo())
                    .orElseGet(() -> tagRepository.save(Tag.created(tagReqDto.getTagName())));
            contentTags.add(ContentTag.created(tag, tagReqDto.getSort(), content));
        });

        contentTagRepository.saveAll(contentTags);
    }

    /**
     * 컨텐츠 수정하기
     *
     * @param reqDto 컨텐츠 요청 객체
     */
    public void updateContentDetail(ContentReqDto reqDto) {
        Content content = contentRepository.findById(reqDto.getCtntNo())
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage()));

        // 컨텐츠 마스터 수정
        content.updated(reqDto.getTitle(), reqDto.getBody());

        // 컨텐츠 상세 수정
        content.getContentDetail().updated(reqDto.getTitle(), reqDto.getBody(), content);

        // 기존 태그 삭제 후 새 태그 등록
        contentTagRepository.deleteAll(content.getContentTags());

        List<ContentTag> contentTags = new ArrayList<>();
        reqDto.getTags().forEach(tagReqDto -> {
            Tag tag = tagRepository.findById(tagReqDto.getTagNo())
                    .orElseGet(() -> tagRepository.save(Tag.created(tagReqDto.getTagName())));
            contentTags.add(ContentTag.created(tag, tagReqDto.getSort(), content));
        });

        contentTagRepository.saveAll(contentTags);
    }

    /**
     * 컨텐츠 삭제하기
     *
     * @param ctntNo 컨텐츠 번호
     */
    public void deleteContentDetail(Long ctntNo)  {
        final var content = contentRepository.findById(ctntNo)
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DELETE_DATA.getMessage()));

        // delete
        contentTagRepository.deleteAll(content.getContentTags());
        contentRepository.delete(content);
    }

}
