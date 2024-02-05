package com.devlog.admin.vo.content;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class ContentVo implements Serializable {

    @Serial
    private static final long serialVersionUID = -4517988092635410128L;

    private Long ctntNo;

    private String ctntTitle;

    private String ctntSubTitle;

    private String ctntBody;

    private String ctntPath;

    private String ctntName;

    private String ctntExt;

    private String inpUser;

    private String inpDttm;

    private String updDttm;

}
