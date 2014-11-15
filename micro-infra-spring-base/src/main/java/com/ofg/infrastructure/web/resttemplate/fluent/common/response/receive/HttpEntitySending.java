package com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive;

import org.springframework.http.HttpEntity;

/**
 * Interface that allows sending a request from a {@link HttpEntity}
 */
public interface HttpEntitySending<T> {
    public abstract T httpEntity(HttpEntity httpEntity);
}
