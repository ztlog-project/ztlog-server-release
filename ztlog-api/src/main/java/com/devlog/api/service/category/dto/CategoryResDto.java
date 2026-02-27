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
public class CategoryResDto {

    @Schema(description = "카테고리 번호")
    private Long cateNo;

    @Schema(description = "카테고리 이름")
    private String cateNm;

    @Schema(description = "계층 깊이")
    private Integer cateDepth;

    @Schema(description = "전시 순서")
    private Integer dispOrd;

    @Schema(description = "사용 여부")
    private UseYN useYn;

    @Schema(description = "생성자")
    private String inpUser;

    private List<CategoryResDto> categories;

    public static CategoryResDto of(Category category) {
        return CategoryResDto.builder()
                .cateNo(category.getCateNo())
                .cateNm(category.getCateNm())
                .cateDepth(category.getCateDepth())
                .dispOrd(category.getDispOrd())
                .useYn(category.getUseYn())
                .inpUser(category.getInpUser())
                .categories(category.getCategories().stream() // 자식들을 다시 DTO로 변환
                        .map(CategoryResDto::of)
                        .collect(Collectors.toList()))
                .build();
    }

}
