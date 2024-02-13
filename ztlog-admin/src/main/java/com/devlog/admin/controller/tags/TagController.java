package com.devlog.admin.controller.tags;

import com.devlog.admin.service.tags.dto.TagReqDto;
import com.devlog.admin.service.tags.dto.TagListResDto;
import com.devlog.admin.service.tags.TagService;
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


@Tag(name = "태그 컨트롤러", description = "태그 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class TagController {

    private final TagService tagService;

    /**
     * 태그 목록 조회하기
     *
     * @return 태그 리스트
     */
    @Operation(summary = "태그 목록 조회", description = "태그 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = TagListResDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @GetMapping(value = "/tags")
    public ResponseEntity<Response<TagListResDto>> getTagList(@RequestParam(value = "no", defaultValue = "1") Integer page) {
        return Response.success(ResponseCode.OK_SUCCESS, tagService.getTagList(page));
    }

    /**
     * 태그 등록/수정하기
     *
     * @param reqDto 태그 정보 객체
     * @return 성공 응답
     */
    @Operation(summary = "태그 등록/수정", description = "태그 등록/수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @PostMapping(value = "/tag")
    public ResponseEntity<Response<String>> saveTagDetail(@RequestBody TagReqDto reqDto) {
        tagService.saveTagDetail(reqDto);
        return Response.success(ResponseCode.OK_SUCCESS);
    }

    /**
     * 태그 삭제하기
     *
     * @param tagsNo 태그 번호
     * @return 성공 응답
     */
    @Operation(summary = "태그 삭제", description = "태그 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @DeleteMapping(value = "/tags/{tagsNo}")
    public ResponseEntity<Response<String>> deleteTagDetail(@PathVariable Long tagsNo) {
        tagService.deleteTagDetail(tagsNo);
        return Response.success(ResponseCode.OK_SUCCESS);
    }

}
