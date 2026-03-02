package com.devlog.admin.service.stats;

import com.devlog.admin.mapper.stats.ReplyStatsMapper;
import com.devlog.core.common.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReplyStatsSummaryService {

    private final ReplyStatsMapper replyStatsMapper;

    private final DateUtils dateUtils;

    /**
     * 전체 컨텐츠의 댓글수를 외부 서비스와 동기화하여 집계
     */
//    @Scheduled(cron = "0 0 2 * * *")
//    public void summarizeReplyStats() {
//        // 1. 동기화가 필요한 컨텐츠 리스트 조회 (필요 시)
//        // 2. 외부 API를 통해 각 컨텐츠의 최신 댓글 수 획득
//        // 3. DB 업데이트
//
//        // 예시: 특정 로직에 의해 추출된 ctntNo 리스트를 순회
//        for (Long ctntNo : targetCtntNos) {
//            int currentReplyCount = replyApiClient.getReplyCount(ctntNo);
//            replyStatsMapper.upsertReplyCount(ctntNo, currentReplyCount);
//        }
//    }

//    /**
//     * 특정 컨텐츠의 댓글수만 실시간 동기화 (Webhook 또는 수동 호출용)
//     */
//    @Transactional
//    public void summarizeReplyStatsByContent(Long ctntNo, int replyCnt) {
//        replyStatsMapper.upsertReplyCount(ctntNo, replyCnt);
//    }

    // @Scheduled(cron = "0 0 2 * * *")
    public void saveReplyStatsSummaryByContent(Long ctntNo, int replyCnt) {
        replyStatsMapper.upsertReplyCount(ctntNo, replyCnt);

    }

}
