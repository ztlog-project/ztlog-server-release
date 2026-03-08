package com.devlog.admin.service.stats;

import com.devlog.admin.component.GiscusComponent;
import com.devlog.admin.dto.stats.request.CommentStatsReqDto;
import com.devlog.admin.dto.stats.response.CommentStatsResDto;
import com.devlog.admin.dto.stats.response.GiscusDataResDto;
import com.devlog.admin.mapper.stats.CommentStatsMapper;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.config.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentStatsService {

    private final CommentStatsMapper commentStatsMapper;
    private final GiscusComponent giscusComponent;

    public void syncCommentStats(CommentStatsReqDto reqDto) {
        // Giscus(GitHub) API를 통해 실시간 댓글 수 수집 -> ctntNo를 기반으로 매핑된 Discussion 정보 사용
        GiscusDataResDto.Node node = giscusComponent.fetchCommentCount(reqDto.getCtntNo());
        // null check
        if (Optional.ofNullable(node).isEmpty()) {
            throw new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage());
        }
        // 통계 테이블 업데이트 (Upsert)
        commentStatsMapper.upsertCommentCount(reqDto.getCtntNo(), node.getComments().getTotalCount());

    }

    public void syncAllCommentStats() {
        List<GiscusDataResDto.Node> nodes = giscusComponent.fetchAllCommentCount();
        // 스트림 안에서 변환과 필터링을 한꺼번에 처리 (가장 권장되는 방식)
        List<CommentStatsReqDto> nodeList = nodes.stream()
                .map(CommentStatsReqDto::convertToDto)
                .flatMap(Optional::stream) // ID 추출 성공한 것만 통과
                .toList();
        // 통계 테이블 업데이트 (Upsert)
        nodeList.forEach(dto -> commentStatsMapper.upsertCommentCount(dto.getCtntNo(), dto.getCommentCnt()));
    }

    /**
     * 현재 댓글 통계 요약 조회
     */
    @Transactional(readOnly = true)
    public CommentStatsResDto getCommentStatsSummary() {
        return commentStatsMapper.selectCommentStatsSummary()
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage()));
    }
}
