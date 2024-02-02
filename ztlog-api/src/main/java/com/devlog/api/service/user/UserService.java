package com.devlog.api.service.user;

import com.devlog.core.domain.entity.user.UserEntity;
import com.devlog.core.domain.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity getUserInfo(Long userNo) {
        return userRepository.findByUserNo(userNo);
    }
}
