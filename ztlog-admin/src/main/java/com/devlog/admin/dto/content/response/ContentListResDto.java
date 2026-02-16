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
    private List<ContentListResInfoDto> list;

    public static ContentListResDto of(Page<Content> contents) {
        return ContentListResDto.builder()
                .count(contents.getNumberOfElements())
                .totalCount(contents.getTotalElements())
                .totalPages(contents.getTotalPages())
                .currentPage(contents.getNumber() + 1)
                .pageSize(contents.getSize())
                .hasNext(contents.hasNext())
                .hasPrevious(contents.hasPrevious())
                .list(contents.stream()
                        .map(ContentListResInfoDto::of)
                        .collect(toList()))
                .build();
    }

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder(access = AccessLevel.PRIVATE)
    public static class ContentListResInfoDto {

        @Schema(description = "게시물 번호")
        private Long ctntNo;

        @Schema(description = "게시물 제목")
        @Size(max = CommonConstants.TITLE_SIZE, message = "content title length is too long!!")
        private String title;

        @Schema(description = "게시물 부제목")
        @Size(max = CommonConstants.SUBTITLE_SIZE, message = "content title length is too long!!")
        private String subTitle;

        @Schema(description = "게시물 태그 목록")
        private List<TagInfoReqDto> tags;

        @Schema(description = "게시물 생성자", defaultValue = CommonConstants.ADMIN_NAME)
        private String inpUser;

        @Schema(description = "게시물 생성일시")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
        private LocalDateTime inpDttm;

        @Schema(description = "게시물 수정일시")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
        private LocalDateTime updDttm;

        public static ContentListResInfoDto of(Content content) {
            return ContentListResInfoDto.builder()
                    .ctntNo(content.getCtntNo())
                    .title(content.getCtntTitle())
                    .subTitle(content.getCtntSubTitle())
                    .tags(TagInfoReqDto.toTagInfoList(content.getContentTags()))
                    .inpUser(content.getInpUser())
                    .inpDttm(content.getInpDttm())
                    .build();
        }

    }

}
