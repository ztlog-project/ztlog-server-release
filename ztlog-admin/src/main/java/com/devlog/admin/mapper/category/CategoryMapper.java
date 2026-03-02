package com.devlog.admin.mapper.category;

import com.devlog.admin.dto.category.response.CategoryListResDto;
import com.devlog.admin.dto.category.response.CategoryResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CategoryMapper {

    List<CategoryListResDto> selectCategoryList();

    Optional<CategoryResDto> selectCategory(Long cateNo);
}
