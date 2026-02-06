package com.devlog.admin.controller.user;

import com.devlog.admin.service.user.dto.request.SignupReqDto;
import com.devlog.admin.service.user.UserService;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.dto.Response;
import com.devlog.core.common.dto.TokenInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.devlog.admin.service.user.dto.request.LoginReqDto;

@Tag(name = "유저 컨트롤러", description = "유저 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1/user")
public class UserController {

    private final UserService userService;

    /**
     * 회원가입하기
     *
     * @param reqDto 회원가입 요청 객체
     * @return 성공 응답
     */
    @Operation(summary = "회원가입하기", description = "회원가입하기")
    @PostMapping("/signup")
    public ResponseEntity<Response<String>> signupUser(@RequestBody @Valid SignupReqDto reqDto, HttpServletRequest request, HttpServletResponse response) {
        userService.signupUser(reqDto);
        return Response.success(ResponseCode.CREATED_SUCCESS);
    }

    /**
     * 로그인하기
     *
     * @param reqDto 로그인 요청 객체
     * @return 성공 응답
     */
    @Operation(summary = "로그인하기", description = "로그인하기")
    @PostMapping("/login")
    public ResponseEntity<Response<TokenInfo>> loginUser(@RequestBody @Valid LoginReqDto reqDto, HttpServletRequest request, HttpServletResponse response) {
        TokenInfo tokenInfo = userService.loginUser(reqDto, request);
        return Response.success(ResponseCode.OK_SUCCESS, tokenInfo);
    }

    /**
     * 로그아웃 하기
     *
     * @return 성공 응답
     */
    @Operation(summary = "로그아웃하기", description = "로그아웃하기")
    @PostMapping("/logout")
    public ResponseEntity<Response<String>> logoutUser(HttpServletRequest request, HttpServletResponse response) {
        userService.logoutUser();
        return Response.success(ResponseCode.OK_SUCCESS);
    }

}
