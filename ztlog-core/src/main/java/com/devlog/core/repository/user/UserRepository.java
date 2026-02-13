package com.devlog.core.repository.user;

import com.devlog.core.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);

    Optional<User> findOptionalByUserId(String username);

    Optional<User> findOptionalByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByUserId(String userId);
}
