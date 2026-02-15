package com.devlog.core.config.exception;

import com.devlog.core.common.enumulation.ResponseCode;

import java.io.Serial;

public class ValidationException extends CoreException {

    @Serial
    private static final long serialVersionUID = 8445416038055646983L;

    public ValidationException(String message, ResponseCode code) {
        super(message, code);
    }

    public ValidationException(String message) {
        super(message, ResponseCode.INVALID_DATA_ERROR);
    }
}
