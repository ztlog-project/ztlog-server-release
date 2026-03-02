package com.devlog.admin.controller.stats;

import com.devlog.admin.service.stats.ReplyStatsSummaryService;
import com.devlog.admin.service.stats.ViewStatsSummaryService;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.common.enumulation.ResponseCode;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;

@Tag(name = "통계 집계 실행", description = "통계 집계 수동 실행 및 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class StatsJobController {

    private final ViewStatsSummaryService viewStatsSummaryService;
    private final ReplyStatsSummaryService replyStatsSummaryService;

    @PostMapping("/stats/view")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    public ResponseEntity<String> processDailyViewStats(@RequestParam @DateTimeFormat(pattern = CommonConstants.DEFAULT_DATE_FORMAT) LocalDateTime date) {
        viewStatsSummaryService.saveDailyViewStatsSummary(date);
        return ResponseEntity.ok(date + " - 조회수 집계 처리가 완료되었습니다.");
    }

    @PostMapping("/stats/reply")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    public ResponseEntity<String> processReplyStatsSync(@RequestParam Long ctntNo, @RequestParam Integer replyCnt) {
        replyStatsSummaryService.saveReplyStatsSummaryByContent(ctntNo, replyCnt);
        return ResponseEntity.ok("CTNT_NO : " + ctntNo + " - 댓글 수 동기화 처리가 완료되었습니다.");
    }
}
