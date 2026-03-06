package com.devlog.admin.controller.stats;

import com.devlog.admin.dto.stats.response.CommentStatsResDto;
import com.devlog.admin.dto.stats.response.DailyStatsDto;
import com.devlog.admin.dto.stats.response.TotalViewStatsResDto;
import com.devlog.admin.dto.stats.response.ViewRawDataDto;
import com.devlog.admin.service.stats.CommentStatsService;
import com.devlog.admin.service.stats.ViewLogService;
import com.devlog.admin.service.stats.ViewStatsService;
import com.devlog.core.common.dto.Response;
import com.devlog.core.common.enumulation.ResponseCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "통계 집계 실행", description = "통계 집계 수동 실행 및 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class StatsJobController {

    private final ViewLogService viewLogService;
    private final ViewStatsService viewStatsService;
    private final CommentStatsService commentStatsService;

//
//    @PostMapping("/stats/view")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
//            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
//            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
//    })
//    public ResponseEntity<Response<String>> processDailyViewStats(@RequestParam @DateTimeFormat(pattern = CommonConstants.DEFAULT_DATE_FORMAT) LocalDateTime date) {
//        viewStatsService.saveDailyViewStatsSummary(date);
//        return Response.success(ResponseCode.OK_SUCCESS, date + " - 조회수 집계 처리가 완료되었습니다.");
//    }
//
//    @PostMapping("/stats/reply")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
//            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
//            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
//    })
//    public ResponseEntity<Response<String>> processReplyStatsSync(@RequestParam Long ctntNo, @RequestParam Integer replyCnt) {
//        commentStatsService.saveReplyStatsSummaryByContent(ctntNo, replyCnt);
//        return Response.success(ResponseCode.OK_SUCCESS, "CTNT_NO : " + ctntNo + " - 댓글 수 동기화 처리가 완료되었습니다.");
//    }
//
//
//    @PostMapping("/stats/reply")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
//            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
//            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
//    })
//    public ResponseEntity<Response<String>> processReplyStatsSync() {
//        commentStatsService.syncAllComments();
//        return Response.success(ResponseCode.OK_SUCCESS, " 모든 댓글 수 동기화 처리가 완료되었습니다.");
//    }

    /**
     * 1. 실시간 댓글 통계 (즉시 업데이트된 데이터 조회)
     */
    @GetMapping("/stats/comments/summary")
    public ResponseEntity<Response<CommentStatsResDto>> getCommentSummary(@RequestParam(required = false) Long ctntNo) {
        return Response.success(ResponseCode.OK_SUCCESS, commentStatsService.getRealTimeCommentStats(ctntNo));
    }


    /**
     * 2. 누적 조회수 통계 (배치 프로그램에서 주기적으로 업데이트된 데이터)
     */
    @GetMapping("/stats/views/total")
    public ResponseEntity<Response<TotalViewStatsResDto>> getTotalViews(@RequestParam(required = false) Long ctntNo) {
        return Response.success(ResponseCode.OK_SUCCESS, viewStatsService.getBatchCumulativeViews(ctntNo));
    }

    /**
     * 3. 일별 통계 - 조회수, 댓글수 증가량 기록 (차트용 데이터)
     */
    @GetMapping("/stats/daily")
    public ResponseEntity<Response<List<DailyStatsDto>>> getDailyStats(@RequestParam String startDate, @RequestParam String endDate) {
        return Response.success(ResponseCode.OK_SUCCESS, viewStatsService.getDailyGrowthStats(startDate, endDate));
    }

    /**
     * 4. 조회수 수집 로우 데이터 (상세 분석용)
     */
    @GetMapping("/stats/views/raw")
    public ResponseEntity<Response<Page<ViewRawDataDto>>> getViewRawData(Pageable pageable) {
        return Response.success(ResponseCode.OK_SUCCESS, viewLogService.getViewRawDataList(pageable));
    }

}
