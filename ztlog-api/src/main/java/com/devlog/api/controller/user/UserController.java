package com.devlog.api.controller.user;

import com.devlog.api.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.devlog.core.common.vo.Response;

@Api(tags = "User")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "유저 정보 조회", notes = "유저 정보 조회")
    @GetMapping(value = "/info")
    public @ResponseBody Response getUserInfo(@PathVariable Long userNo) {
        return new Response(userService.getUserInfo(userNo));
    }

}
