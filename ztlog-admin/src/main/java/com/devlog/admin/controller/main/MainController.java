package com.devlog.admin.controller.main;

import com.devlog.admin.service.main.MainService;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "메인(대쉬보드) 컨트롤러", description = "메인(대쉬보드) 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1")
public class MainController {

    private final MainService mainService;

    /**
     * 메인화면(대쉬보드) 조회
     *
     * @return 메인화면(대쉬보드) 정보
     */
    @Operation(summary = "메인화면(대쉬보드) 조회", description = "메인화면(대쉬보드) 조회")
    @GetMapping("/main")
    public ResponseEntity<Response<Object>> getMainStatusInfo() {
        return Response.success(ResponseCode.OK_SUCCESS, mainService.getMainStatusInfo());
    }

}
