package com.devlog.admin.dto.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
public class UserInfoResDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 3352408126115467024L;

    private Long userNo;

    private String username;

    private String grant;


}
