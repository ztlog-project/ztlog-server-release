package com.devlog.core.config.exception;

import com.devlog.core.common.enumulation.ResponseCode;
import lombok.Getter;

@Getter
public abstract class CoreException extends RuntimeException {

    private final ResponseCode responseCode;

    public CoreException(String message, ResponseCode responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    public int getStatus() {
        return this.responseCode.getStatus();
    }

    public String getMessage() {
        return this.responseCode.getMessage();
    }

}
