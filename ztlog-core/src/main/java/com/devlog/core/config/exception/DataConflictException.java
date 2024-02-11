package com.devlog.core.config.exception;

import com.devlog.core.common.enumulation.ResponseStatusCode;

public class DataConflictException extends CoreException{

    public DataConflictException(String message, ResponseStatusCode code) {
        super(message, code);
    }

    public DataConflictException(String message) {
        super(message, ResponseStatusCode.RESOURCE_DATA_CONFLICT);
    }

}
