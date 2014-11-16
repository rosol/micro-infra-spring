package com.ofg.infrastructure.web.resttemplate.fluent.delete;

import com.ofg.infrastructure.web.resttemplate.fluent.common.Parameters;
import com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.ResponseTypeRelatedRequestsExecutor;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

/**
 * Implementation of method execution for the {@link HttpMethod#DELETE} method.
 */
public class DeleteExecuteForResponseTypeRelated extends ResponseTypeRelatedRequestsExecutor<Object> {
    public DeleteExecuteForResponseTypeRelated(Parameters params, RestOperations restOperations) {
        super(params, restOperations, Object.class);
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.DELETE;
    }

}
