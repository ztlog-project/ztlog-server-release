package com.devlog.admin.service.content.dto;

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
public class ContentReqDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 9080199739318840233L;

    private Long ctntNo;

    private String ctntTitle;

    private String ctntBody;

    private String ctntPath;

    private String ctntName;

    private String ctntExt;

    private String inpUser;

}
