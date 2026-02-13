package com.devlog.core.config.exception;

import com.devlog.core.common.enumulation.ResponseCode;

import java.io.Serial;

public class DataNotFoundException extends CoreException {

    @Serial
    private static final long serialVersionUID = -2747589207423689438L;

    public DataNotFoundException(String message, ResponseCode code) {
        super(message, code);
    }

    public DataNotFoundException(String message) {
        super(message, ResponseCode.NOT_FOUND_DATA);
    }

}
