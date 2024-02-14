package com.devlog.admin.service.tags.dto;

import com.devlog.core.common.constants.CommonConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class TagReqDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -4642091134040102457L;

    @Schema(description = "태그 번호")
    private Long tagNo;

    @Schema(description = "태그 이름")
    @NotNull(message = "tag name can not be null!!")
    @Size(max = CommonConstants.TAG_NAME_SIZE, message = "tag name length is too long!!")
    private String tagName;

}
