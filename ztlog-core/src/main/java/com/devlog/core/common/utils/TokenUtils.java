package com.devlog.core.common.utils;

import com.devlog.core.common.constants.CommonConstants;
import com.devlog.core.common.dto.TokenInfo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.DecodingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;


@Slf4j
@Component
public class TokenUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-token-expire-time}")
    private long accessTokenExpireTime;

    @Value("${jwt.refresh-token-expire-time}")
    private long refreshTokenExpireTime;

    private static final String USER_ID = "USER_ID";

    private Key key;

    @jakarta.annotation.PostConstruct
    public void init() {
        byte[] keyBytes = io.jsonwebtoken.security.Keys.hmacShaKeyFor(secretKey.getBytes()).getEncoded();
        this.key = io.jsonwebtoken.security.Keys.hmacShaKeyFor(keyBytes);
    }


    /**
     * JWT 토큰 생성
     * @param userId 유저 ID
     * @return JWT 토큰
     */
    public TokenInfo generateToken(String userId) {
        long now = (new Date()).getTime();
        Date accessTokenExpires = new Date(now + accessTokenExpireTime);
        Date refreshTokenExpires = new Date(now + refreshTokenExpireTime);

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(userId)
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
     * 토큰에서 userId 추출
     *
     * @param token 토큰
     * @return userId
     */
    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
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
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
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

