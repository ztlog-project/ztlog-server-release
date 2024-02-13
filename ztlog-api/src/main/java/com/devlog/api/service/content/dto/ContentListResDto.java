package com.devlog.api.service.content.dto;

import com.devlog.api.service.tag.dto.TagResDto;
import com.devlog.core.common.constants.CommonConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class ContentListResDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7660421737047809693L;

    @Schema(description = "게시물 갯수")
    private Integer count;

    @Schema(description = "게시물 목록")
    private List<ContentMainDto> list;

    @Getter
    @Setter
    @ToString
    @Builder
    public static class ContentMainDto {

        @Schema(description = "게시물 번호")
        private Long ctntNo;

        @Schema(description = "게시물 제목")
        @Size(max = CommonConstants.CONTENT_TITLE_SIZE, message = "content title length is too long!!")
        private String ctntTitle;

        @Schema(description = "게시물 부제목")
        @Size(max = CommonConstants.CONTENT_SUBTITLE_SIZE, message = "content title length is too long!!")
        private String ctntSubTitle;

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

}
