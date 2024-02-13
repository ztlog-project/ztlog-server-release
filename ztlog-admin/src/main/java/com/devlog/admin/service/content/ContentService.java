package com.devlog.admin.service.content;

import com.devlog.admin.service.content.dto.request.ContentInfoReqDto;
import com.devlog.admin.service.content.dto.response.ContentResDto;
import com.devlog.admin.service.content.dto.response.ContentListResDto;
import com.devlog.admin.dto.tag.response.TagResDto;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.util.PageUtils;
import com.devlog.core.config.exception.CoreException;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.entity.content.ContentEntity;
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

//    private final ContentMapper contentMapper;

    private final ContentRepository contentRepository;

    /**
     * 컨텐츠 리스트 조회하기
     *
     * @param page 페이지 번호
     * @return 컨텐츠 리스트
     */
    public ContentListResDto getContentList(Integer page) {
        List<ContentListResDto.ContentMainDto> list = new ArrayList<>();

        // select content list
        Page<ContentEntity> contentEntityPage = contentRepository.findAll(PageUtils.getPageable(page));

        // setting content entity -> dto list
        contentEntityPage.getContent().forEach(content -> {
            final var dto = ContentListResDto.ContentMainDto.builder().build();
            BeanUtils.copyProperties(content, dto);
            list.add(dto);
        });

        return new ContentListResDto(list, Long.valueOf(contentEntityPage.getTotalElements()).intValue());
    }

    /**
     * 컨텐츠 상세 조회하기
     *
     * @param ctntNo 컨텐츠 번호
     * @return 컨텐츠 객체
     */
    public ContentResDto getContentDetail(Long ctntNo){
        ContentEntity content = contentRepository.findById(Long.valueOf(ctntNo))
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage()));

        final var dto = ContentResDto.builder().build();
        BeanUtils.copyProperties(content, dto);

        // setting content tags
        List<TagResDto> tags = content.getContentTags()
                .stream().map(contentTags -> TagResDto.builder()
                        .tagNo(contentTags.getTags().getTagNo())
                        .tagName(contentTags.getTags().getTagName())
                        .build()
                ).collect(Collectors.toList());
        dto.setTags(tags);

        return dto;
    }

    /**
     * 컨텐츠 등록/수정하기
     *
     * @param reqVo 컨텐츠 요청 객체
     */
    public void saveContentDetail(ContentInfoReqDto reqVo) {

        /*

          프로세트 정리
          1. 컨텐츠 존재 확인

           -> 1.1 존재하지 않을 경우 등록
            새 객체 생성

            컨텐츠 마스터 / 컨텐츠 디테일 / 컨텐츠 태그

           -> 1.2 존재할 경우 수정

            컨텐츠 마스터 / 컨텐츠 디테일 / 컨텐츠 태그



         */


//        ContentEntity content = contentRepository.findById(reqVo.getCtntNo())


//        ContentVo vo = new ContentVo();
//        BeanUtils.copyProperties(reqVo, vo);
//
//        // vo setting
//        vo.setInpUser(CommonConstants.ADMIN_NAME);
//        vo.setInpDttm(LocalDateTime.now().format(DateTimeFormatter.ofPattern(CommonConstants.DEFAULT_DATETIME_FORMAT)));
//        vo.setUpdDttm(LocalDateTime.now().format(DateTimeFormatter.ofPattern(CommonConstants.DEFAULT_DATETIME_FORMAT)));
//
//        // insert
//        this.contentMapper.insertContentMaster(vo);
//        this.contentMapper.insertContentDetail(vo);

    }

    /**
     * 컨텐츠 삭제하기
     *
     * @param ctntNo 컨텐츠 번호
     * @throws CoreException 조회 오류 예외처리
     */
    public void deleteContentDetail(Long ctntNo)  {


        // check content exist
//        ContentVo vo = this.contentMapper.selectContentByCtntNo(ctntNo);
//        if (ObjectUtils.isEmpty(vo)) {
//            throw new DataNotFoundException(ResponseCode.NOT_FOUND_DELETE_DATA.getMessage());
//        }
//
//        // contents delete
//        this.contentMapper.deleteContentMaster(ctntNo);
//        this.contentMapper.deleteContentDetail(ctntNo);

        // tag delete
//        this.tagMapper.deleteContentTags(ctntNo);
    }

}
