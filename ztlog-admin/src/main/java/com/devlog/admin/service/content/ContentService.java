package com.devlog.admin.service.content;

import com.devlog.admin.dto.content.request.ContentReqDto;
import com.devlog.admin.mapper.content.ContentStatisticsMapper;
import com.devlog.admin.dto.content.response.ContentResDto;
import com.devlog.admin.dto.content.response.ContentListResDto;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.utils.TokenUtils;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.entity.content.Content;
import com.devlog.core.entity.content.ContentTag;
import com.devlog.core.entity.tag.Tag;
import com.devlog.core.repository.content.ContentRepository;
import com.devlog.core.repository.content.ContentTagRepository;
import com.devlog.core.repository.tag.TagRepository;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ContentService {

    private final EntityManager entityManager;
    // repository
    private final ContentRepository contentRepository;
//    private final ContentDtlRepository contentDtlRepository;
    private final ContentTagRepository contentTagRepository;
    private final TagRepository tagRepository;

    // mapper
    private final ContentStatisticsMapper contentStatisticsMapper;
    private final TokenUtils tokenUtils;

    /**
     * 컨텐츠 리스트 조회하기
     *
     * @param page 페이지 번호
     * @return 컨텐츠 리스트
     */
    public ContentListResDto getContentList(Integer page) {
        List<ContentResDto> contentList = contentStatisticsMapper.selectContentList();
        return ContentListResDto.of(contentList, page);
    }

    /**
     * 컨텐츠 상세 조회하기
     *
     * @param ctntNo 컨텐츠 번호
     * @return 컨텐츠 객체
     */
    public ContentResDto getContentDetail(Long ctntNo) {
        return contentStatisticsMapper.selectContent(ctntNo)
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage()));
    }

    /**
     * 컨텐츠 등록하기
     *
     * @param request http 요청 객체
     * @param reqDto  컨텐츠 요청 객체
     */
    public void createContentDetail(HttpServletRequest request, ContentReqDto.ContentReqInfoDto reqDto) {
        Content content = Content.created(
                reqDto.getTitle(),
                reqDto.getSubTitle(),
                reqDto.getBody(),
                tokenUtils.getUserIdFromHeader(request));
        contentRepository.save(content);

        // Create and save tags after content has ID
        List<ContentTag> contentTags = new ArrayList<>();
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
     * @param request http 요청 객체
     * @param reqDto  컨텐츠 요청 객체
     */
    public void updateContentDetail(HttpServletRequest request, ContentReqDto.ContentReqInfoDto reqDto) {
        Content content = contentRepository.findById(reqDto.getCtntNo())
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage()));

        // 컨텐츠 수정
        content.updated(reqDto.getTitle(), reqDto.getSubTitle());
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
    public void deleteContentDetail(Long ctntNo) {
        final var content = contentRepository.findById(ctntNo)
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DELETE_DATA.getMessage()));
        // delete
        contentTagRepository.deleteAll(content.getContentTags());
        contentRepository.delete(content);
        contentRepository.flush();

        entityManager.refresh(content);
    }

}
