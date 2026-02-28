package com.devlog.admin.dto.category.request;

import com.devlog.admin.dto.category.response.CategoryListResDto;
import com.devlog.core.common.enumulation.UseYN;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategorySaveReqDto {
    @Schema(description = "카테고리 번호")
    private Long cateNo;

    @Schema(description = "카테고리 이름")
    private String cateNm;

    @Schema(description = "계층 깊이")
    private Integer cateDepth;

    @Schema(description = "상위 카테고리 번호")
    private Long upperCateNo;

    @Schema(description = "노출 순서")
    private Integer dispOrd;

    @Schema(description = "사용 여부")
    private UseYN useYn;

}
