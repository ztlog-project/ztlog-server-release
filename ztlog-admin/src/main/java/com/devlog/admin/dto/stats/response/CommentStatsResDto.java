package com.devlog.admin.dto.stats.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentStatsResDto {

    private long totalCommentCnt;   // 전체 댓글 수 합계
    private int maxCommentCnt;      // 단일 컨텐츠 최다 댓글 수
    private int contentCount;       // 댓글이 존재하는 컨텐츠 수
}
