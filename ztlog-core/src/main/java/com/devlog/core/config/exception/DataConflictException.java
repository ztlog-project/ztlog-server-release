package com.devlog.core.config.exception;

import com.devlog.core.common.enumulation.ResponseCode;

import java.io.Serial;

public class DataConflictException extends CoreException{

    @Serial
    private static final long serialVersionUID = -9101820825193957867L;

    public DataConflictException(String message, ResponseCode code) {
        super(message, code);
    }

    public DataConflictException(String message) {
        super(message, ResponseCode.CONFLICT_DATA_ERROR);
    }

}
