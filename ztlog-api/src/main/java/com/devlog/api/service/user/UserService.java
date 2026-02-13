package com.devlog.api.service.user;

import com.devlog.api.service.user.dto.UserResDto;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.entity.user.User;
import com.devlog.core.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResDto getUserInfo(String userName) {
        UserResDto resDto = userRepository.findOptionalByUsername(userName)
                .map(UserResDto::of)
                .orElseThrow(() -> new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage()));
        log.info("user = {}", resDto.toString());
        return resDto;
    }
}
