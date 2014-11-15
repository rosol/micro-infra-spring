package com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor;

import groovy.transform.TypeChecked;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class that executes {@link RestOperations}.exchange() and from {@link org.springframework.http.ResponseEntity#headers}
 * it returns {@link org.springframework.http.HttpHeaders#LOCATION} header
 */
public abstract class LocationFindingExecutor implements LocationReceiving {
    public LocationFindingExecutor(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    protected abstract HttpMethod getHttpMethod();

    @Override
    public URI forLocation() {
        if (params.url.asBoolean()) {
            return getLocation(restOperations.exchange(new URI(UrlParsingUtils.appendPathToHost((String) params.host, (URI) params.url)), getHttpMethod(), HttpEntityUtils.getHttpEntityFrom(params), params.request.getClass()));
        } else if (params.urlTemplate.asBoolean()) {
            final Object[] objects = (Object[]) params.urlVariablesArray;
            return getLocation(restOperations.exchange(UrlParsingUtils.appendPathToHost((String) params.host, (String) params.urlTemplate), getHttpMethod(), HttpEntityUtils.getHttpEntityFrom(params), params.request.getClass(), DefaultGroovyMethods.asBoolean(objects) ? objects : (Map<String, ?>) params.urlVariablesMap));
        }

        throw new InvalidHttpMethodParametersException(params);
    }

    private static URI getLocation(HttpEntity entity) {
        return (entity == null ? null : entity.getHeaders()).getLocation();
    }

    protected final Map params = new LinkedHashMap();
    protected final RestOperations restOperations;
}
