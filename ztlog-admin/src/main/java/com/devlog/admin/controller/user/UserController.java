package com.devlog.admin.controller.user;

import com.devlog.admin.dto.user.request.SignupReqDto;
import com.devlog.admin.service.user.UserService;
import com.devlog.core.common.vo.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.devlog.admin.dto.user.request.LoginReqDto;

@Tag(name = "유저 컨트롤러", description = "유저 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;

    /**
     * 유저 정보 조회
     *
     * @return 유저 정보
     */
    @Operation(summary = "유저 정보 조회", description = "유저 정보 조회")
    @GetMapping(value = "/info")
    public @ResponseBody Response getUserInfo() {
        // TODO : 테스트용 api -> 추후 삭제 or 수정 요망
        return new Response(userService.getUserInfo(1L));
    }

    /**
     * 회원가입하기
     *
     * @param reqDto 회원가입 요청 객체
     * @return 성공 응답
     */
    @Operation(summary = "회원가입하기", description = "회원가입하기")
    @PostMapping(value = "/signup")
    public @ResponseBody Response signupUser(@RequestBody @Valid SignupReqDto reqDto) {
        this.userService.signupUser(reqDto);
        return new Response();
    }

    /**
     * 로그인하기
     *
     * @param reqDto 로그인 요청 객체
     * @return 성공 응답
     */
    @Operation(summary = "로그인하기", description = "로그인하기")
    @PostMapping(value = "/login")
    public @ResponseBody Response loginUser(@RequestBody @Valid LoginReqDto reqDto) {
        this.userService.loginUser(reqDto);
        return new Response();
    }

    /**
     * 로그아웃 하기
     *
     * @return 성공 응답
     */
    @Operation(summary = "로그아웃하기", description = "로그아웃하기")
    @PostMapping(value = "/logout")
    public @ResponseBody Response logoutUser() {
        this.userService.logoutUser();
        return new Response();
    }

}
