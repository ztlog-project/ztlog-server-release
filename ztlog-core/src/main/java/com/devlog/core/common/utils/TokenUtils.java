package com.devlog.core.common.utils;

import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.common.dto.TokenInfo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.DecodingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;


@Slf4j
@Component
public class TokenUtils {

    private static final String secretKey = "thisIsASecretKeyUsedForJwtTokenGenerationAndItIsLongEnoughToMeetTheRequirementOf256Bits";

    private Key key;

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 10 * 60 * 1000L;              // 10분

    private static final long REFRESH_TOKEN_EXPIRE_TIME = 6 * 30 * 24 * 60 * 60 * 1000L;    // 180일

    private static final String USER_ID = "USER_ID";


    /**
     * JWT 토큰 생성
     * @param userId 유저 ID
     * @return JWT 토큰
     */
    public TokenInfo generateToken(String userId) {
        long now = (new Date()).getTime();
        Date accessTokenExpires = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpires = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        // Access Token 생성
        String accessToken = Jwts.builder()
                .claim(USER_ID, userId)
                .setExpiration(accessTokenExpires)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpires)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return TokenInfo.builder()
                .grantType(CommonConstants.BEARER_TYPE)
                .authorizationType(CommonConstants.AUTHORIZATION_HEADER)
                .accessToken(accessToken)
                .accessTokenExpiresIn(accessTokenExpires.getTime())
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * 토큰 검증
     *
     * @param token 토큰
     * @param response 응답
     * @return 검증 여부
     */
    public boolean validateToken(String token, HttpServletResponse response) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException | DecodingException e) {
            log.warn("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.warn("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.warn("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.warn("JWT claims string is empty.", e);
        } catch (Exception e) {
            log.error("Unhandled JWT exception", e);
        }
        return false;
    }

    /**
     * access 토큰 헤더에 세팅
     *
     * @param accessToken 액세스 토큰
     * @param response 응답
     */
    public void accessTokenSetHeader(String accessToken, HttpServletResponse response) {
        String headerValue = CommonConstants.BEARER_PREFIX + accessToken;
        response.setHeader(CommonConstants.AUTHORIZATION_HEADER, headerValue);
    }

    /**
     * refresh 토큰 헤더에 세팅
     *
     * @param refreshToken refresh 토큰
     * @param response 응답
     */
    public void refresshTokenSetHeader(String refreshToken, HttpServletResponse response) {
        response.setHeader("Refresh", refreshToken);
    }

    /**
     * Request 헤더에 세팅된 Access Token 추출
     *
     * @param request 요청
     * @return 토큰 정보
     */
    public String resolveAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(CommonConstants.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(CommonConstants.BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

}

