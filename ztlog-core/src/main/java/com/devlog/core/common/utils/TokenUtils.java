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

/**
 * JWT 관련 토큰 Util
 */
@Slf4j
@Component
public class TokenUtils {


    private static final String secretKey = "thisIsASecretKeyUsedForJwtTokenGenerationAndItIsLongEnoughToMeetTheRequirementOf256Bits";
    // jwtSecretKey를 바이트 배열로 변환하고, 이를 사용하여 HMAC-SHA256 알고리즘에 사용할 키를 생성한다.

    //private static final Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    private Key key;

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 10 * 60 * 1000L;              // 10분

    private static final long REFRESH_TOKEN_EXPIRE_TIME = 6 * 30 * 24 * 60 * 60 * 1000L;    // 180일

    private static final long EXPIRED_TIME = 1L;

    private static final String USER_ID = "USER_ID";

    //    private static final long TOKEN_EXPIRE_TIME = 365 * 24 * 60 * 60 * 1000L;   // 1년


    /**
     * JWT 토큰 생성
     * @param userId 유저 ID
     * @return JWT 토큰
     */
    public TokenInfo generateTokenDto(String userId) {
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

    // Request Header에 Access Token 정보를 추출하는 메서드

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
























//
//    /**
//     * 사용자 pk를 기준으로 JWT 토큰을 발급하여 반환해 준다.
//     */
//    public static String generateJwtToken(User user) {
//
//        JwtBuilder builder = Jwts.builder()
//                .signWith(key)
//                .setHeader(createHeader())                              // Header 구성
//                .setClaims(createClaims(user))                          // Payload - Claims구성
//                .setSubject(String.valueOf(user.getUsername()))         // Payload - Subjects구성
//                .setIssuer("profile")                                   // Issuer 구성
////                .signWith(key, SignatureAlgorithm.HS256)              // Signature 구성 : 이 키를 사용하여 JWT 토큰에 서명을 추가한다. 이 서명은 토큰의 무결성을 보장하는 데 사용된다.
//                .setExpiration(createExpiredDate());                    // Expired Date 구성
//
//        return builder.compact();
//    }
//
//    /**
//     * 토큰을 기반으로 사용자의 정보를 반환해주는 메서드
//     */
//    public static boolean isValidToken(String token) {
//        try {
//            Claims claims = getClaimsFormToken(token);
//
//            log.info("expireTime : {}", claims.getExpiration());
//            log.info("userNo : {}", claims.get(USER_NO));
//            log.info("username : {}", claims.get(USERNAME));
//
//            return true;
//        } catch (ExpiredJwtException expiredJwtException) {
//            log.error("Token Expired", expiredJwtException);
//            return false;
//        } catch (JwtException jwtException) {
//            log.error("Token Tampered", jwtException);
//            return false;
//        } catch (NullPointerException npe) {
//            log.error("Token is null", npe);
//            return false;
//        }
//    }
//
//    /**
//     * 토큰의 만료기간을 지정하는 함수
//     *
//     * @return Date
//     */
//    private static Date createExpiredDate() {
//        // 토큰의 만료기간은 8시간으로 지정
//        Instant now = Instant.now();
//        Instant expiryDate = now.plus(Duration.ofHours(8));
//        return Date.from(expiryDate);
//    }
//
//    /**
//     * JWT의 헤더값을 생성해주는 메서드
//     */
//    private static Map<String, Object> createHeader() {
//        Map<String, Object> header = new HashMap<>();
//
//        header.put("typ", JWT_TYPE);
//        header.put("alg", ALGORITHM);
//        header.put("regDate", System.currentTimeMillis());
//        return header;
//    }
//
//    /**
//     * 사용자 정보를 기반으로 클래임을 생성해주는 메서드
//     *
//     * @param user 사용자 정보
//     * @return Map<String, Object>
//     */
//    private static Map<String, Object> createClaims(User user) {
//        // 공개 클래임에 사용자의 이름과 이메일을 설정해서 정보를 조회할 수 있다.
//        Map<String, Object> claims = new HashMap<>();
//
//        log.info("username : {}", user.getUsername());
//
//        claims.put(USER_NO, user.getUserNo());
//        claims.put(USERNAME, user.getUsername());
//        return claims;
//    }
//
//    /**
//     * 토큰 정보를 기반으로 Claims 정보를 반환받는 메서드
//     *
//     * @return Claims : Claims
//     */
//    private static Claims getClaimsFormToken(String token) {
//        return Jwts.parserBuilder().setSigningKey(key)
//                .build().parseClaimsJws(token).getBody();
//    }
//
//    /**
//     * 토큰을 기반으로 사용자 정보를 반환받는 메서드
//     *
//     * @return String : 사용자 아이디
//     */
//    public static String getUserIdFromToken(String token) {
//        Claims claims = getClaimsFormToken(token);
//        return claims.get(USERNAME).toString();
//    }

}

