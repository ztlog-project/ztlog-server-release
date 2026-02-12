package com.devlog.admin.dto.tag.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.jetbrains.annotations.UnknownNullability;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class TagCountListResDto {

    @Schema(description = "태그 갯수")
    private Integer count;

    @Schema(description = "태그 목록")
    private List<TagCountResDto> list;

    public static TagCountListResDto of(@UnknownNullability List<TagCountResDto> tags) {
        return TagCountListResDto.builder()
                .count(tags.size())
                .list(tags)
                .build();
    }

}
