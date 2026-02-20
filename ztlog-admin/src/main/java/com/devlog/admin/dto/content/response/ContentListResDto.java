package com.devlog.admin.dto.content.response;

import com.devlog.admin.dto.tag.request.TagInfoReqDto;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.entity.content.Content;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

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

    public static ContentListResDto of(List<ContentResDto> contents) {
        int totalCount = contents.size();
        int totalPages = (int) Math.ceil((double) totalCount / CommonConstants.PAGE_LIST_SIZE);

        return ContentListResDto.builder()
                .count(CommonConstants.PAGE_LIST_SIZE)
                .totalCount(contents.size())
                .totalPages(totalPages)
                .currentPage(1)
                .pageSize(CommonConstants.PAGE_LIST_SIZE)
                .hasNext(true)
                .hasPrevious(false)
                .list(contents)
                .build();
    }

}
