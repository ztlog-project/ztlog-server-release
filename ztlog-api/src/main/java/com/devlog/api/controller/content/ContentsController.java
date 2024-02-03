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
@RequestMapping(value = "/api/v1/")
public class ContentsController {

    private final ContentService contentService;

    /**
     *
     * @param page
     * @return
     */
    @Operation(summary = "컨텐츠 목록 조회", description = "컨텐츠 목록 조회")
    @GetMapping(value = "/contents")
    public @ResponseBody Response getContentsListInfo(@RequestParam(value = "no", defaultValue = "1") Integer page) {
        return new Response(contentService.getContentsListInfo(page));
    }

    /**
     *
     * @param ctntNo
     * @return
     * @throws DataNotFoundException
     */
    @Operation(summary = "컨텐츠 상세 조회", description = "컨텐츠 상세 조회")
    @GetMapping(value = "/content/{ctntNo}")
    public @ResponseBody Response getContentInfo(@PathVariable Integer ctntNo) throws DataNotFoundException {
        return new Response(contentService.getContentInfo(ctntNo));
    }

    /**
     *
     * @param param
     * @param page
     * @return
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
