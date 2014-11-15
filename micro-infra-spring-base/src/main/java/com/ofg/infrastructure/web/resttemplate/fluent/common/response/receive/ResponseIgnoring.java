package com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive;

public interface ResponseIgnoring {
    /**
     * When you do not care about the received response for your HTTP request
     */
    public abstract void ignoringResponse();
}
