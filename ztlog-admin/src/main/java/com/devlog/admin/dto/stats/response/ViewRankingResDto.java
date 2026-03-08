package com.devlog.admin.dto.stats.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ViewRankingResDto {

    private int rankNo;         // 순위
    private Long ctntNo;        // 컨텐츠 번호
    private String ctntTitle;   // 컨텐츠 제목
    private long viewCnt;       // 누적 조회수
}
