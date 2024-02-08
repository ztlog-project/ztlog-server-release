package com.devlog.admin.vo.content;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class ContentSearchVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 9081374746129824111L;

    private Integer page;

    private Integer size;

}
