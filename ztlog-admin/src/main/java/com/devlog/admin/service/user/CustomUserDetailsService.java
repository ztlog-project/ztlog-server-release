package com.devlog.admin.service.user;

import com.devlog.admin.service.user.dto.response.UserDetailDto;
import com.devlog.admin.service.user.dto.response.UserInfoDto;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.entity.user.User;
import com.devlog.core.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 1. userRepository로부터 loginId로 유저정보를 받아온다.
        User user = userRepository.findByUsername(userName);
        if (ObjectUtils.isEmpty(user))
            throw new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage());

        // 2.user를 dto로 변환시켜준다.
        UserInfoDto userInfo = UserInfoDto.builder().build();
        BeanUtils.copyProperties(user, userInfo);

        // 3. 사용자 정보를 기반으로 SecurityUserDetailsDto 객체를 생성한다.
        return new UserDetailDto(userInfo,Collections.singleton(new SimpleGrantedAuthority(userInfo.getGrant())));
    }
}
