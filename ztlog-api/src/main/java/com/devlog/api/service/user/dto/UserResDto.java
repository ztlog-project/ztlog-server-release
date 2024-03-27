package com.devlog.api.service.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserResDto {

    private String username;

    private String grant;

}
