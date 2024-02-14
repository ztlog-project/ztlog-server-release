package com.devlog.admin.controller.content;

import com.devlog.admin.service.content.dto.ContentReqDto;
import com.devlog.admin.service.content.dto.ContentResDto;
import com.devlog.admin.service.content.dto.ContentListResDto;
import com.devlog.admin.service.content.ContentService;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "컨텐츠 컨트롤러", description = "컨텐츠 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class ContentController {

    private final ContentService contentService;

    /**
     * 컨텐츠 목록 조회하기
     *
     * @param page 페이지 번호 (기본값 = 1)
     * @return 컨텐츠 리스트 반환
     */
    @Operation(summary = "컨텐츠 목록 조회", description = "컨텐츠 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @GetMapping(value = "/contents")
    public ResponseEntity<Response<ContentListResDto>> getContentList(@RequestParam(value = "no", defaultValue = "1") Integer page) {
        return Response.success(ResponseCode.OK_SUCCESS, contentService.getContentList(page));
    }

    /**
     * 컨텐츠 상세 조회하기
     *
     * @param ctntNo 컨텐츠 번호
     * @return 컨텐츠 반환
     */
    @Operation(summary = "컨텐츠 상세 조회", description = "컨텐츠 상세 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @GetMapping(value = "/contents/{ctntNo}")
    public ResponseEntity<Response<ContentResDto>> getContentDetail(@PathVariable Long ctntNo) {
        return Response.success(ResponseCode.OK_SUCCESS, contentService.getContentDetail(ctntNo));
    }


    /**
     * 컨텐츠 등록하기
     *
     * @param reqDto 컨텐츠 객체
     * @return 성공 응답
     */
    @Operation(summary = "컨텐츠 등록", description = "컨텐츠 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @PostMapping(value = "/contents")
    public ResponseEntity<Response<String>> createContentDetail(@RequestBody ContentReqDto reqDto) {
        contentService.createContentDetail(reqDto);
        return Response.success(ResponseCode.CREATED_SUCCESS);
    }

    /**
     * 컨텐츠 수정하기
     *
     * @param reqDto 컨텐츠 객체
     * @return 성공 응답
     */
    @Operation(summary = "컨텐츠 수정", description = "컨텐츠 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @PutMapping(value = "/contents")
    public ResponseEntity<Response<String>> updateContentDetail(@RequestBody ContentReqDto reqDto) {
        contentService.updateContentDetail(reqDto);
        return Response.success(ResponseCode.CREATED_SUCCESS);
    }

    /**
     * 컨텐츠 삭제하기
     *
     * @param ctntNo 컨텐츠 번호
     * @return 성공 응답
     */
    @Operation(summary = "컨텐츠 삭제", description = "컨텐츠 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @DeleteMapping(value = "/contents/{ctntNo}")
    public ResponseEntity<Response<String>> deleteContentDetail(@PathVariable Long ctntNo) {
        contentService.deleteContentDetail(ctntNo);
        return Response.success(ResponseCode.OK_SUCCESS);
    }

}
