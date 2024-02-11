package com.devlog.core.config.exception;

import com.devlog.core.common.enumulation.ResponseStatusCode;
import lombok.Getter;

@Getter
public abstract class CoreException extends RuntimeException {

    private final ResponseStatusCode responseStatusCode;

    public CoreException(String message, ResponseStatusCode responseStatusCode) {
        super(message);
        this.responseStatusCode = responseStatusCode;
    }

    public int getStatus() {
        return this.responseStatusCode.getStatus();
    }

    public String getMessage() {
        return this.responseStatusCode.getMessage();
    }

}
