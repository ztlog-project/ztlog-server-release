package com.devlog.admin.dto.user.response;

import com.devlog.core.entity.user.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class UserResDto {

    private String userId;
    private String username;
    private String grant;
    private LocalDateTime inpDttm;
    private LocalDateTime updDttm;

    public static UserResDto of(User user) {
        return UserResDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .grant(user.getGrant())
                .inpDttm(user.getInpDttm())
                .updDttm(user.getUpdDttm())
                .build();
    }
}
