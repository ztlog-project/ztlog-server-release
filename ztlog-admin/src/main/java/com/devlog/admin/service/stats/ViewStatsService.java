package com.devlog.admin.service.stats;

import com.devlog.admin.component.GoogleSearchConsole;
import com.devlog.admin.dto.stats.request.DailyStatsReqDto;
import com.devlog.admin.dto.stats.request.ViewStatsReqDto;
import com.devlog.admin.dto.stats.response.DailyStatsDto;
import com.devlog.admin.mapper.stats.ViewStatsMapper;
import com.devlog.core.common.utils.DateUtils;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.searchconsole.v1.SearchConsole;
import com.google.api.services.searchconsole.v1.SearchConsoleScopes;
import com.google.api.services.searchconsole.v1.model.SearchAnalyticsQueryRequest;
import com.google.api.services.searchconsole.v1.model.SearchAnalyticsQueryResponse;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ViewStatsService {

    private final ViewStatsMapper viewStatsMapper;
    private final GoogleSearchConsole googleSearchConsole;
    private final DateUtils dateUtils;

    /**
     * 1. 일별 통계 내역 기록 (조회수, 댓글수 증가량 get 후 save)
     */
    // @Scheduled(cron = "0 0 2 * * *")
    public void collectDailyGrowthStats(DailyStatsReqDto reqDto) {
        // [GET] 구글 서치콘솔에서 데이터 가져오기
        List<DailyStatsDto> googleData = googleSearchConsole.fetchGoogleSearchConsoleData(reqDto.getStartDate(), reqDto.getEndDate());

        // [SAVE] DB에 저장 (MyBatis Upsert)
        for (DailyStatsDto dto : googleData) {
            viewStatsMapper.upsertDailyViewStats(dto);
        }
    }

    /**
     * 3. 누적 조회수 통계 업데이트 (1시간 주기 합산 get 후 save)
     */
    // @Scheduled(cron = "0 0 2 * * *")
    @Transactional
    public void syncTotalViews(ViewStatsReqDto reqDto) {
        // [GET] 로우 데이터에서 총합 계산
        Long totalCount = viewStatsMapper.getTotalViewCountFromLog(reqDto.getCtntNo());
        // [SAVE] 누적 테이블 갱신
        viewStatsMapper.upsertTotalViewStats(reqDto.getCtntNo(), totalCount);
    }

}
