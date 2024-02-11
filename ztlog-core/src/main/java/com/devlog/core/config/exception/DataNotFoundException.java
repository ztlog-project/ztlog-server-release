package com.devlog.core.config.exception;

import com.devlog.core.common.enumulation.ResponseCode;

public class DataNotFoundException extends CoreException {

    public DataNotFoundException(String message, ResponseCode code) {
        super(message, code);
    }

    public DataNotFoundException(String message) {
        super(message, ResponseCode.NOT_FOUND_DATA);
    }

}
