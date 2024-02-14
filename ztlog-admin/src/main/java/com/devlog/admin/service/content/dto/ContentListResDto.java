package com.devlog.admin.service.content.dto;

import com.devlog.admin.service.tag.dto.TagInfoDto;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.entity.content.Content;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class ContentListResDto {

    @Schema(description = "게시물 갯수")
    private Integer count;

    @Schema(description = "게시물 목록")
    private List<ContentMainDto> list;

    public static ContentListResDto of(List<Content> contents) {
        return ContentListResDto.builder()
                .count(contents.size())
                .list(contents.stream()
                        .map(ContentMainDto::of)
                        .collect(toList()))
                .build();
    }

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder(access = AccessLevel.PRIVATE)
    public static class ContentMainDto {

        @Schema(description = "게시물 번호")
        private Long ctntNo;

        @Schema(description = "게시물 제목")
        @Size(max = CommonConstants.CONTENT_TITLE_SIZE, message = "content title length is too long!!")
        private String title;

        @Schema(description = "게시물 부제목")
        @Size(max = CommonConstants.CONTENT_SUBTITLE_SIZE, message = "content title length is too long!!")
        private String subTitle;

        @Schema(description = "게시물 태그 목록")
        private List<TagInfoDto> tags;

        @Schema(description = "게시물 생성자", defaultValue = CommonConstants.ADMIN_NAME)
        private String inpUser;

        @Schema(description = "게시물 생성일시")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
        private LocalDateTime inpDttm;

        @Schema(description = "게시물 수정일시")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
        private LocalDateTime updDttm;

        public static ContentMainDto of(Content content) {
            return ContentMainDto.builder()
                    .ctntNo(content.getCtntNo())
                    .title(content.getCtntTitle())
                    .subTitle(content.getCtntSubTitle())
                    .tags(TagInfoDto.toTagInfoList(content.getContentTags()))
                    .inpUser(content.getInpUser())
                    .inpDttm(content.getInpDttm())
                    .build();
        }

    }

}
