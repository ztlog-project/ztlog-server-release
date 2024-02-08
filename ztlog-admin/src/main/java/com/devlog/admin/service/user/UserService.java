package com.devlog.admin.service.user;

import com.devlog.admin.dto.user.request.LoginReqDto;
import com.devlog.admin.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public Object getUserInfo(Long userNo) {
        return null;
    }

    public void loginUser(LoginReqDto reqDto) {
    }

    public void logoutUser() {

    }
}
