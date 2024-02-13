package com.devlog.admin.service.tags.dto;

import com.devlog.admin.service.content.dto.ContentInfoDto;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.entity.tag.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
public class TagListResDto {

    @Schema(description = "태그 갯수")
    private Integer count;

    @Schema(description = "태그 목록")
    private List<TagMainDto> list;

    public static TagListResDto of(List<Tag> tags) {
        return TagListResDto.builder()
                .count(tags.size())
                .list(tags.stream()
                        .map(TagMainDto::of)
                        .collect(toList()))
                .build();
    }

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder(access = AccessLevel.PRIVATE)
    public static class TagMainDto {
        @Schema(description = "태그 번호")
        private Long tagNo;

        @Schema(description = "태그 이름")
        @NotNull(message = "tag name can not be null!!")
        @Size(max = CommonConstants.TAG_NAME_SIZE, message = "tag name length is too long!!")
        private String tagName;

        @Schema(description = "태그 게시물")
        private List<ContentInfoDto> contents;

        @Schema(description = "태그 생성일시")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
        private LocalDateTime inpDttm;

        @Schema(description = "태그 수정일시")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
        private LocalDateTime updDttm;

        public static TagMainDto of(Tag tag) {
            return TagMainDto.builder()
                    .tagNo(tag.getTagNo())
                    .tagName(tag.getTagName())
                    .contents(ContentInfoDto.toContentInfoList(tag.getContentTags()))
                    .inpDttm(tag.getInpDttm())
                    .updDttm(tag.getUpdDttm())
                    .build();
        }

    }

}
