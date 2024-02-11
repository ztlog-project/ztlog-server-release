package com.devlog.core.config.exception;

import com.devlog.core.common.enumulation.ResponseCode;

public class DataConflictException extends CoreException{

    public DataConflictException(String message, ResponseCode code) {
        super(message, code);
    }

    public DataConflictException(String message) {
        super(message, ResponseCode.CONFLICT_DATA_ERROR);
    }

}
