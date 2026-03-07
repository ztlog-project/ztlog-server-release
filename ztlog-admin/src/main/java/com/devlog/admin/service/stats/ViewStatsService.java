package com.devlog.admin.service.stats;

import com.devlog.admin.dto.stats.request.DailyStatsReqDto;
import com.devlog.admin.dto.stats.request.ViewStatsReqDto;
import com.devlog.admin.dto.stats.response.DailyStatsDto;
import com.devlog.admin.dto.stats.response.TotalViewStatsDto;
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
import jakarta.validation.Valid;
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
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ViewStatsService {

    private final ViewStatsMapper viewStatsMapper;

    private final DateUtils dateUtils;

    @Value("${google.search-console.site-url}")
    private String siteUrl;

    public void syncTotalViews(ViewStatsReqDto reqDto) {

    }

    public void collectDailyGrowthStats(DailyStatsReqDto reqDto) {
        try (InputStream keyFile = getClass().getResourceAsStream("/google-search-console-key.json")) {
            String startDate = reqDto.getStartDate();
            String endDate = reqDto.getEndDate();


            // 1. 키 파일 존재 확인
            if (keyFile == null) {
                throw new RuntimeException("구글 서비스 키 파일을 찾을 수 없습니다.");
            }

            // 2. 인증 및 서비스 빌드
            GoogleCredentials credentials = GoogleCredentials.fromStream(keyFile)
                    .createScoped(Collections.singleton(SearchConsoleScopes.WEBMASTERS_READONLY));

            SearchConsole service = new SearchConsole.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    GsonFactory.getDefaultInstance(),
                    new HttpCredentialsAdapter(credentials))
                    .setApplicationName("ztlog-admin-app")
                    .build();

            // 3. 쿼리 요청 설정
            SearchAnalyticsQueryRequest request = new SearchAnalyticsQueryRequest()
                    .setStartDate(startDate)
                    .setEndDate(endDate)
                    .setDimensions(List.of("page"))
                    .setRowLimit(100);

            // 4. API 실행 및 결과 반환
            SearchAnalyticsQueryResponse response = service.searchanalytics()
                    .query(siteUrl, request)
                    .execute();

            // Optional로 null 처리와 변환을 한 번에!
            List<DailyStatsDto> list =  Optional.ofNullable(response.getRows())
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(DailyStatsDto::of)
                    .toList();

            saveDailyViewStatsSummary(DateUtils.toLocalDateTime(endDate));

        } catch (GoogleJsonResponseException e) {
            // 구글 API 응답 에러 (권한 부족, 할당량 초과 등)
            throw new RuntimeException("Google API 응답 실패: " + e.getDetails().getMessage(), e);
        } catch (IOException | GeneralSecurityException e) {
            // 네트워크 연결 및 인증서 관련 시스템 에러
            throw new RuntimeException("통계 데이터 수집 중 시스템 오류 발생", e);
        }
    }

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
}
