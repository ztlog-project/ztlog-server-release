package com.devlog.api.service.content.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
@Builder
public class ContentInfoResDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -960846485183605173L;

    private Long ctntNo;

    private String ctntTitle;

    private String ctntBody;

    private String ctntPath;

    private String ctntName;

    private String ctntExt;

    private String inpUser;

    private String inpDttm;

    private String updDttm;

    private List<String> tags;
}
