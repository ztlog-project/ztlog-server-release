package com.devlog.api.service.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@ToString
@Builder
public class UserInfoResDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1961991902596974635L;

    private String username;

    private String grant;

}
