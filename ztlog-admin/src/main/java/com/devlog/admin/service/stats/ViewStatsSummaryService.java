package com.devlog.admin.service.stats;

import com.devlog.admin.mapper.stats.ViewStatsMapper;
import com.devlog.core.common.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ViewStatsSummaryService {

    private final ViewStatsMapper viewStatsMapper;

    private final DateUtils dateUtils;

    // @Scheduled(cron = "0 0 2 * * *")
    public void saveDailyViewStatsSummary(LocalDateTime dateTime) {

//        LocalDateTime dateTime = DateUtils.todayLocalDateTime();

        // 로그에서 컨텐츠별 조회수 집계 데이터 가져오기
        List<Map<String, Object>> counts = viewStatsMapper.selectDailyViewCounts(dateTime.toLocalDate());

        for (Map<String, Object> row : counts) {
            Long ctntNo = Long.valueOf(row.get("ctntNo").toString());
            int viewCnt = Integer.parseInt(row.get("viewCnt").toString());

            // 일별 통계 반영 (Upsert)
            viewStatsMapper.upsertDailyViewStats(dateTime.toLocalDate(), ctntNo, viewCnt);

            // 누적 통계 합산 반영 (Upsert)
            viewStatsMapper.updateCumulativeViewStats(ctntNo, viewCnt);
        }

    }
}
