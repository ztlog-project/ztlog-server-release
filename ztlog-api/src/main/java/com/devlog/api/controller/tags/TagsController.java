package com.devlog.api.controller.tags;

import com.devlog.api.service.tags.TagsService;
import com.devlog.core.common.vo.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "태그 컨트롤러", description = "태그 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/tags")
public class TagsController {

    private final TagsService tagsService;

    @Operation(summary = "태그 목록 조회", description = "태그 목록 조회")
    @GetMapping(value = "/test")
    public @ResponseBody Response test() {
        return new Response("hello ztlog test!!");
    }


}
