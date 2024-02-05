package com.devlog.api.controller.content;

import com.devlog.api.service.content.ContentService;
import com.devlog.core.common.vo.Response;
import com.devlog.core.config.exception.DataNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "컨텐츠 컨트롤러", description = "컨텐츠 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class ContentController {

    private final ContentService contentService;

    /**
     * 컨텐츠 목록 조회하기
     *
     * @param page 페이지 번호 (기본값 = 1)
     * @return 컨텐츠 리스트 반환
     */
    @Operation(summary = "컨텐츠 목록 조회", description = "컨텐츠 목록 조회")
    @GetMapping(value = "/contents")
    public @ResponseBody Response getContentsList(@RequestParam(value = "no", defaultValue = "1") Integer page) {
        return new Response(contentService.getContentsList(page));
    }

    /**
     * 컨텐츠 상세 조회하기
     *
     * @param ctntNo 컨텐츠 번호
     * @return 컨텐츠 반환
     * @throws DataNotFoundException 조회 오류 예외처리
     */
    @Operation(summary = "컨텐츠 상세 조회", description = "컨텐츠 상세 조회")
    @GetMapping(value = "/content/{ctntNo}")
    public @ResponseBody Response getContentInfo(@PathVariable Integer ctntNo) throws DataNotFoundException {
        return new Response(contentService.getContentInfo(ctntNo));
    }

    /**
     * 컨텐츠 검색하기
     *
     * @param param 검색 키워드
     * @param page 페이지 번호 (기본값 = 1)
     * @return 검색한 키워드 관련 리스트 반환
     */
    @Operation(summary = "컨텐츠 검색", description = "컨텐츠 검색")
    @GetMapping(value = "/search")
    public @ResponseBody Response searchContentsInfo(
            @RequestParam(value = "param") String param,
            @RequestParam(value = "no", defaultValue = "1") Integer page
    ) {
        return new Response(contentService.searchContentsInfo(param, page));
    }
}
