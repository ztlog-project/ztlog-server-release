package com.devlog.api.controller.user;

import com.devlog.api.service.user.UserService;
import com.devlog.api.service.user.dto.UserInfoResDto;
import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.common.enumulation.ResponseStatusCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.devlog.core.common.vo.Response;

@Tag(name = "유저 컨트롤러", description = "유저 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 정보 조회", description = "유저 정보 조회")
    @GetMapping(value = "/info")
    public ResponseEntity<Response<UserInfoResDto>> getUserInfo() {
        return Response.success(ResponseStatusCode.OK_SUCCESS, userService.getUserInfo(CommonConstants.ADMIN));
    }

}
