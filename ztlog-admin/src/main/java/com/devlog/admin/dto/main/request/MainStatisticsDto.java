package com.devlog.admin.dto.main.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainStatisticsDto {
    private long totalPostCount;
    private long totalTagCount;
    private long totalViewCount;
    private long totalCommentCount;
}
