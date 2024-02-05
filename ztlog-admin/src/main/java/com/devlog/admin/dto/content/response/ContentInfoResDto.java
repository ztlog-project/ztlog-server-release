package com.devlog.admin.dto.content.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class ContentInfoResDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 278168601438426731L;

    private Long ctntNo;

    private String ctntTitle;

    private String ctntBody;

    private String ctntPath;

    private String ctntName;

    private String ctntExt;

    private String inpUser;

    private String inpDttm;

    private String updDttm;


}
