package com.devlog.admin.dto.content.response;

import com.devlog.core.common.constants.CommonConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class ContentListResDto {

    @Schema(description = "현재 페이지 게시물 수")
    private Integer count;

    @Schema(description = "전체 게시물 수")
    private Integer totalCount;

    @Schema(description = "전체 페이지 수")
    private Integer totalPages;

    @Schema(description = "현재 페이지 번호")
    private Integer currentPage;

    @Schema(description = "페이지 크기")
    private Integer pageSize;

    @Schema(description = "다음 페이지 존재 여부")
    private Boolean hasNext;

    @Schema(description = "이전 페이지 존재 여부")
    private Boolean hasPrevious;

    @Schema(description = "게시물 목록")
    private List<ContentResDto> list;

    public static ContentListResDto of(List<ContentResDto> list, int currentPage) {
        int totalCount = list.size();
        int totalPages = (int) Math.ceil((double) totalCount / CommonConstants.PAGE_LIST_SIZE);

        return ContentListResDto.builder()
                .count(CommonConstants.PAGE_LIST_SIZE)
                .totalCount(totalCount)
                .totalPages(totalPages)
                .currentPage(currentPage)
                .pageSize(CommonConstants.PAGE_LIST_SIZE)
                .hasNext(currentPage < totalPages)
                .hasPrevious(currentPage > 1)
                .list(list)
                .build();
    }

}
