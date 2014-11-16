package com.ofg.infrastructure.web.resttemplate.fluent.options;

import com.ofg.infrastructure.web.resttemplate.fluent.common.Parameters;
import com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.ResponseTypeRelatedRequestsExecutor;
import groovy.transform.CompileStatic;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

/**
 * Implementation of method execution for the {@link HttpMethod#OPTIONS} method.
 */
@CompileStatic
public class OptionsExecuteForResponseTypeRelated<T> extends ResponseTypeRelatedRequestsExecutor<T> {
    public OptionsExecuteForResponseTypeRelated(Parameters params, RestOperations restOperations, Class<T> responseType) {
        super(params, restOperations, responseType);
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.OPTIONS;
    }

}
