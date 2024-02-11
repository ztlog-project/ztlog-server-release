package com.devlog.admin.service.user;

import com.devlog.admin.dto.user.request.LoginReqDto;
import com.devlog.admin.dto.user.request.SignupReqDto;
import com.devlog.admin.dto.user.response.UserInfoResDto;
import com.devlog.admin.mapper.UserMapper;
import com.devlog.admin.vo.user.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    /**
     * 유저 정보 조회하기
     *
     * @param userNo 유저 번호
     * @return 유저 정보
     */
    public UserInfoResDto getUserInfo(Long userNo) {
        UserVo userVo = this.userMapper.selectUserByNo(userNo);

        UserInfoResDto resDto = UserInfoResDto.builder().build();
        BeanUtils.copyProperties(userVo, resDto);
        return resDto;
    }

    public void signupUser(SignupReqDto reqDto) {

    }

    public void loginUser(LoginReqDto reqDto) {

    }

    public void logoutUser() {


    }

}
