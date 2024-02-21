package com.devlog.admin.service.user;

import com.devlog.admin.service.user.dto.response.UserDetailDto;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.entity.user.User;
import com.devlog.core.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        // userRepository 에서 유저 객체를 받아온다.
        User user = userRepository.findByUsername(userName);
        if (ObjectUtils.isEmpty(user))
            throw new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage());

        // UserDetailDto 객체를 생성하여 반환
        return new UserDetailDto(user, Collections.singleton(new SimpleGrantedAuthority(user.getGrant())));
    }
}
