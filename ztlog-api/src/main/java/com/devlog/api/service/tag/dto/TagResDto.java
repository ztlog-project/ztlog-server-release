package com.devlog.api.service.tag.dto;

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
public class TagResDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1686790962136143267L;

    private Long tagNo;

    private String tagName;

}
