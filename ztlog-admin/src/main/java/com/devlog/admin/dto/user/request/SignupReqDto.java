package com.devlog.admin.dto.user.request;

import lombok.Value;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.devlog.core.domain.entity.user.UserEntity}
 */
@Value
public class SignupReqDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 767758686263528683L;

    String username;

    String password;

    String grant;
}