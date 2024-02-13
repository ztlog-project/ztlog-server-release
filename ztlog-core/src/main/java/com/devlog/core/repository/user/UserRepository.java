package com.devlog.core.repository.user;

import com.devlog.core.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserNo(Long userNo);

    UserEntity findByUsername(String username);
}
