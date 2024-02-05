package com.devlog.admin.service.content;

import com.devlog.admin.dto.content.request.ContentInfoReqDto;
import com.devlog.admin.dto.content.response.ContentInfoResDto;
import com.devlog.admin.dto.content.response.ContentListResDto;
import com.devlog.admin.mapper.ContentMapper;
import com.devlog.admin.mapper.TagsMapper;
import com.devlog.admin.vo.content.ContentSearchVo;
import com.devlog.admin.vo.content.ContentVo;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.config.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentService {

    private final ContentMapper contentMapper;

    private final TagsMapper tagMapper;

    // TODO :

    /**
     * 컨텐츠 리스트 조회하기
     *
     * @param page 페이지 번호
     * @return 컨텐츠 리스트
     */
    public ContentListResDto getContentList(Integer page) {
        ContentSearchVo searchVo = ContentSearchVo.builder()
                .page(page)
                .size(CommonConstants.PAGE_SIZE)
                .build();

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
     * @throws DataNotFoundException 조회 오류 예외처리
     */
    public ContentListResDto getContentInfo(Integer ctntNo) throws DataNotFoundException {
        ContentVo vo = this.contentMapper.selectContentByCtntNo(ctntNo);

        // check
        if (ObjectUtils.isEmpty(vo)) {
            throw  new DataNotFoundException(CommonConstants.BAD_REQUEST);
        }

        ContentListResDto resDto = ContentListResDto.builder().build();
        BeanUtils.copyProperties(vo, resDto);

        return resDto;
    }


    public void createContentInfo(ContentInfoReqDto reqVo) {

    }

    public void updateContentInfo(ContentInfoReqDto reqVo) throws DataNotFoundException {

    }

    public void deleteContentInfo(Long ctntNo) throws DataNotFoundException {
        // check content exist

        // contents delete
        this.contentMapper.deleteContentMaster(ctntNo);
        this.contentMapper.deleteContentDetail(ctntNo);

        // tag delete
        this.tagMapper.deleteContentTags(ctntNo);
    }
}
