package com.devlog.admin.config.security;

import com.devlog.core.common.dto.Response;
import com.devlog.core.common.enumulation.ResponseCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String exception = (String) request.getAttribute("exception");
        ResponseCode responseCode;

        if (exception == null) {
            responseCode = ResponseCode.UNAUTHORIZED_USER_GRANT;
        } else if ("EXPIRED_TOKEN".equals(exception)) {
            responseCode = ResponseCode.UNAUTHORIZED_EXPIRED_TOKEN;
        } else {
            responseCode = ResponseCode.UNAUTHORIZED_INVALID_TOKEN;
        }

        log.warn("Unauthorized error: {}, exception attribute: {}", authException.getMessage(), exception);
        setResponse(response, responseCode);
    }

    private void setResponse(HttpServletResponse response, ResponseCode responseCode) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Response<String> errorResponse = new Response<>(
                responseCode.getStatus(),
                com.devlog.core.common.enumulation.ResultCode.FAIL,
                responseCode.getMessage()
        );

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
