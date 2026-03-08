package com.devlog.admin.controller.stats;

import com.devlog.admin.dto.stats.response.CommentStatsResDto;
import com.devlog.admin.dto.stats.response.DailyGrowthResDto;
import com.devlog.admin.dto.stats.response.ViewRankingResDto;
import com.devlog.admin.service.stats.CommentStatsService;
import com.devlog.admin.service.stats.ViewStatsService;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.common.dto.Response;
import com.devlog.core.common.enumulation.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "통계 데이터 조회", description = "통계 데이터 조회 API 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stats")
public class StatsViewController {

    private final ViewStatsService viewStatsService;
    private final CommentStatsService commentStatsService;

    /**
     * 일별 성장 통계 조회 (조회수, 댓글수 증가량 추이)
     * 수집된 collectDailyGrowthStats 데이터를 시각화하기 위한 API
     */
    @Operation(summary = "일별 성장 통계 조회", description = "특정 기간 동안의 일별 조회수/댓글수 증가량 추이 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @GetMapping("/daily-growth")
    public ResponseEntity<Response<List<DailyGrowthResDto>>> getDailyGrowthStats(
            @RequestParam @DateTimeFormat(pattern = CommonConstants.DEFAULT_DATE_FORMAT) LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = CommonConstants.DEFAULT_DATE_FORMAT) LocalDate endDate) {
        // service에서 해당 기간의 통계 데이터를 조회하는 로직 호출
        return Response.success(ResponseCode.OK_SUCCESS, viewStatsService.getDailyGrowthStats(startDate, endDate));
    }

    /**
     * 실시간 댓글 통계 현황 조회
     * syncCommentStats에 의해 업데이트된 현재 통계 상태 확인
     */
    @Operation(summary = "실시간 댓글 통계 조회", description = "현재 기준 전체 댓글 통계 현황 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @GetMapping("/comments/current")
    public ResponseEntity<Response<CommentStatsResDto>> getCurrentCommentStats() {
        return Response.success(ResponseCode.OK_SUCCESS, commentStatsService.getCommentStatsSummary());
    }

    /**
     * 누적 및 기간별 조회수 랭킹/현황 조회
     * syncTotalViews로 합산된 데이터를 기반으로 리스트 제공
     */
    @Operation(summary = "조회수 통계 현황 조회", description = "누적 조회수 및 인기 콘텐츠 순위 데이터 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @GetMapping("/views/ranking")
    public ResponseEntity<Response<List<ViewRankingResDto>>> getViewRankingStats() {
        return Response.success(ResponseCode.OK_SUCCESS, viewStatsService.getViewRankingStats());
    }
}
