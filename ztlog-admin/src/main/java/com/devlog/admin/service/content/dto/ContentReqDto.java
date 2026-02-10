package com.devlog.admin.service.content.dto;

import com.devlog.admin.service.tag.dto.TagReqDto;
import com.devlog.core.common.constants.CommonConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


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

    @Schema(description = "게시물 내용")
    private String body;

    @Schema(description = "게시물 경로")
    private String path;

    @Schema(description = "게시물 이름")
    private String name;

    @Schema(description = "게시물 확장자")
    private String ext;

    @Schema(description = "게시물 생성자", defaultValue = CommonConstants.ADMIN_NAME)
    private String inpUser;

    @Schema(description = "게시물 태그 번호 목록")
    List<TagReqDto> tags;

}
