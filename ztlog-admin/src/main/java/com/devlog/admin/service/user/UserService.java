package com.devlog.admin.service.user;

import com.devlog.admin.service.user.dto.request.LoginReqDto;
import com.devlog.admin.service.user.dto.request.SignupReqDto;
import com.devlog.admin.service.user.dto.response.UserDetailDto;
import com.devlog.core.common.dto.TokenInfo;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.utils.TokenUtils;
import com.devlog.core.config.exception.DataConflictException;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.config.exception.InternalServerException;
import com.devlog.core.entity.user.User;
import com.devlog.core.repository.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;
    private final PasswordEncoder passwordEncoder;

    @Value("${user.default-role:ADMIN}")
    private String defaultRole;

    /**
     * 유저 정보 조회하기
     *
     * @param userNo 유저 번호
     * @return 유저 정보
     */
    @Transactional(readOnly = true)
    public UserDetailDto getUserInfo(Long userNo) {
        User user = userRepository.findByUserNo(userNo);
        if (user == null) {
            throw new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage());
        }
        return UserDetailDto.of(user, null);
    }

    /**
     * 회원가입
     *
     * @param reqDto 회원가입 요청 정보
     */
    public void signupUser(SignupReqDto reqDto) {
        // 중복 사용자 검증
        if (userRepository.existsByUserId(reqDto.getUserId())) {
            throw new DataConflictException(ResponseCode.CONFLICT_USER_ERROR.getMessage());
        }

        // 비밀번호 암호화 및 사용자 생성
        String encodedPassword = passwordEncoder.encode(reqDto.getPassword());
        User user = User.created(reqDto.getUserId(), reqDto.getUsername(), encodedPassword, defaultRole);

        userRepository.save(user);
    }

    /**
     * 로그인
     *
     * @param reqDto 로그인 요청 정보
     * @param request HTTP 요청
     * @return JWT 토큰 정보
     */
    public TokenInfo loginUser(LoginReqDto reqDto, HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(reqDto.getUsername(), reqDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (authentication.getPrincipal() instanceof UserDetailDto userDetailDto) {
            return tokenUtils.generateToken(userDetailDto.getUsername());
        }

        throw new InternalServerException(ResponseCode.INTERNAL_SERVER_ERROR.getMessage());
    }

    /**
     * 로그아웃
     *
     * @param request HTTP 요청
     */
    public void logoutUser(HttpServletRequest request) {
        // SecurityContext 초기화
        SecurityContextHolder.clearContext();

        // 세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    /**
     * 회원탈퇴
     *
     * @param request HTTP 요청
     */
    public void withdrawUser(HttpServletRequest request) {
        // 현재 인증된 사용자 정보 조회
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new InternalServerException(ResponseCode.UNAUTHORIZED_USER_GRANT.getMessage());
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage());
        }

        // 사용자 삭제
        userRepository.delete(user);

        // 로그아웃 처리
        logoutUser(request);
    }

}
