package com.devlog.core.config.exception;

import java.io.Serial;

public class DataNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public DataNotFoundException(int key) {
        super(String.valueOf(key));
    }

    public DataNotFoundException(String id) {
        super(id);
    }

    public DataNotFoundException(Throwable cause) {
        super(cause);
    }

    public DataNotFoundException(String id, Throwable cause) {
        super(id, cause);
    }

}
