package com.devlog.core.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    private int code = 200;
    private Object message = "Success";
    private Object result;

    public Response() {}

    public Response(Object result) {
        this.result = result;
    }

    public Response(int code, Object message) {
        this.code = code;
        this.message = message;
    }

    public Response(int code, Object message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }
}

