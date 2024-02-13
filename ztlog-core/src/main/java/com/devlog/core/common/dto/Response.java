package com.devlog.core.common.dto;

import com.devlog.core.common.enumulation.ResponseCode;
import com.devlog.core.common.enumulation.ResultCode;
import lombok.*;
import org.springframework.http.ResponseEntity;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private int status;
    private ResultCode code;
    private String message;
    private T data;

    public Response(int status, ResultCode code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static <T> ResponseEntity<Response<T>> success(ResponseCode code) {
        return ResponseEntity
                .status(code.getStatus())
                .body(new Response<>(code.getStatus(), ResultCode.SUCCESS, code.getMessage()));
    }

    public static <T> ResponseEntity<Response<T>> success(ResponseCode code, T data) {
        return ResponseEntity
                .status(code.getStatus())
                .body(new Response<>(code.getStatus(), ResultCode.SUCCESS, code.getMessage(), data));
    }

    public static <T> ResponseEntity<Response<T>> error(ResponseCode code) {
        return ResponseEntity
                .status(code.getStatus())
                .body(new Response<>(code.getStatus(), ResultCode.FAIL, code.getMessage()));
    }

    public static <T> ResponseEntity<Response<T>> error(ResponseCode code, String message) {
        return ResponseEntity
                .status(code.getStatus())
                .body(new Response<>(code.getStatus(), ResultCode.FAIL, message));
    }
}

