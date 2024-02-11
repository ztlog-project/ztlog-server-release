package com.devlog.api.service.content.dto;

import com.devlog.api.service.tag.dto.TagResDto;
import com.devlog.core.common.constants.CommonConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
public class ContentListResDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7660421737047809693L;

    private Integer count;
    private List<ContentMainDto> list;

    public ContentListResDto(List<ContentMainDto> list, Integer count) {
        this.list = list;
        this.count = count;
    }

    @Getter
    @Setter
    @ToString
    @Builder
    public static class ContentMainDto {

        private Long ctntNo;

        private String ctntTitle;

        private String ctntSubTitle;

        private String inpUser;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
        private LocalDateTime inpDttm;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
        private LocalDateTime updDttm;

        private List<TagResDto> tags;

    }

}
