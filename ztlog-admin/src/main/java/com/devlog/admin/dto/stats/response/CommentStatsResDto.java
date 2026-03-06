package com.devlog.admin.dto.stats.response;

import lombok.*;

@Getter
@NoArgsConstructor // 추가
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class CommentStatsResDto {
    private Long ctntNo;
    private Integer replyCnt;

    public static CommentStatsResDto of(Long ctntNo, Integer replyCnt) {
        return CommentStatsResDto.builder().
                ctntNo(ctntNo)
                .replyCnt(replyCnt)
                .build();
    }
}
