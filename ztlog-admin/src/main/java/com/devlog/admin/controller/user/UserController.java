package com.devlog.admin.controller.user;

import com.devlog.admin.service.user.UserService;
import com.devlog.core.common.vo.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 컨트롤러", description = "유저 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 정보 조회", description = "유저 정보 조회")
    @GetMapping(value = "/info")
    public @ResponseBody Response getUserInfo() {
        return new Response(userService.getUserInfo(1L));
    }

}
