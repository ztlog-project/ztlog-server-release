package com.devlog.core.common.vo;

import com.devlog.core.common.enumulation.ResponseStatusCode;
import com.devlog.core.common.enumulation.ResponseResultCode;
import lombok.*;
import org.springframework.http.ResponseEntity;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private int status;
    private ResponseResultCode code;
    private String message;
    private T data;

    public Response(int status, ResponseResultCode code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static <T> ResponseEntity<Response<T>> success(ResponseStatusCode code) {
        return ResponseEntity
                .status(code.getStatus())
                .body(new Response<>(code.getStatus(), ResponseResultCode.SUCCESS, code.getMessage()));
    }

    public static <T> ResponseEntity<Response<T>> success(ResponseStatusCode code, T data) {
        return ResponseEntity
                .status(code.getStatus())
                .body(new Response<>(code.getStatus(), ResponseResultCode.SUCCESS, code.getMessage(), data));
    }

    public static <T> ResponseEntity<Response<T>> error(ResponseStatusCode code) {
        return ResponseEntity
                .status(code.getStatus())
                .body(new Response<>(code.getStatus(), ResponseResultCode.FAIL, code.getMessage()));
    }

    public static <T> ResponseEntity<Response<T>> error(ResponseStatusCode code, String message) {
        return ResponseEntity
                .status(code.getStatus())
                .body(new Response<>(code.getStatus(), ResponseResultCode.FAIL, message));
    }
}

