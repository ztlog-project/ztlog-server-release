package com.devlog.admin.service.stats;

import com.devlog.admin.component.GoogleSearchConsole;
import com.devlog.admin.dto.stats.request.ViewRawDataReqDto;
import com.devlog.admin.dto.stats.request.ViewStatsReqDto;
import com.devlog.admin.dto.stats.response.DailyStatsDto;
import com.devlog.admin.mapper.stats.ViewStatsMapper;
import com.devlog.core.common.utils.DateUtils;
import com.devlog.core.entity.content.Content;
import com.devlog.core.repository.content.ContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ViewStatsService {

    private final ViewStatsMapper viewStatsMapper;
    private final ContentRepository contentRepository;
    private final GoogleSearchConsole googleSearchConsole;

    /**
     * 1. 일별 통계 내역 기록 (구글 서치콘솔 조회수 get 후 save)
     */
    @Transactional
    public void collectDailyGrowthStats() {
        String today = DateUtils.todayLocalDateToString();

        googleSearchConsole.fetchAllPageViews(today, today)
                .stream()
                .filter(dto -> dto.getCtntNo() != null)
                .forEach(viewStatsMapper::upsertDailyViewStats);
    }

    /**
     * 3. 게시물 누적 조회수 업데이트 (작성일~오늘 전체 클릭수 SET)
     */
    @Transactional
    public void syncTotalViews(ViewStatsReqDto reqDto) {
        Content content = contentRepository.findById(reqDto.getCtntNo())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 컨텐츠입니다."));

        String startDate = DateUtils.dateToString(content.getInpDttm().toLocalDate());
        String endDate = DateUtils.todayLocalDateToString();

        long totalViewCnt = googleSearchConsole.fetchContentViews(startDate, endDate, reqDto.getCtntNo())
                .stream()
                .mapToLong(DailyStatsDto::getViewCnt)
                .sum();

        viewStatsMapper.updateCumulativeViewStats(reqDto.getCtntNo(), totalViewCnt);
    }

    @Transactional
    public void collectViewRawLogs(String startDate, String endDate) {
        List<ViewRawDataReqDto> rawLogs = googleSearchConsole.fetchRawLogData(startDate, endDate);
        if (!rawLogs.isEmpty()) {
            viewStatsMapper.insertViewRawLogs(rawLogs);
        }
    }

}
