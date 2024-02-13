package com.devlog.api.controller.content;

import com.devlog.api.service.content.ContentService;
import com.devlog.api.service.content.dto.ContentResDto;
import com.devlog.api.service.content.dto.ContentListResDto;
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
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ContentListResDto.class))),
            @ApiResponse(responseCode = "500", description = "서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @GetMapping("/contents")
    public ResponseEntity<Response<ContentListResDto>> getContentsList(@RequestParam(value = "no", defaultValue = "1") Integer page) {
        return Response.success(ResponseCode.OK_SUCCESS, contentService.getContentsList(page));
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
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러가 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @GetMapping(value = "contents/{ctntNo}")
    public ResponseEntity<Response<ContentResDto>> getContentsDetail(@PathVariable Integer ctntNo) {
        return Response.success(ResponseCode.OK_SUCCESS, contentService.getContentsDetail(ctntNo));
    }

    /**
     * 컨텐츠 검색하기
     *
     * @param param 검색 키워드
     * @param page  페이지 번호 (기본값 = 1)
     * @return 검색한 키워드 관련 리스트 반환
     */
    @Operation(summary = "컨텐츠 검색", description = "컨텐츠 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ContentListResDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러가 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @GetMapping(value = "contents/search")
    public ResponseEntity<Response<ContentListResDto>> searchContentList(
            @RequestParam(value = "param") String param,
            @RequestParam(value = "no", defaultValue = "1") Integer page
    ) {
        return Response.success(ResponseCode.OK_SUCCESS, contentService.searchContentList(param, page));
    }

}
