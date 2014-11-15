package com.ofg.infrastructure.web.resttemplate.fluent.head;

import com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.HeadersHaving;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

/**
 * {@link org.springframework.http.HttpMethod#HEAD} method doesn't have a response
 * so this interface provides a {@link ResponseEntity} result or {@link HttpHeaders} to get headers
 */
public interface ResponseReceivingHeadMethod extends HeadersHaving<ResponseReceivingHeadMethod> {
    public abstract ResponseEntity aResponseEntity();

    public abstract HttpHeaders httpHeaders();
}
