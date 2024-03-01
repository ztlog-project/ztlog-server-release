package com.devlog.admin.service.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Builder
public class LoginReqDto {

    private Long userNo;

    private String userId;

    private String username;

    private String password;

    private String grant;

}
