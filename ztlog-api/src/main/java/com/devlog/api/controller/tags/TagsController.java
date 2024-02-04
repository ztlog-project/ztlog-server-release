package com.devlog.api.controller.tags;

import com.devlog.api.service.tags.TagsService;
import com.devlog.core.common.vo.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "태그 컨트롤러", description = "태그 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class TagsController {

    private final TagsService tagsService;

    /**
     * 태그 목록 조회하기
     *
     * @return 태그 리스트
     */
    @Operation(summary = "태그 목록 조회", description = "태그 목록 조회")
    @GetMapping(value = "/tags")
    public @ResponseBody Response getTagsListInfo() {
        return new Response(tagsService.getTagsListInfo());
    }

    /**
     * 태그 게시물 목록 모회하기
     *
     * @param tagNo 태그 번호
     * @param page 페이지 번호 (기본값 = 1)
     * @return 태그 게시물 리스트
     */
    @Operation(summary = "태그 게시물 목록 조회", description = "태그로 게시물 목록 조회")
    @GetMapping(value = "/tags/{tagNo}")
    public @ResponseBody Response getTagsContentsListInfo(
            @PathVariable Integer tagNo,
            @RequestParam(value = "no", defaultValue = "1") Integer page
    ) {
        return new Response(tagsService.getTagsContentsListInfo(tagNo, page));
    }


}
