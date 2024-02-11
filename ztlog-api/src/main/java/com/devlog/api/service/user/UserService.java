package com.devlog.api.service.user;

import com.devlog.api.service.user.dto.UserInfoResDto;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.domain.entity.user.UserEntity;
import com.devlog.core.domain.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfoResDto getUserInfo(String userName) {
        UserEntity user = userRepository.findByUsername(userName);
        if (ObjectUtils.isEmpty(user)) {
            throw new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage());
        }

        return UserInfoResDto.builder()
                .username(user.getUsername())
                .build();
    }
}
