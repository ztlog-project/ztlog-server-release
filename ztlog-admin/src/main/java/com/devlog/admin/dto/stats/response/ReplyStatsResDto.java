package com.devlog.admin.dto.stats.response;

import lombok.*;

@Getter
@NoArgsConstructor // 추가
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class ReplyStatsResDto {
    private Long ctntNo;
    private Integer replyCnt;

    public static ReplyStatsResDto of(Long ctntNo, Integer replyCnt) {
        return ReplyStatsResDto.builder().
                ctntNo(ctntNo)
                .replyCnt(replyCnt)
                .build();
    }
}
