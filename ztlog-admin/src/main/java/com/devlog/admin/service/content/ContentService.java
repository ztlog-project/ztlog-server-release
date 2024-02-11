package com.devlog.admin.service.content;

import com.devlog.admin.dto.content.request.ContentInfoReqDto;
import com.devlog.admin.dto.content.response.ContentInfoResDto;
import com.devlog.admin.dto.content.response.ContentListResDto;
import com.devlog.admin.mapper.ContentMapper;
import com.devlog.admin.mapper.TagsMapper;
import com.devlog.admin.vo.content.ContentSearchVo;
import com.devlog.admin.vo.content.ContentVo;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.config.exception.CoreException;
import com.devlog.core.config.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentService {

    private final ContentMapper contentMapper;

    private final TagsMapper tagMapper;

    /**
     * 컨텐츠 리스트 조회하기
     *
     * @param page 페이지 번호
     * @return 컨텐츠 리스트
     */
    public ContentListResDto getContentList(Integer page) {
        ContentSearchVo searchVo = new ContentSearchVo();
        searchVo.setPage(page);
        searchVo.setSize(CommonConstants.PAGE_SIZE);

        List<ContentInfoResDto> list = new ArrayList<>();
        this.contentMapper.selectContentList(searchVo).forEach(vo -> {
            ContentInfoResDto dto = ContentInfoResDto.builder().build();
            BeanUtils.copyProperties(vo, dto);
            list.add(dto);
        });

        return ContentListResDto.builder().list(list).build();
    }

    /**
     * 컨텐츠 상세 조회하기
     *
     * @param ctntNo 컨텐츠 번호
     * @return 컨텐츠 객체
     */
    public ContentInfoResDto getContentInfo(Long ctntNo){
        ContentVo vo = this.contentMapper.selectContentByCtntNo(ctntNo);
        if (ObjectUtils.isEmpty(vo)) {
            throw new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage());
        }

        ContentInfoResDto resDto = ContentInfoResDto.builder().build();
        BeanUtils.copyProperties(vo, resDto);

        return resDto;
    }

    /**
     * 컨텐츠 등록하기
     *
     * @param reqVo 컨텐츠 요청 객체
     */
    public void createContentInfo(ContentInfoReqDto reqVo) {
        ContentVo vo = new ContentVo();
        BeanUtils.copyProperties(reqVo, vo);

        // vo setting
        vo.setInpUser(CommonConstants.ADMIN);
        vo.setInpDttm(LocalDateTime.now().format(DateTimeFormatter.ofPattern(CommonConstants.DEFAULT_DATETIME_FORMAT)));
        vo.setUpdDttm(LocalDateTime.now().format(DateTimeFormatter.ofPattern(CommonConstants.DEFAULT_DATETIME_FORMAT)));

        // insert
        this.contentMapper.insertContentMaster(vo);
        this.contentMapper.insertContentDetail(vo);

    }

    /**
     * 컨텐츠 수정하기
     *
     * @param reqVo 컨텐츠 요청 객체
     */
    public void updateContentInfo(ContentInfoReqDto reqVo) {
        ContentVo vo = this.contentMapper.selectContentByCtntNo(reqVo.getCtntNo());
        if (ObjectUtils.isEmpty(vo)) {
            throw new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage());
        }

        vo.setCtntTitle(reqVo.getCtntTitle());
        vo.setCtntSubTitle(reqVo.getCtntBody().substring(0, 300));
        vo.setCtntBody(reqVo.getCtntBody());
        vo.setUpdDttm(LocalDateTime.now().format(DateTimeFormatter.ofPattern(CommonConstants.DEFAULT_DATETIME_FORMAT)));

        this.contentMapper.updateContentMaster(vo);
        this.contentMapper.updateContentDetail(vo);
    }

    /**
     * 컨텐츠 삭제하기
     *
     * @param ctntNo 컨텐츠 번호
     * @throws CoreException 조회 오류 예외처리
     */
    public void deleteContentInfo(Long ctntNo)  {
        // check content exist
        ContentVo vo = this.contentMapper.selectContentByCtntNo(ctntNo);
        if (ObjectUtils.isEmpty(vo)) {
            throw new DataNotFoundException(ResponseCode.NOT_FOUND_DELETE_DATA.getMessage());
        }

        // contents delete
        this.contentMapper.deleteContentMaster(ctntNo);
        this.contentMapper.deleteContentDetail(ctntNo);

        // tag delete
//        this.tagMapper.deleteContentTags(ctntNo);
    }
}
