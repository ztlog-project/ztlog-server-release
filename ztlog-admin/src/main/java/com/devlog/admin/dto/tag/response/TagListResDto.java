package com.devlog.admin.dto.tag.response;

import com.devlog.core.entity.tag.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class TagListResDto {

    @Schema(description = "현재 페이지 게시물 수")
    private Integer count;

    @Schema(description = "전체 게시물 수")
    private Long totalCount;

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
    private List<TagCountResDto> list;

    public static TagListResDto of(Page<Tag> tags) {
        return TagListResDto.builder()
                .count(tags.getNumberOfElements())
                .totalCount(tags.getTotalElements())
                .totalPages(tags.getTotalPages())
                .currentPage(tags.getNumber() + 1)
                .pageSize(tags.getSize())
                .hasNext(tags.hasNext())
                .hasPrevious(tags.hasPrevious())
                .list(tags.stream()
                        .map(TagCountResDto::of)
                        .collect(toList()))
                .build();
    }

}
