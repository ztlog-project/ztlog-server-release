package com.devlog.admin.dto.content.response;

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
public class ContentListResDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -7045158107410991137L;

    private List<ContentInfoResDto> list;

}
