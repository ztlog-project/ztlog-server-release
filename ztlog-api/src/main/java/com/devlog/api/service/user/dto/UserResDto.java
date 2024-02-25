package com.devlog.api.service.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@ToString
@Builder
public class UserResDto {

    private String username;

    private String grant;

}
