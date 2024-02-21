package com.devlog.core.common.dto;

import lombok.*;


@Data
@Builder
public class TokenInfo {

    private final String grantType;

    private final String authorizationType;

    private final String accessToken;

    private final String refreshToken;

    private final Long accessTokenExpiresIn;
}
