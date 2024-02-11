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
@Builder
public class ContentResDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -960846485183605173L;

    private Long ctntNo;

    private String ctntTitle;

    private String ctntBody;

    private String ctntPath;

    private String ctntName;

    private String ctntExt;

    private String inpUser;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
    private LocalDateTime inpDttm;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DEFAULT_DATETIME_FORMAT, timezone = "Asia/Seoul")
    private LocalDateTime updDttm;

    private List<TagResDto> tags;
}
