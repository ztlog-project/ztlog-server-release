package com.devlog.api.service.category.dto;

import com.devlog.core.common.enumulation.UseYN;
import com.devlog.core.entity.category.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class CategoryInfoDto {

    @Schema(description = "카테고리 번호")
    private Long cateNo;

    @Schema(description = "카테고리 이름")
    private String cateNm;

    public static CategoryInfoDto of(Category category) {
        return CategoryInfoDto.builder()
                .cateNo(category.getCateNo())
                .cateNm(category.getCateNm())
                .build();
    }

}
