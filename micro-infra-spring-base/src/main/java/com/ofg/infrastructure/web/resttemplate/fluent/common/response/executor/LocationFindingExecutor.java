package com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor;

import com.ofg.infrastructure.web.resttemplate.fluent.common.Parameters;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

import java.net.URI;

import static com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.HttpEntityUtils.getHttpEntityFrom;
import static com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.UrlParsingUtils.appendPathToHost;

/**
 * Class that executes {@link RestOperations}.exchange() and from {@link org.springframework.http.ResponseEntity#headers}
 * it returns {@link org.springframework.http.HttpHeaders#LOCATION} header
 */
public abstract class LocationFindingExecutor implements LocationReceiving {
    public LocationFindingExecutor(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    protected final Parameters params = new Parameters();

    protected final RestOperations restOperations;

    protected abstract HttpMethod getHttpMethod();

    @Override
    public URI forLocation() {
        if (params.hasUrl()) {
            return getLocation(restOperations.exchange(URI.create(appendPathToHost(params.host, params.url)), getHttpMethod(), getHttpEntityFrom(params), params.request.getClass()));
        } else if (params.hasUrlTemplate()) {
            final Object[] objects = params.urlVariablesArray;
            return getLocation(restOperations.exchange(appendPathToHost(params.host, params.urlTemplate), getHttpMethod(), getHttpEntityFrom(params), params.request.getClass(), objects != null ? objects : params.urlVariablesMap));
        }

        throw new InvalidHttpMethodParametersException(params);
    }
    private static URI getLocation(HttpEntity entity) {
        return (entity == null ? null : entity.getHeaders()).getLocation();
    }
}
