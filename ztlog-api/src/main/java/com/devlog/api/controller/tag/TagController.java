package com.devlog.api.controller.tag;

import com.devlog.api.service.content.dto.ContentListResDto;
import com.devlog.api.service.tag.TagService;
import com.devlog.api.service.tag.dto.TagResDto;
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

import java.util.List;

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
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = TagResDto.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @GetMapping("/tags")
    public ResponseEntity<Response<List<TagResDto>>> getTagList() {
        return Response.success(ResponseCode.OK_SUCCESS, tagService.getTagList());
    }

    /**
     * 태그 컨텐츠 목록 조회하기
     *
     * @param tagNo 태그 번호
     * @param page 페이지 번호 (기본값 = 1)
     * @return 태그 컨텐츠 리스트
     */
    @Operation(summary = "태그 컨텐츠 목록 조회", description = "태그 컨텐츠 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @GetMapping("/tags/{tagNo}")
    public ResponseEntity<Response<ContentListResDto>> getTagContentList(
            @PathVariable Integer tagNo,
            @RequestParam(value = "no", defaultValue = "1") Integer page
    ) {
        return Response.success(ResponseCode.OK_SUCCESS, tagService.getTagContentList(tagNo, page));
    }

}
