package com.devlog.admin.service.stats.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MainStatsReqsDto {
    private long totalPostCount;
    private long totalTagCount;
    private long totalViewCount;
    private long totalCommentCount;
}
