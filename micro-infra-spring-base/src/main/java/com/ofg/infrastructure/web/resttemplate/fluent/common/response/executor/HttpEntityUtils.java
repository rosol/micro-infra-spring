package com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor;

import groovy.transform.CompileStatic;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.Map;

/**
 * Utility class that extracts {@link HttpEntity} from the provided map of passed parameters
 */
public final class HttpEntityUtils {
    private HttpEntityUtils() {
        throw new UnsupportedOperationException("Can't instantiate a utility class");
    }

    public static HttpEntity<Object> getHttpEntityFrom(Map params) {
        if (params.httpEntity.asBoolean()) {
            return (HttpEntity) params.httpEntity;
        }

        HttpHeaders headers = (HttpHeaders) params.headers;
        HttpEntity<?> httpEntity = new HttpEntity<Object>(params.request, headers);
        return ((HttpEntity<Object>) (httpEntity));
    }

}
