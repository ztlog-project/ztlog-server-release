package com.devlog.admin.mapper.stats;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentStatsMapper {

    boolean existCommentStats(Long ctntNo);

    void upsertCommentCount(@Param("ctntNo") Long ctntNo, @Param("CommentCnt") int commentCnt);

    void updateCommentCount(Long ctntNo, int commentCnt);

}
