package com.devlog.admin.service.user.dto.request;


import java.io.Serial;
import java.io.Serializable;


public class SignupReqDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 767758686263528683L;

    private String username;

    private String password;

    private String grant;

}