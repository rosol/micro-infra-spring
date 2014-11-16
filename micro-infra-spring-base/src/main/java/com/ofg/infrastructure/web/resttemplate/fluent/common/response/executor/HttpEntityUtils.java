package com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor;

import com.ofg.infrastructure.web.resttemplate.fluent.common.Parameters;
import org.springframework.http.HttpEntity;

/**
 * Utility class that extracts {@link HttpEntity} from the provided map of passed parameters
 */
public final class HttpEntityUtils {
    private HttpEntityUtils() {
        throw new UnsupportedOperationException("Can't instantiate a utility class");
    }

    public static HttpEntity<Object> getHttpEntityFrom(Parameters params) {
        if (params.hasHttpEntity()) {
            return params.httpEntity;
        }
        return new HttpEntity<Object>(params.request, params.headers);
    }

}
