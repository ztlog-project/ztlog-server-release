package com.devlog.admin.scheduler;

import com.devlog.admin.service.stats.CommentStatsService;
import com.devlog.admin.service.stats.ViewStatsService;
import com.devlog.core.common.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatsScheduler {

    private final ViewStatsService viewStatsService;
    private final CommentStatsService commentStatsService;

    /**
     * 매일 새벽 2시 - 전날 일별 조회수 통계 수집
     */
    @Scheduled(cron = "0 0 2 * * *", zone = "Asia/Seoul")
    public void collectDailyGrowthStats() {
        log.info("[Scheduler] 일별 조회수 통계 수집 시작");
        viewStatsService.collectDailyGrowthStats();
        log.info("[Scheduler] 일별 조회수 통계 수집 완료");
    }

    /**
     * 매일 새벽 3시 - 전날 댓글 수 전체 동기화
     */
    @Scheduled(cron = "0 0 3 * * *", zone = "Asia/Seoul")
    public void syncAllCommentStats() {
        log.info("[Scheduler] 댓글 수 동기화 시작");
        commentStatsService.syncAllCommentStats();
        log.info("[Scheduler] 댓글 수 동기화 완료");
    }

    /**
     * 매주 월요일 새벽 4시 - 전주 로우 데이터 배치 적재
     */
    @Scheduled(cron = "0 0 4 * * MON", zone = "Asia/Seoul")
    public void collectWeeklyViewRawLogs() {
        String endDate = DateUtils.dateToString(DateUtils.yesterdayLocalDate());
        String startDate = DateUtils.dateToString(DateUtils.yesterdayLocalDate().minusDays(6));
        log.info("[Scheduler] 조회수 로우 데이터 적재 시작: {} ~ {}", startDate, endDate);
        viewStatsService.collectViewRawLogs(startDate, endDate);
        log.info("[Scheduler] 조회수 로우 데이터 적재 완료");
    }

}
