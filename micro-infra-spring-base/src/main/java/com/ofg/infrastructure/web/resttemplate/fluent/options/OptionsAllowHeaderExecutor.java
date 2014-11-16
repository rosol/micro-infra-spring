package com.ofg.infrastructure.web.resttemplate.fluent.options;

import com.ofg.infrastructure.web.resttemplate.fluent.common.Parameters;
import com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.InvalidHttpMethodParametersException;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.Set;

import static com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.HttpEntityUtils.getHttpEntityFrom;
import static com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.UrlParsingUtils.appendPathToHost;
import static org.springframework.http.HttpMethod.OPTIONS;

/**
 * Class that executes {@link RestOperations}.exchange() and from {@link org.springframework.http.ResponseEntity#headers}
 * it returns {@link org.springframework.http.HttpHeaders#ALLOW} header
 */
class OptionsAllowHeaderExecutor implements AllowHeaderReceiving {
    private final Parameters params;
    private final RestOperations restOperations;

    public OptionsAllowHeaderExecutor(Parameters params, RestOperations restOperations) {
        this.params = params;
        this.restOperations = restOperations;
    }

    @Override
    public Set<HttpMethod> allow() {
        if (params.hasUrl()) {
            ResponseEntity response = restOperations.exchange(URI.create(appendPathToHost(params.host, params.url)), OPTIONS, getHttpEntityFrom(params), Object.class);
            return response.getHeaders().getAllow();
        } else if (params.hasUrlTemplate()) {
            Object[] objects = params.urlVariablesArray;
            ResponseEntity response = restOperations.exchange(params.host + params.urlTemplate, OPTIONS, getHttpEntityFrom(params), Object.class, objects != null ? objects : params.urlVariablesMap);
            return response.getHeaders().getAllow();
        }
        throw new InvalidHttpMethodParametersException(params);
    }
}
