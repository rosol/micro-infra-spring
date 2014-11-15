package com.ofg.infrastructure.web.resttemplate.custom;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class ResponseException extends RuntimeException {
    public ResponseException(HttpStatus httpStatus, String body, HttpHeaders headers) {
        this.httpStatus = httpStatus;
        this.body = body;
        this.headers = headers;
    }

    public String getMessage() {
        return "Status code: [" + String.valueOf(httpStatus) + "], Headers: [" + String.valueOf(headers) + "], Body: [" + body + "]";
    }

    public final HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public final HttpHeaders getHeaders() {
        return headers;
    }

    public final String getBody() {
        return body;
    }

    private final HttpStatus httpStatus;
    private final HttpHeaders headers;
    private final String body;
}
