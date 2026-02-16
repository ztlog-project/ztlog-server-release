package com.devlog.admin.dto.tag.response;

import com.devlog.admin.dto.content.request.ContentReqDto;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.entity.tag.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class TagResDto {

    @Schema(description = "태그 번호")
    private Long tagNo;

    @Schema(description = "태그 이름")
    @NotNull(message = "tag name can not be null!!")
    @Size(max = CommonConstants.TAG_NAME_SIZE, message = "tag name length is too long!!")
    private String tagName;

    @Schema(description = "태그 게시물")
    private List<ContentReqDto> contents;

    @Schema(description = "태그 생성일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
    private LocalDateTime inpDttm;

    @Schema(description = "태그 수정일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
    private LocalDateTime updDttm;

    public static TagResDto of(Tag tag) {
        return TagResDto.builder()
                .tagNo(tag.getTagNo())
                .tagName(tag.getTagName())
                .contents(ContentReqDto.toContentInfoList(tag.getContentTags()))
                .inpDttm(tag.getInpDttm())
                .updDttm(tag.getUpdDttm())
                .build();
    }
}
