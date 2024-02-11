package com.devlog.admin.controller.tags;

import com.devlog.admin.dto.content.response.ContentListResDto;
import com.devlog.admin.dto.tag.request.TagsInfoReqDto;
import com.devlog.admin.dto.tag.response.TagsInfoResDto;
import com.devlog.admin.service.tags.TagsService;
import com.devlog.core.common.enumulation.ResponseStatusCode;
import com.devlog.core.common.vo.Response;
import com.devlog.core.config.exception.CoreException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "태그 컨트롤러", description = "태그 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class TagsController {

    private final TagsService tagsService;

    /**
     * 태그 목록 조회하기
     *
     * @return 태그 리스트
     */
    @Operation(summary = "태그 목록 조회", description = "태그 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = TagsInfoResDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseStatusCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러가 발생", content = @Content(schema = @Schema(implementation = ResponseStatusCode.class)))
    })
    @GetMapping(value = "/tags")
    public ResponseEntity<Response<List<TagsInfoResDto>>> getTagsList() {
        return Response.success(ResponseStatusCode.OK_SUCCESS, tagsService.getTagsList());
    }

    /**
     * 태그 생성하기
     *
     * @param reqDto 태그 정보 객체
     * @return 성공 응답
     */
    @Operation(summary = "태그 등록", description = "태그 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseStatusCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러가 발생", content = @Content(schema = @Schema(implementation = ResponseStatusCode.class)))
    })
    @PostMapping(value = "/tag")
    public ResponseEntity<Response<String>> createTagsInfo(@RequestBody TagsInfoReqDto reqDto) {
        tagsService.createTagsInfo(reqDto);
        return Response.success(ResponseStatusCode.CREATED_SUCCESS);
    }

    /**
     * 태그 수정하기
     *
     * @param reqDto 태그 정보 객체
     * @return 성공 응답
     */
    @Operation(summary = "태그 수정", description = "태그 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseStatusCode.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseStatusCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러가 발생", content = @Content(schema = @Schema(implementation = ResponseStatusCode.class)))
    })
    @PutMapping(value = "/tag")
    public ResponseEntity<Response<String>> updateTagsInfo(@RequestBody TagsInfoReqDto reqDto) {
        tagsService.updateTagsInfo(reqDto);
        return Response.success(ResponseStatusCode.OK_SUCCESS);
    }

    /**
     * 태그 삭제하기
     *
     * @param tagsNo 태그 번호
     * @return 성공 응답
     */
    @Operation(summary = "태그 삭제", description = "태그 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseStatusCode.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseStatusCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러가 발생", content = @Content(schema = @Schema(implementation = ResponseStatusCode.class)))
    })
    @DeleteMapping(value = "/tags/{tagsNo}")
    public ResponseEntity<Response<String>> deleteTagsInfo(@PathVariable Long tagsNo) {
        tagsService.deleteTagsInfo(tagsNo);
        return Response.success(ResponseStatusCode.OK_SUCCESS);
    }

}
