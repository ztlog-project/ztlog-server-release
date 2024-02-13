package com.devlog.admin.service.tags;

import com.devlog.admin.service.tags.dto.TagReqDto;
import com.devlog.admin.service.tags.dto.TagResDto;
import com.devlog.core.repository.tag.TagRepository;
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
public class TagService {

    private final TagRepository tagRepository;

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
    public void createTagsInfo(TagReqDto reqDto) {
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
    public void updateTagsInfo(TagReqDto reqDto) {
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
