package com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor;

import com.ofg.infrastructure.web.resttemplate.fluent.common.Parameters;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import java.net.URI;

import static com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.HttpEntityUtils.getHttpEntityFrom;
import static com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.UrlParsingUtils.appendPathToHost;

/**
 * Abstraction over {@link RestOperations} that for a {@link com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.ResponseTypeRelatedRequestsExecutor#getHttpMethod()}
 * checks whether user passed an URL or a template. Basing on this we create an execute a request.
 * <p/>
 * The parameters passed to the <b>exchange</b> method are set based on whether user wants to call
 * an external or internal service and whether he wants to call a direct URL or wants to do it from a template.
 * If the user passes relative path (i.e. ws/api and wants to call a service http://4finance.net) the abstraction
 * via {@link com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.UrlParsingUtils} will prepend a slash to the URL thus it will call http://4finance.net/ws/api.
 * For more details check {@link com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.UrlParsingUtils}
 *
 * @param < T >
 * @see com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.UrlParsingUtils
 * @see RestOperations
 */
public abstract class ResponseTypeRelatedRequestsExecutor<T> {
    protected final RestOperations restOperations;
    protected final Parameters params;
    protected final Class<T> responseType;

    public ResponseTypeRelatedRequestsExecutor(Parameters params, RestOperations restOperations, Class<T> responseType) {
        this.restOperations = restOperations;
        this.params = params;
        this.responseType = responseType;
    }

    protected abstract HttpMethod getHttpMethod();

    public ResponseEntity<T> exchange() {
        if (params.hasUrl()) {
            return restOperations.exchange(URI.create(appendPathToHost(params.host, params.url)), getHttpMethod(), getHttpEntityFrom(params), responseType);
        } else if (params.hasUrlTemplate()) {
            final Object[] objects = params.urlVariablesArray;
            return restOperations.exchange(appendPathToHost(params.host, params.urlTemplate), getHttpMethod(), getHttpEntityFrom(params), responseType, objects != null ? objects : params.urlVariablesMap);
        }
        throw new InvalidHttpMethodParametersException(params);
    }
}
