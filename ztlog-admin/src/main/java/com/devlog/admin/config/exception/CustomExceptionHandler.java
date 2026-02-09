package com.devlog.admin.config.exception;

import com.devlog.core.common.dto.Response;
import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.config.exception.DataConflictException;
import com.devlog.core.config.exception.DataNotFoundException;
import com.devlog.core.config.exception.InternalServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Response<String>> handleDataNotFoundException(DataNotFoundException e) {
        log.warn("DataNotFoundException: {}", e.getMessage());
        return Response.error(ResponseCode.NOT_FOUND_DATA, e.getMessage());
    }

    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<Response<String>> handleDataConflictException(DataConflictException e) {
        log.warn("DataConflictException: {}", e.getMessage());
        return Response.error(ResponseCode.CONFLICT_USER_ERROR, e.getMessage());
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<Response<String>> handleInternalServerException(InternalServerException e) {
        log.error("InternalServerException: {}", e.getMessage());
        return Response.error(ResponseCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<String>> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("유효성 검사 실패");
        log.warn("ValidationException: {}", message);
        return Response.error(ResponseCode.INVALID_DATA_ERROR, message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> handleException(Exception e) {
        log.error("Unhandled Exception: ", e);
        return Response.error(ResponseCode.INTERNAL_SERVER_ERROR);
    }
}
