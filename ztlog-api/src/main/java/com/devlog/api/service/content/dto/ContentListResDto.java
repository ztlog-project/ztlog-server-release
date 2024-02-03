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
public class ContentListResDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7660421737047809693L;

    private Long count;
    private List<ContentMainDto> list;

    public ContentListResDto(List<ContentMainDto> list, Long count) {
        this.count = count;
        this.list = list;
    }

    @Getter
    @Setter
    @ToString
    @Builder
    public static class ContentMainDto {

        private Long ctntNo;
        private String ctntTitle;
        private String ctntSubTitle;
        private String inpUser;
        private String inpDttm;
        private String updDttm;
        private List<String> tags;
    }

}
