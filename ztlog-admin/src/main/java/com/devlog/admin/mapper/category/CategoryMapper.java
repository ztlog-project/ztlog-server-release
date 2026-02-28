package com.devlog.admin.mapper.category;

import com.devlog.admin.dto.category.response.CategoryListResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<CategoryListResDto> selectCategoryList();
}
