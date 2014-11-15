package com.ofg.infrastructure.web.resttemplate.fluent.options;

import com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.HttpEntityUtils;
import com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.InvalidHttpMethodParametersException;
import groovy.transform.PackageScope;
import groovy.transform.TypeChecked;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.Map;
import java.util.Set;

import static org.springframework.http.HttpMethod.OPTIONS;

/**
 * Class that executes {@link RestOperations}.exchange() and from {@link org.springframework.http.ResponseEntity#headers}
 * it returns {@link org.springframework.http.HttpHeaders#ALLOW} header
 */
class OptionsAllowHeaderExecutor implements AllowHeaderReceiving {
    public OptionsAllowHeaderExecutor(Map params, RestOperations restOperations) {
        this.params = params;
        this.restOperations = restOperations;
    }

    @Override
    public Set<HttpMethod> allow() {
        if (params.url.asBoolean()) {
            ResponseEntity response = restOperations.exchange((URI) params.url, OPTIONS, HttpEntityUtils.getHttpEntityFrom(params), Object.class);
            return response.getHeaders().getAllow();
        } else if (params.urlTemplate.asBoolean()) {
            final Object[] objects = (Object[]) params.urlVariablesArray;
            ResponseEntity response = restOperations.exchange(String.valueOf(params.host) + String.valueOf(params.urlTemplate), OPTIONS, HttpEntityUtils.getHttpEntityFrom(params), Object.class, DefaultGroovyMethods.asBoolean(objects) ? objects : (Map<String, ?>) params.urlVariablesMap);
            return response.getHeaders().getAllow();
        }

        throw new InvalidHttpMethodParametersException(params);
    }

    private final Map params;
    private final RestOperations restOperations;
}
