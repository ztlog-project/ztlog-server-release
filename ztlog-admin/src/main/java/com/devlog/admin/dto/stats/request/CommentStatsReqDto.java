package com.devlog.admin.dto.stats.request;

import lombok.*;

@Getter
@NoArgsConstructor // 추가
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class CommentStatsReqDto {
    private Long ctntNo;
    private Integer commentCnt;

    public static CommentStatsReqDto of(Long ctntNo, Integer commentCnt) {
        return CommentStatsReqDto.builder().
                ctntNo(ctntNo)
                .commentCnt(commentCnt)
                .build();
    }
}
