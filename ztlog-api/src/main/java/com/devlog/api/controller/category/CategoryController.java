package com.devlog.api.controller.category;

import com.devlog.api.service.category.CategoryService;
import com.devlog.api.service.category.dto.CategoryResDto;
import com.devlog.api.service.content.dto.ContentListResDto;
import com.devlog.core.common.dto.Response;
import com.devlog.core.common.enumulation.ResponseCode;
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

@Tag(name = "카테고리 컨트롤러", description = "카테고리 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 카테고리 목록 조회하기
     *
     * @return 카테고리 리스트
     */
    @Operation(summary = "카테고리 목록 조회", description = "카테고리 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ContentListResDto.class))),
            @ApiResponse(responseCode = "500", description = "서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @GetMapping("/categories")
    public ResponseEntity<Response<List<CategoryResDto>>> getCategoryList() {
        return Response.success(ResponseCode.OK_SUCCESS, categoryService.getCategoryList());
    }

    /**
     * 카테고리 컨텐츠 목록 조회하기
     *
     * @param cateNo 카테고리 번호
     * @param page   페이지 번호 (기본값 = 1)
     * @return 카테고리 컨텐츠 리스트
     */
    @Operation(summary = "태그 컨텐츠 목록 조회", description = "태그 컨텐츠 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseCode.class))),
            @ApiResponse(responseCode = "500", description = "예상치 못한 서버 에러 발생", content = @Content(schema = @Schema(implementation = ResponseCode.class)))
    })
    @GetMapping("/categories/{cateNo}")
    public ResponseEntity<Response<ContentListResDto>> getTagContentList(
            @PathVariable Integer cateNo,
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ) {
        return Response.success(ResponseCode.OK_SUCCESS, categoryService.getCategoryContentList(cateNo, page));
    }
}
