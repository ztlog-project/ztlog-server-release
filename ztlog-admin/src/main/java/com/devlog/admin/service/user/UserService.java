package com.devlog.admin.service.user;

import com.devlog.admin.service.user.dto.request.LoginReqDto;
import com.devlog.admin.service.user.dto.request.SignupReqDto;
import com.devlog.admin.service.user.dto.response.UserDetailDto;
import com.devlog.core.common.dto.TokenInfo;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.utils.TokenUtils;
import com.devlog.core.config.exception.InternalServerException;
import com.devlog.core.repository.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final TokenUtils tokenUtils;


    /**
     * 유저 정보 조회하기
     *
     * @param userNo 유저 번호
     * @return 유저 정보
     */
    public UserDetailDto getUserInfo(Long userNo) {

        return null;
    }

    public void signupUser(SignupReqDto reqDto) {

    }

    public TokenInfo loginUser(LoginReqDto reqDto, HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(reqDto.getUsername(), reqDto.getPassword())
        );

        if (authentication.getPrincipal() instanceof UserDetailDto userDetailDto) {
            // 세션에 인증 정보 저장
            request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            
            return tokenUtils.generateToken(userDetailDto.getUsername());
        }

        throw new InternalServerException(ResponseCode.INTERNAL_SERVER_ERROR.getMessage());
    }

    public void logoutUser() {


    }

}
