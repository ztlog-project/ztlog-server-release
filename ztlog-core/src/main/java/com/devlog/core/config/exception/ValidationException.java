package com.devlog.core.config.exception;

import com.devlog.core.common.enumulation.ResponseCode;

public class ValidationException extends CoreException {

	public ValidationException(String message, ResponseCode code) {
		super(message, code);
	}

	public ValidationException(String message) {
		super(message, ResponseCode.INVALID_DATA_ERROR);
	}
}
