package com.devlog.admin.dto.tag.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
public class TagsInfoResDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 8631335185381607696L;

    private Long tag_no;

    private String tagName;

    private Integer sort;

    private Long ctntNo;

}
