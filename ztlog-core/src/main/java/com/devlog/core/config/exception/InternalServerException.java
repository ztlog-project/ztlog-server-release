package com.devlog.core.config.exception;

import com.devlog.core.common.enumulation.ResponseCode;

import java.io.Serial;

public class InternalServerException extends CoreException {

	@Serial
	private static final long serialVersionUID = -7138649212712765308L;

	public InternalServerException(String message, ResponseCode code) {
		super(message, code);
	}

	public InternalServerException(String message) {
		super(message, ResponseCode.INTERNAL_SERVER_ERROR);
	}

}
