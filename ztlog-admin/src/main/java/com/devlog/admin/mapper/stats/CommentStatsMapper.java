package com.devlog.admin.mapper.stats;

import com.devlog.admin.service.stats.dto.response.CommentStatsResDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface CommentStatsMapper {

    boolean existCommentStats(Long ctntNo);

    void upsertCommentCount(@Param("ctntNo") Long ctntNo, @Param("commentCnt") int commentCnt);

    void updateCommentCount(@Param("ctntNo") Long ctntNo, @Param("commentCnt") int commentCnt);

    Optional<CommentStatsResDto> selectCommentStatsSummary();
}
