package com.devlog.api.service.tags.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@ToString
@Builder
public class TagListResDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4253972827045328359L;

    private Long tagNo;

    private String tagName;

    private Integer count;


}
