package com.ofg.infrastructure.web.resttemplate.fluent.delete;

import com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.HeadersHaving;
import org.springframework.http.ResponseEntity;

/**
 * {@link org.springframework.http.HttpMethod#DELETE} method doesn't have a response
 * so this interface provides only a {@link ResponseEntity} result or {@link ResponseIgnoring} one
 */
public interface ResponseReceivingDeleteMethod extends HeadersHaving<ResponseReceivingDeleteMethod> {
    public abstract ResponseEntity aResponseEntity();
}
