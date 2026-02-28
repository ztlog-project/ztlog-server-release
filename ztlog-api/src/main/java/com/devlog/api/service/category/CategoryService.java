package com.devlog.api.service.category;

import com.devlog.api.service.category.dto.CategoryResDto;
import com.devlog.api.service.content.dto.ContentListResDto;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.enumulation.UseYN;
import com.devlog.core.common.utils.PageUtils;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.entity.category.Category;
import com.devlog.core.entity.content.Content;
import com.devlog.core.repository.category.CategoryRepository;
import com.devlog.core.repository.content.ContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ContentRepository contentRepository;
    private final PageUtils pageUtils;

    /**
     * 카테고리 목록 조회하기
     *
     * @return 카테고리 리스트
     */
    public List<CategoryResDto> getCategoryList() {
        return categoryRepository.findAllByUseYnIs(UseYN.Y).stream().map(CategoryResDto::of).toList();
    }

    /**
     * 카테고리 게시물 목록 조회하기
     *
     * @param cateNo 카테고리 번호
     * @param page  페이지 번호 (기본값 = 1)
     * @return 카테고리 게시물 리스트
     */
    public ContentListResDto getCategoryContentList(Integer cateNo, Integer page) {
        if (!categoryRepository.existsById(Long.valueOf(cateNo))) {
            throw new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage());
        }
        Page<Content> contentPage = contentRepository.findAllByCategoryCateNo(Long.valueOf(cateNo), pageUtils.getPageable(page, Content.class));
        return ContentListResDto.of(contentPage);
    }
}
