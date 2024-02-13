package com.devlog.api.service.tag.dto;

import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.entity.tag.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class TagMainResDto {

    @Schema(description = "태그 번호")
    private Long tagNo;

    @Schema(description = "태그 이름")
    @NotNull(message = "tag name can not be null!!")
    @Size(max = CommonConstants.TAG_NAME_SIZE, message = "tag name length is too long!!")
    private String tagName;

    @Schema(description = "태그된 게시물 갯수")
    private Integer count;

    public static TagMainResDto of(Tag tag) {
        return TagMainResDto.builder()
                .tagNo(tag.getTagNo())
                .tagName(tag.getTagName())
                .count(tag.getContentTags().size())
                .build();
    }

}
