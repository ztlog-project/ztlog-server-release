package com.devlog.admin.service.content;

import com.devlog.admin.service.content.dto.ContentReqDto;
import com.devlog.admin.service.content.dto.ContentResDto;
import com.devlog.admin.service.content.dto.ContentListResDto;
import com.devlog.admin.service.tags.dto.TagListResDto;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.util.PageUtils;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.entity.content.Content;
import com.devlog.core.repository.content.ContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        Content content = contentRepository.findById(Long.valueOf(ctntNo))
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage()));
        return ContentResDto.of(content);
    }

    /**
     * 컨텐츠 등록/수정하기
     *
     * @param reqVo 컨텐츠 요청 객체
     */
    public void saveContentDetail(ContentReqDto reqVo) {

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
