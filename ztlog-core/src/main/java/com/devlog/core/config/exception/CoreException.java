package com.devlog.core.config.exception;

import com.devlog.core.common.enumulation.ResponseCode;
import lombok.Getter;

import java.io.Serial;

@Getter
public abstract class CoreException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5973020248307001293L;
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
