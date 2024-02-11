package com.devlog.core.config.exception;

import com.devlog.core.common.enumulation.ResponseCode;

public class InternalServerException extends CoreException {

	public InternalServerException(String message, ResponseCode code) {
		super(message, code);
	}

	public InternalServerException(String message) {
		super(message, ResponseCode.INTERNAL_SERVER_ERROR);
	}

}
