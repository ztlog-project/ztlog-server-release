package com.devlog.admin.mapper.stats;

import com.devlog.admin.dto.stats.response.GiscusResDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentStatsMapper {

    boolean existCommentStats(Long ctntNo);

    Integer upsertCommentCount(@Param("ctntNo") Long ctntNo, @Param("CommentCnt") int commentCnt);

    void updateCommentCount(Long ctntNo, int commentCnt);

    void updateCommentCountsBatch(@Param("list") List<GiscusResDto.Node> nodes);  // (선택) 성능을 위한 벌크 업데이트
}
