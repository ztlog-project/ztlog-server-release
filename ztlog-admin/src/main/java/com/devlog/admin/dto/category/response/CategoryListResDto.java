package com.devlog.admin.dto.category.response;

import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.common.enumulation.UseYN;
import com.devlog.core.entity.category.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class CategoryListResDto {

    @Schema(description = "카테고리 번호")
    private Long cateNo;

    @Schema(description = "카테고리 이름")
    private String cateNm;

    @Schema(description = "계층 깊이")
    private Integer cateDepth;

    @Schema(description = "노출 순서")
    private Integer dispOrd;

    @Schema(description = "사용 여부")
    private UseYN useYn;

    @Schema(description = "하위 카테고리 리스트")
    private List<CategoryListResDto> categories = new ArrayList<>();

    @Schema(description = "생성자")
    private String inpUser;

    @Schema(description = "태그 생성일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
    private LocalDateTime inpDttm;

    @Schema(description = "태그 수정일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
    private LocalDateTime updDttm;

//    public static CategoryListResDto of(Category entity) {
//        return CategoryListResDto.builder()
//                .cateNo(entity.getCateNo())
//                .cateNm(entity.getCateNm())
//                .cateDepth(entity.getCateDepth())
//                .dispOrd(entity.getDispOrd())
//                .useYn(entity.getUseYn())
//                .inpUser(entity.getInpUser())
//                .categories(entity.getCategories().stream() // 엔티티의 자식 리스트 순회
//                        .map(CategoryListResDto::of)    // 다시 from 호출 (재귀)
//                        .collect(Collectors.toList()))
//                .build();
//    }
}
