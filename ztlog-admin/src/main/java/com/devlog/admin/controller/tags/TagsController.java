package com.devlog.admin.controller.tags;

import com.devlog.admin.dto.tag.request.TagsInfoReqDto;
import com.devlog.admin.service.tags.TagsService;
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
     * 태그 목록 조회하기
     *
     * @return 태그 리스트
     */
    @Operation(summary = "태그 목록 조회", description = "태그 목록 조회")
    @GetMapping(value = "/tags")
    public @ResponseBody Response getTagsList() {
        return new Response(tagsService.getTagsList());
    }

    /**
     * 태그 생성하기
     *
     * @param reqDto 태그 정보 객체
     * @return 성공 응답
     */
    @PostMapping(value = "/tag")
    public @ResponseBody Response createTagsInfo(@RequestBody TagsInfoReqDto reqDto) {
        this.tagsService.createTagsInfo(reqDto);
        return new Response();
    }

    /**
     * 태그 수정하기
     *
     * @param reqDto 태그 정보 객체
     * @return 성공 응답
     */
    @PutMapping(value = "/tag")
    public @ResponseBody Response updateTagsInfo(@RequestBody TagsInfoReqDto reqDto) {
        this.tagsService.updateTagsInfo(reqDto);
        return new Response();
    }

    /**
     * 태그 삭제하기
     *
     * @param tagsNo 태그 번호
     * @return 성공 응답
     */
    @DeleteMapping(value = "/tags/{tagsNo}")
    public @ResponseBody Response deleteTagsInfo(@PathVariable Long tagsNo) {
        this.tagsService.deleteTagsInfo(tagsNo);
        return new Response();
    }

}
