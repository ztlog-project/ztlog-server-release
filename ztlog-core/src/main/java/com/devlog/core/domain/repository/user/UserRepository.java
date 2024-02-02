package com.devlog.core.domain.repository.user;

import com.devlog.core.domain.entity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUserNo(Long userNo);

    UserEntity findByUsername(String username);
}
