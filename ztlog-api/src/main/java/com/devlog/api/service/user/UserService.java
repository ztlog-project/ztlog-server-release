package com.devlog.api.service.user;

import com.devlog.api.service.user.dto.UserResDto;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.entity.user.User;
import com.devlog.core.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResDto getUserInfo(String userName) {
        User user = userRepository.findByUsername(userName);
        if (ObjectUtils.isEmpty(user)) {
            throw new DataNotFoundException(ResponseCode.NOT_FOUND_DATA.getMessage());
        }

        UserResDto resDto = UserResDto.builder().build();
        BeanUtils.copyProperties(user, resDto);
        return resDto;
    }
}
