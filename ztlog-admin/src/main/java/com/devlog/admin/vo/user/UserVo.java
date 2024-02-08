package com.devlog.admin.vo.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 973716813720954659L;

    private Long userNo;

    private String username;

    private String password;

    private String grant;

}
