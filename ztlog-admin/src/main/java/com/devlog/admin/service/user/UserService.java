package com.devlog.admin.service.user;

import com.devlog.admin.service.user.dto.request.LoginReqDto;
import com.devlog.admin.service.user.dto.request.SignupReqDto;
import com.devlog.admin.service.user.dto.response.UserDetailDto;
import com.devlog.core.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


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

    public void loginUser(LoginReqDto reqDto) {

    }

    public void logoutUser() {


    }

}
