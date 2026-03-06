package com.devlog.admin.mapper.stats;

import com.devlog.admin.dto.stats.response.GiscusResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyStatsMapper {

    boolean existReplyStats(Long ctntNo);

    Integer upsertReplyCount(@Param("ctntNo") Long ctntNo, @Param("replyCnt") int replyCnt);

    void updateReplyCount(Long ctntNo, int replyCnt);

    void updateCommentCountsBatch(@Param("list") List<GiscusResponse.Node> nodes);  // (선택) 성능을 위한 벌크 업데이트
}
