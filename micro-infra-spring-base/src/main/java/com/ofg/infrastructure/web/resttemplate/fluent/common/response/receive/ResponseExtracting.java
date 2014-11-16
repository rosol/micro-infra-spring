package com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive;

/**
 * Interface allowing to retrieve a response in a form of an object or
 * a {@link org.springframework.http.ResponseEntity}. You can also choose
 * to ignore the received response.
 *
 * @see com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.ObjectReceiving
 * @see com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.ResponseEntityReceiving
 * @see com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.ResponseIgnoring
 */
public interface ResponseExtracting extends ResponseIgnoring {
    ObjectReceiving anObject();

    ResponseEntityReceiving aResponseEntity();
}
