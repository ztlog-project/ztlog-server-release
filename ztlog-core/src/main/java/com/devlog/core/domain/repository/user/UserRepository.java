package com.devlog.core.domain.repository.user;

import com.devlog.core.domain.entity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
