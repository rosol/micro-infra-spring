package com.ofg.infrastructure.web.resttemplate.fluent.post;

import com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.ResponseTypeRelatedRequestsExecutor;
import groovy.transform.CompileStatic;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

import java.util.Map;

/**
 * Implementation of method execution for the {@link HttpMethod#POST} method.
 */
@CompileStatic
public class PostExecuteForResponseTypeRelated<T> extends ResponseTypeRelatedRequestsExecutor<T> {
    public PostExecuteForResponseTypeRelated(Map params, RestOperations restOperations, Class<T> responseType) {
        super(params, restOperations, responseType);
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

}
