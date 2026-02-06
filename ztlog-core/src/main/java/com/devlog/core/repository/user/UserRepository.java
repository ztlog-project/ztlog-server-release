package com.devlog.core.repository.user;

import com.devlog.core.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserNo(Long userNo);

    User findByUsername(String username);

    boolean existsByUsername(String username);
}
