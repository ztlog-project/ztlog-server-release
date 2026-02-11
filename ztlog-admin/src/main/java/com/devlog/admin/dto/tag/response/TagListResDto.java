package com.devlog.admin.dto.tag.response;

import com.devlog.core.entity.tag.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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
    private List<TagResDto> list;

    public static TagListResDto of(List<Tag> tags) {
        return TagListResDto.builder()
                .count(tags.size())
                .list(tags.stream()
                        .map(TagResDto::of)
                        .collect(toList()))
                .build();
    }

}
