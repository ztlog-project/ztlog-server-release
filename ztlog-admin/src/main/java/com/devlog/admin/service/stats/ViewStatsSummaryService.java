package com.devlog.admin.service.stats;

import com.devlog.admin.mapper.stats.ViewStatsMapper;
import com.devlog.core.common.utils.DateUtils;
import com.devlog.core.config.exception.DataNotFoundException;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.searchconsole.v1.SearchConsole;
import com.google.api.services.searchconsole.v1.SearchConsoleScopes;
import com.google.api.services.searchconsole.v1.model.ApiDataRow;
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
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ViewStatsSummaryService {

    @Value("${google.search-console.site-url}")
    private String siteUrl;

    private final ViewStatsMapper viewStatsMapper;

    private final DateUtils dateUtils;

    // @Scheduled(cron = "0 0 2 * * *")
    public void saveDailyViewStatsSummary(LocalDateTime dateTime) {

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

    public List<ApiDataRow> getPagePerformance(String startDate, String endDate) throws Exception {
        // 1. 인증 정보 로드 (Try-with-resources로 안전하게 자원 해제)
        GoogleCredentials credentials;
        try (InputStream keyFile = getClass().getResourceAsStream("/google-search-console-key.json")) {
            if (keyFile == null) {
                throw new DataNotFoundException("Google Search Console API key file is missing in resources.");
            }

            credentials = GoogleCredentials.fromStream(keyFile)
                    .createScoped(Collections.singleton(SearchConsoleScopes.WEBMASTERS_READONLY));
        } catch (IOException e) {
            throw new DataNotFoundException("Failed to load Google Search Console API key:" + e.getMessage());
        }

        // 2. Search Console 서비스 빌드 (성능을 위해 Bean 등록이나 필드 추출 고려 가능)
        SearchConsole service = new SearchConsole.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                new HttpCredentialsAdapter(credentials))
                .setApplicationName("ztlog-admin-app")
                .build();

        // 3. 쿼리 요청 설정 (빌더 패턴 스타일로 가독성 향상)
        SearchAnalyticsQueryRequest request = new SearchAnalyticsQueryRequest()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setDimensions(List.of("page"))
                .setRowLimit(100);

        // 4. API 실행 및 결과 반환
        SearchAnalyticsQueryResponse response = service.searchanalytics()
                .query(siteUrl, request)
                .execute();

        // 결과가 null일 경우 빈 리스트 반환 (NullPointerException 방지)
        return response.getRows() != null ? response.getRows() : Collections.emptyList();
    }
}
