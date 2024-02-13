package com.devlog.api.service.tag.dto;

import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.entity.content.ContentTag;
import com.devlog.core.entity.tag.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

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

    public static TagResDto of(Tag tag) {
        return TagResDto.builder()
                .tagNo(tag.getTagNo())
                .tagName(tag.getTagName())
                .build();
    }

    public static List<TagResDto> toTagResDtoList(List<ContentTag> contentTags) {
        return contentTags.stream()
                .map(contentTag -> TagResDto.of(contentTag.getTags()))
                .collect(toList());
    }
}
