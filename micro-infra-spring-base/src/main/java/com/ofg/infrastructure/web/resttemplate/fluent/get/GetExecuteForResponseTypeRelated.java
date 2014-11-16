package com.ofg.infrastructure.web.resttemplate.fluent.get;

import com.ofg.infrastructure.web.resttemplate.fluent.common.Parameters;
import com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.ResponseTypeRelatedRequestsExecutor;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

/**
 * Implementation of method execution for the {@link HttpMethod#GET} method.
 */
public class GetExecuteForResponseTypeRelated<T> extends ResponseTypeRelatedRequestsExecutor<T> {
    public GetExecuteForResponseTypeRelated(Parameters params, RestOperations restOperations, Class<T> responseType) {
        super(params, restOperations, responseType);
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

}
