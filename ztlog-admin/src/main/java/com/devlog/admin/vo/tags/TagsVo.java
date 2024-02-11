package com.devlog.admin.vo.tags;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class TagsVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 6566928760535799829L;

    private Long tagNo;

    private String tagName;

    private Integer sort;

    private Long ctntNo;

    private Integer tagCnt;


}
