package com.devlog.core.common.service.repository.user;

import com.devlog.core.common.service.entity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
