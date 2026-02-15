package com.devlog.api.service.content;

import com.devlog.api.service.content.dto.ContentResDto;
import com.devlog.api.service.content.dto.ContentListResDto;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.utils.PageUtils;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.entity.content.Content;
import com.devlog.core.repository.content.ContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    private final PageUtils pageUtils;

    /**
     * 컨텐츠 목록 조회하기
     *
     * @param page 페이지 번호 (기본값 = 1)
     * @return 컨텐츠 리스트 반환
     */
    public ContentListResDto getContentsList(int page) {
        Page<Content> contentPage = contentRepository.findAll(pageUtils.getPageable(page, Content.class));
        return ContentListResDto.of(contentPage);
    }

    /**
     * 컨텐츠 상세 조회하기
     *
     * @param ctntNo 컨텐츠 번호
     * @return 컨텐츠 반환
     */
    public ContentResDto getContentsDetail(Long ctntNo) {
        final var content = contentRepository.findById(ctntNo)
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage()));
        return ContentResDto.of(content);
    }

    /**
     * 컨텐츠 검색하기
     *
     * @param param 검색 키워드
     * @param page  페이지 번호 (기본값 = 1)
     * @return 검색한 키워드 관련 리스트 반환
     */
    public ContentListResDto searchContentList(String param, int page) {
        Page<Content> contentPage = contentRepository.findAllByCtntTitleContaining(param, pageUtils.getPageable(page, Content.class));
        return ContentListResDto.of(contentPage);
    }

}
