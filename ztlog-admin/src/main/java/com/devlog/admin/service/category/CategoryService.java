package com.devlog.admin.service.category;

import com.devlog.admin.dto.category.request.CategoryReqDto;
import com.devlog.admin.dto.category.response.CategoryListResDto;
import com.devlog.admin.mapper.category.CategoryMapper;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.utils.PageUtils;
import com.devlog.core.common.utils.TokenUtils;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.entity.category.Category;
import com.devlog.core.repository.category.CategoryRepository;
import com.devlog.core.repository.content.ContentRepository;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final EntityManager entityManager;

    // repository
    private final CategoryRepository categoryRepository;
    private final ContentRepository contentRepository;

    // mapper
    private final CategoryMapper categoryMapper;

    // utils
    private final TokenUtils tokenUtils;
    private final PageUtils pageUtils;

    public List<CategoryListResDto> getCategoryList() {
        return categoryMapper.selectCategoryList();
    }

    public void createCategoryDetail(HttpServletRequest request, CategoryReqDto reqDto) {
        // TODO : 상위, 하위 카테고리 설정 로직 추가할 것

        Category category = Category.created(
                reqDto.getCateNm(),
                reqDto.getCateDepth(),
                reqDto.getDispOrd(),
                tokenUtils.getUserIdFromHeader(request), null);
        categoryRepository.save(category);
    }

    public void updateCategoryDetail(HttpServletRequest request, CategoryReqDto reqDto) {
        // TODO : 상위, 하위 카테고리 설정 및 UseYN 변경 로직 추가할 것

        Category category = categoryRepository.findById(reqDto.getCateNo())
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DELETE_DATA.getMessage()));

        category.updated(
                reqDto.getCateNm(),
                reqDto.getCateDepth(),
                reqDto.getDispOrd(),
                tokenUtils.getUserIdFromHeader(request));
    }

    public void deleteCategoryDetail(Long cateNo) {
        var category = categoryRepository.findById(cateNo)
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DELETE_DATA.getMessage()));
        categoryRepository.delete(category);
    }
}
