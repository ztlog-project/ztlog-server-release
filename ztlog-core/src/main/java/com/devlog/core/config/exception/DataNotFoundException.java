package com.devlog.core.config.exception;

import com.devlog.core.common.enumulation.ResponseStatusCode;

public class DataNotFoundException extends CoreException {

    public DataNotFoundException(String message, ResponseStatusCode code) {
        super(message, code);
    }

    public DataNotFoundException(String message) {
        super(message, ResponseStatusCode.RESOURCE_NOT_FOUND);
    }

}
