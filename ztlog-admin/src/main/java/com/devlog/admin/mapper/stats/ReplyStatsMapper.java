package com.devlog.admin.mapper.stats;

import com.devlog.admin.dto.stats.response.GiscusResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyStatsMapper {

    // 특정 컨텐츠의 댓글수 강제 동기화 (외부 라이브러리 수치 반영)
    Integer updateReplyCount(@Param("ctntNo") Long ctntNo, @Param("replyCnt") int replyCnt);

    void upsertReplyCount(Long ctntNo, int replyCnt);

    // 단건 업데이트
    void updateCommentCount(@Param("postId") Long postId, @Param("commentCount") int commentCount);

    // (선택) 성능을 위한 벌크 업데이트
    void updateCommentCountsBatch(@Param("list") List<GiscusResponse.Node> nodes);
}
