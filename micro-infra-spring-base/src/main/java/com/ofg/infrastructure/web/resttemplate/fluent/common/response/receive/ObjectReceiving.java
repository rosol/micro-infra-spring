package com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive;

/**
 * Interface that defines what is the type of the received response.
 * It will return an object of provided class.
 */
public interface ObjectReceiving {
    public abstract <T> T ofType(Class<T> responseType);
}
