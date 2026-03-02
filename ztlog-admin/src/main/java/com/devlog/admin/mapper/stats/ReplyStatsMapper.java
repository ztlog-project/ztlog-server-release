package com.devlog.admin.mapper.stats;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReplyStatsMapper {

    // 특정 컨텐츠의 댓글수 강제 동기화 (외부 라이브러리 수치 반영)
    Integer updateReplyCount(@Param("ctntNo") Long ctntNo, @Param("replyCnt") int replyCnt);

    void upsertReplyCount(Long ctntNo, int replyCnt);
}
