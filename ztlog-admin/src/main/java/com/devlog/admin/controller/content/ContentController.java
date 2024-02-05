package com.devlog.admin.controller.content;

import com.devlog.admin.dto.content.request.ContentInfoReqDto;
import com.devlog.admin.service.content.ContentService;
import com.devlog.core.common.vo.Response;
import com.devlog.core.config.exception.DataNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "컨텐츠 컨트롤러", description = "컨텐츠 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class ContentController {

    private final ContentService contentService;

    // TODO : Contents CRUD API

    /**
     * 컨텐츠 목록 조회하기
     *
     * @param page 페이지 번호 (기본값 = 1)
     * @return 컨텐츠 리스트 반환
     */
    @Operation(summary = "컨텐츠 목록 조회", description = "컨텐츠 목록 조회")
    @GetMapping(value = "/contents")
    public @ResponseBody Response getContentList(@RequestParam(value = "no", defaultValue = "1") Integer page) {
        return new Response(contentService.getContentList(page));
    }

    /**
     * 컨텐츠 상세 조회하기
     *
     * @param ctntNo 컨텐츠 번호
     * @return 컨텐츠 반환
     * @throws DataNotFoundException 조회 오류 예외처리
     */
    @GetMapping(value = "/content/{ctntNo}")
    public @ResponseBody Response getContentInfo(@PathVariable Integer ctntNo) throws DataNotFoundException {
        return new Response(contentService.getContentInfo(ctntNo));
    }


    /**
     * 컨텐츠 등록하기
     *
     * @param reqVo 컨텐츠 객체
     * @return 성공 응답
     */
    @PostMapping(value = "/content")
    public @ResponseBody Response createContentInfo(@RequestBody ContentInfoReqDto reqVo) {
        this.contentService.createContentInfo(reqVo);
        return new Response();
    }

    /**
     * 컨텐츠 수정하기
     *
     * @param reqVo 컨텐츠 객체
     * @return 성공 응답
     * @throws DataNotFoundException 조회 오류 예외처리
     */
    @PutMapping(value = "/content")
    public @ResponseBody Response updateContentInfo(@RequestBody ContentInfoReqDto reqVo) throws DataNotFoundException {
        this.contentService.updateContentInfo(reqVo);
        return new Response();
    }

    /**
     * 컨텐츠 삭제하기
     *
     * @param ctntNo 컨텐츠 번호
     * @return 성공 응답
     * @throws DataNotFoundException 조회 오류 예외처리
     */
    @DeleteMapping(value = "/content/{ctntNo}")
    public @ResponseBody Response deleteContentInfo(@PathVariable Long ctntNo) throws DataNotFoundException {
        this.contentService.deleteContentInfo(ctntNo);
        return new Response();
    }

}
