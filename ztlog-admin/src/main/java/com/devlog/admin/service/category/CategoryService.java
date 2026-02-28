package com.devlog.admin.service.category;

import com.devlog.admin.dto.category.request.CategorySaveReqDto;
import com.devlog.admin.dto.category.request.CategoryUpdateReqDto;
import com.devlog.admin.dto.category.response.CategoryListResDto;
import com.devlog.admin.mapper.category.CategoryMapper;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.enumulation.UseYN;
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

    public void createCategoryDetail(HttpServletRequest request, CategorySaveReqDto reqDto) {
        // 상위 카테고리 조회
        Category upperCategory = null;
        if (reqDto.getUpperCateNo() != null) {
            upperCategory = categoryRepository.findById(reqDto.getUpperCateNo())
                    .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DELETE_DATA.getMessage()));
        }

        Category category = Category.created(
                reqDto.getCateNm(),
                reqDto.getCateDepth(),
                reqDto.getDispOrd(),
                tokenUtils.getUserIdFromHeader(request),
                upperCategory);
        categoryRepository.save(category);
    }

    public void updateCategoryDetail(HttpServletRequest request, CategoryUpdateReqDto reqDto) {
        // TODO : 상위, 하위 카테고리 설정 및 UseYN 변경 로직 추가할 것


        // 수정할 카테고리 조회 (자식들까지 같이 가져오도록 Fetch Join 권장)
        Category category = categoryRepository.findById(reqDto.getCateNo())
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DELETE_DATA.getMessage()));

        // 상위 카테고리 조회
        Category upperCategory = null;
        if (reqDto.getUpperCateNo() != null) {
            upperCategory = categoryRepository.findById(reqDto.getUpperCateNo())
                    .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DELETE_DATA.getMessage()));
        }

        category.updated(
                reqDto.getCateNm(),
                reqDto.getCateDepth(),
                reqDto.getDispOrd(),
                UseYN.valueOf(reqDto.getUseYn()),
                tokenUtils.getUserIdFromHeader(request),
                upperCategory);

        // 4. 하위 카테고리 리스트 처리 (재귀)
        if (reqDto.getCategories() != null && !reqDto.getCategories().isEmpty()) {
            for (CategoryUpdateReqDto childDto : reqDto.getCategories()) {
                // 자식의 부모 ID를 현재 카테고리 ID로 강제 설정하여 계층 유지
                updateOrCreateChild(childDto, category);
            }
        }
        // categoryRepository.save(category);
    }

    public void deleteCategoryDetail(Long cateNo) {
        var category = categoryRepository.findById(cateNo)
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DELETE_DATA.getMessage()));
        categoryRepository.delete(category);
    }

    private void updateOrCreateChild(CategoryUpdateReqDto childDto, Category parent) {
        if (childDto.getCateNo() != null) {
            // 기존 자식 수정
            Category child = categoryRepository.findById(childDto.getCateNo())
                    .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DELETE_DATA.getMessage()));

            child.updated(
                    childDto.getCateNm(),
                    childDto.getCateDepth(),
                    childDto.getDispOrd(),
                    UseYN.valueOf(childDto.getUseYn()),
                    parent.getInpUser(),
                    parent);

            // 자식의 자식들도 재귀 처리
            if (childDto.getCategories() != null) {
                childDto.getCategories().forEach(grandChild -> updateOrCreateChild(grandChild, child));
            }
        } else {
            // 신규 자식 등록
            Category newChild = Category.created(
                    childDto.getCateNm(),
                    childDto.getCateDepth(),
                    childDto.getDispOrd(),
                    parent.getInpUser(),
                    parent);
            categoryRepository.save(newChild);
        }
    }
}
