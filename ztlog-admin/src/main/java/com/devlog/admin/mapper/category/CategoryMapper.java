package com.devlog.admin.mapper.category;

import com.devlog.admin.service.category.dto.response.CategoryListResDto;
import com.devlog.admin.service.category.dto.response.CategoryResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CategoryMapper {

    List<CategoryListResDto> selectCategoryList();

    Optional<CategoryResDto> selectCategory(Long cateNo);
}
