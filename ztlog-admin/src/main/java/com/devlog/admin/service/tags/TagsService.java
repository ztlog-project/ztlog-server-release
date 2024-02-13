package com.devlog.admin.service.tags;

import com.devlog.admin.dto.tag.request.TagsInfoReqDto;
import com.devlog.admin.dto.tag.response.TagResDto;
import com.devlog.admin.mapper.TagsMapper;
import com.devlog.admin.vo.tags.TagsVo;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.config.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TagsService {

    private final TagsMapper tagsMapper;

    /**
     * 태그 목록 조회
     *
     * @return 태그 리스트
     */
    public List<TagResDto> getTagsList() {
        List<TagResDto> list = new ArrayList<>();

//        this.tagsMapper.selectTagsList().forEach(vo -> {
//            TagResDto resDto = TagResDto.builder().build();
//            BeanUtils.copyProperties(resDto, vo);
//            list.add(resDto);
//        });

        return list;
    }

    /**
     * 태그 등록하기
     *
     * @param reqDto 태그 요청 객체
     */
    public void createTagsInfo(TagsInfoReqDto reqDto) {
//        TagsVo tagsVo = new TagsVo();
//        tagsVo.setTagName(reqDto.getTagsName());
        // check tag name
//        if (!ObjectUtils.isEmpty(this.tagsMapper.selectTagsByName(tagsVo.getTagName()))) {
//            throw new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage());
//        }

        // insert vo
//        this.tagsMapper.insertTagsMaster(tagsVo);
    }

    /**
     * 태그 수정하기
     *
     * @param reqDto 태그 요청 객체
     */
    public void updateTagsInfo(TagsInfoReqDto reqDto) {
//        TagsVo tagsVo = this.tagsMapper.selectTagsByNo(reqDto.getTagsNo());
        // check tag exist
//        if (ObjectUtils.isEmpty(tagsVo)) {
//            throw new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage());
//        }

        // update vo
//        tagsVo.setTagName(reqDto.getTagsName());
//        this.tagsMapper.updateTagsMaster(tagsVo);
    }

    /**
     * 태그 삭제하기
     *
     * @param tagNo 태그 번호
     */
    public void deleteTagsInfo(Long tagNo) {
//        TagsVo tagsVo = this.tagsMapper.selectTagsByNo(tagNo);
        // check tag exist
//        if (ObjectUtils.isEmpty(tagsVo)) {
//            throw new DataNotFoundException(ResponseCode.NOT_FOUND_DELETE_DATA.getMessage());
//        }

//        this.tagsMapper.deleteContentTags(tagsVo.getTagNo());
//        this.tagsMapper.deleteTagsMaster(tagsVo.getTagNo());
    }

}
