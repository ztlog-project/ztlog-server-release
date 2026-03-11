package com.devlog.api.service.category.dto;

import com.devlog.core.common.enumulation.UseYN;
import com.devlog.core.common.utils.DateUtils;
import com.devlog.core.entity.category.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.function.Function;
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

    @Schema(description = "게시물 갯수")
    private Integer ctntCount;

    @Schema(description = "사용 여부")
    private UseYN useYn;

    @Schema(description = "생성자")
    private String inpUser;

    @Schema(description = "생성일시")
    private String inpDttm;

    private List<CategoryResDto> categories;

    public static CategoryResDto of(Category category, Function<Long, Integer> countProvider) {
        return CategoryResDto.builder()
                .cateNo(category.getCateNo())
                .cateNm(category.getCateNm())
                .cateDepth(category.getCateDepth())
                .dispOrd(category.getDispOrd())
                .ctntCount(countProvider.apply(category.getCateNo()))
                .useYn(category.getUseYn())
                .inpUser(category.getInpUser())
                .inpDttm(DateUtils.datetomeToString(category.getInpDttm()))
                .categories(category.getCategories().stream()
                        .map(child -> of(child, countProvider))
                        .collect(Collectors.toList()))
                .build();
    }
}
