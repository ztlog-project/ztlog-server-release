package com.devlog.admin.service.content;

import com.devlog.admin.service.content.dto.ContentReqDto;
import com.devlog.admin.service.content.dto.ContentResDto;
import com.devlog.admin.service.content.dto.ContentListResDto;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.util.PageUtils;
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

    private final ContentRepository contentRepository;

    private final ContentDtlRepository contentDtlRepository;

    private final ContentTagRepository contentTagRepository;

    private final TagRepository tagRepository;

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
        // save detail entity
        final var contentDetail =  contentDtlRepository.save(ContentDetail.created(reqDto.getTitle(), reqDto.getBody(), CommonConstants.ADMIN_NAME));
        // save master entity
        final var content = contentRepository.save(Content.created(contentDetail));

        // content - tag
        List<ContentTag> contentTags = new ArrayList<>();
        reqDto.getTags().forEach(tagReqDto -> {
            final var tag = tagRepository.findById(tagReqDto.getTagNo())
                    .orElse(tagRepository.save(Tag.created(tagReqDto.getTagName())));
            contentTags.add(ContentTag.created(tag, tagReqDto.getSort(), content));
        });
        contentTagRepository.saveAll(contentTags);
    }

    /**
     * 컨텐츠 수정하기
     *
     * @param reqVo 컨텐츠 요청 객체
     */
    public void updateContentDetail(ContentReqDto reqVo) {

        /*

          프로세트 정리
          1. 컨텐츠 존재 확인

           -> 1.1 존재하지 않을 경우 등록
            새 객체 생성

            컨텐츠 마스터 / 컨텐츠 디테일 / 컨텐츠 태그

           -> 1.2 존재할 경우 수정

            컨텐츠 마스터 / 컨텐츠 디테일 / 컨텐츠 태그



         */

    }

    /**
     * 컨텐츠 삭제하기
     *
     * @param ctntNo 컨텐츠 번호
     */
    public void deleteContentDetail(Long ctntNo)  {


    }

}
