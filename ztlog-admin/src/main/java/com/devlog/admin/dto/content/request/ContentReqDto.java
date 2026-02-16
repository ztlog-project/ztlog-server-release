package com.devlog.admin.dto.content.request;

import com.devlog.admin.dto.tag.request.TagInsertReqDto;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.entity.content.Content;
import com.devlog.core.entity.content.ContentTag;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class ContentReqDto {

    @Schema(description = "게시물 번호")
    private Long ctntNo;

    @Schema(description = "게시물 제목")
    @Size(max = CommonConstants.TITLE_SIZE, message = "content title length is too long!!")
    private String title;

    @Schema(description = "게시물 부제목")
    @Size(max = CommonConstants.SUBTITLE_SIZE, message = "content sub-title length is too long!!")
    private String subTitle;

    @Schema(description = "게시물 생성자", defaultValue = CommonConstants.ADMIN_NAME)
    private String inpUser;

    @Schema(description = "게시물 생성일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
    private LocalDateTime inpDttm;

    @Schema(description = "게시물 수정일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
    private LocalDateTime updDttm;

    public static ContentReqDto of(Content content) {
        return ContentReqDto.builder()
                .ctntNo(content.getCtntNo())
                .title(content.getCtntTitle())
                .inpUser(content.getInpUser())
                .inpDttm(content.getInpDttm())
                .build();
    }

    public static List<ContentReqDto> toContentInfoList(List<ContentTag> contentTags) {
        return Optional.ofNullable(contentTags).orElseGet(Collections::emptyList)
                .stream()
                .map(contentTag -> ContentReqDto.of(contentTag.getContents()))
                .collect(toList());
    }

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder(access = AccessLevel.PRIVATE)
    public static class ContentReqInfoDto {

        @Schema(description = "게시물 번호")
        private Long ctntNo;

        @Schema(description = "게시물 제목")
        @Size(max = CommonConstants.TITLE_SIZE, message = "content title length is too long!!")
        private String title;

        @Schema(description = "게시물 부제목")
        @Size(max = CommonConstants.SUBTITLE_SIZE, message = "content sub-title length is too long!!")
        private String subTitle;

        @Schema(description = "게시물 내용")
        private String body;

        @Schema(description = "게시물 생성자", defaultValue = CommonConstants.ADMIN_NAME)
        private String inpUser;

        @Schema(description = "게시물 태그 목록")
        List<TagInsertReqDto> tags;

        @Schema(description = "게시물 파일 경로")
        private String path;

        @Schema(description = "게시물 파일 이름")
        private String name;

        @Schema(description = "게시물 파일 확장자")
        private String ext;

    }
}
