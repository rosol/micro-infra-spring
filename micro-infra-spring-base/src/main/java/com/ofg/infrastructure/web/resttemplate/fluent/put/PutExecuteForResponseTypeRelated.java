package com.ofg.infrastructure.web.resttemplate.fluent.put;

import com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.ResponseTypeRelatedRequestsExecutor;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

import java.util.Map;

/**
 * Implementation of method execution for the {@link HttpMethod#PUT} method.
 */
public class PutExecuteForResponseTypeRelated<T> extends ResponseTypeRelatedRequestsExecutor<T> {
    public PutExecuteForResponseTypeRelated(Map params, RestOperations restOperations, Class<T> responseType) {
        super(params, restOperations, responseType);
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.PUT;
    }

}
