package com.devlog.admin.dto.user.request;

import com.devlog.core.entity.user.UserEntity;
import lombok.Value;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO for {@link UserEntity}
 */
@Value
public class SignupReqDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 767758686263528683L;

    String username;

    String password;

    String grant;
}