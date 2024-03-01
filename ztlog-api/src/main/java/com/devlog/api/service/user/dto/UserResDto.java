package com.devlog.api.service.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class UserResDto {

    private String username;

    private String grant;

}
