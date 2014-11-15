package com.ofg.infrastructure.web.resttemplate.fluent.head;

import com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.ResponseTypeRelatedRequestsExecutor;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

import java.util.Map;

/**
 * Implementation of method execution for the {@link HttpMethod#HEAD} method.
 */
public class HeadExecuteForResponseTypeRelated extends ResponseTypeRelatedRequestsExecutor<Object> {
    public HeadExecuteForResponseTypeRelated(Map params, RestOperations restOperations) {
        super(params, restOperations, Object.class);
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.HEAD;
    }

}
