package com.devlog.admin.service.content.dto;

import com.devlog.admin.service.tags.dto.TagResDto;
import com.devlog.core.common.constants.CommonConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ContentResDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 278168601438426731L;

    @Schema(description = "게시물 번호")
    private Long ctntNo;

    @Schema(description = "게시물 제목")
    @Size(max = CommonConstants.CONTENT_TITLE_SIZE, message = "content title length is too long!!")
    private String ctntTitle;

    @Schema(description = "게시물 부제목")
    @Size(max = CommonConstants.CONTENT_SUBTITLE_SIZE, message = "content title length is too long!!")
    private String ctntSubTitle;

    @Schema(description = "게시물 내용")
    private String ctntBody;

    @Schema(description = "게시물 경로")
    private String ctntPath;

    @Schema(description = "게시물 이름")
    private String ctntName;

    @Schema(description = "게시물 파일 확장자")
    private String ctntExt;

    @Schema(description = "게시물 생성자", defaultValue = CommonConstants.ADMIN_NAME)
    private String inpUser;

    @Schema(description = "게시물 생성일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
    private LocalDateTime inpDttm;

    @Schema(description = "게시물 수정일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
    private LocalDateTime updDttm;

    @Schema(description = "게시물 태그 목록")
    private List<TagResDto> tags;


}
