package com.devlog.api.service.user.dto;

import com.devlog.core.entity.user.User;
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

    public static UserResDto of(User user) {
        return UserResDto.builder()
                .username(user.getUsername())
                .build();
    }
}
