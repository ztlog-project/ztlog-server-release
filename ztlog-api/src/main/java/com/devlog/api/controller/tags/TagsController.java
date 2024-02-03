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
@RequestMapping(value = "/api/v1")
public class TagsController {

    private final TagsService tagsService;

    /**
     *
     * @return
     */
    @Operation(summary = "태그 목록 조회", description = "태그 목록 조회")
    @GetMapping(value = "/tags")
    public @ResponseBody Response getTagsListInfo() {
        return new Response(tagsService.getTagsListInfo());
    }

    /**
     *
     * @param tagNo
     * @return
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
