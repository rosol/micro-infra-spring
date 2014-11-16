package com.ofg.infrastructure.web.resttemplate.fluent.put;

import com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.LocationFindingExecutor;
import com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.*;
import org.springframework.http.*;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpMethod.PUT;

/**
 * Implementation of the {@link org.springframework.http.HttpMethod#PUT method} fluent API
 */
public class PutMethodBuilder extends LocationFindingExecutor implements PutMethod, RequestHavingPutMethod, ResponseReceivingPutMethod, UrlParameterizablePutMethod, HeadersSetting {
    public static final String EMPTY_HOST = "";

    private final BodyContainingWithHeaders withHeaders;

    public PutMethodBuilder(String host, RestOperations restOperations) {
        super(restOperations);
        params.host = host;
        withHeaders = new BodyContainingWithHeaders(this, params);
    }

    public PutMethodBuilder(RestOperations restOperations) {
        this(EMPTY_HOST, restOperations);
    }

    @Override
    protected HttpMethod getHttpMethod() {
        return PUT;
    }

    @Override
    public RequestHavingPutMethod onUrl(URI url) {
        params.url = url;
        return this;
    }

    @Override
    public RequestHavingPutMethod onUrl(String url) {
        params.url = URI.create(url);
        return this;
    }

    @Override
    public ResponseReceivingPutMethod httpEntity(HttpEntity httpEntity) {
        params.httpEntity = httpEntity;
        return this;
    }

    @Override
    public UrlParameterizablePutMethod onUrlFromTemplate(String urlTemplate) {
        params.urlTemplate = urlTemplate;
        return this;
    }

    @Override
    public RequestHavingPutMethod withVariables(Object... urlVariables) {
        params.urlVariablesArray = urlVariables;
        return this;
    }

    @Override
    public RequestHavingPutMethod withVariables(Map<String, ?> urlVariables) {
        params.urlVariablesMap = urlVariables;
        return this;
    }

    @Override
    public ResponseReceivingPutMethod body(Object request) {
        params.request = request;
        return this;
    }

    @Override
    public ResponseReceivingPutMethod withoutBody() {
        params.request = null;
        return this;
    }

    @Override
    public ObjectReceiving anObject() {
        return new ObjectReceiving() {
            @Override
            public <T> T ofType(Class<T> responseType) {
                final ResponseEntity<T> exchange = new PutExecuteForResponseTypeRelated<T>(params, restOperations, responseType).exchange();
                return (exchange == null ? null : exchange.getBody());
            }

        };
    }

    @Override
    public ResponseEntityReceiving aResponseEntity() {
        return new ResponseEntityReceiving() {
            @Override
            public <T> ResponseEntity<T> ofType(Class<T> responseType) {
                return new PutExecuteForResponseTypeRelated<T>(params, restOperations, responseType).exchange();
            }

        };
    }

    @Override
    public void ignoringResponse() {
        aResponseEntity().ofType(Object.class);
    }

    public HeadersSetting<ResponseReceiving> withHeaders() {
        return withHeaders.withHeaders();
    }

    public ResponseReceiving andExecuteFor() {
        return withHeaders.andExecuteFor();
    }

    public HeadersSetting accept(List acceptableMediaTypes) {
        return withHeaders.accept(acceptableMediaTypes);
    }

    public HeadersSetting accept(MediaType[] acceptableMediaTypes) {
        return withHeaders.accept(acceptableMediaTypes);
    }

    public HeadersSetting cacheControl(String cacheControl) {
        return withHeaders.cacheControl(cacheControl);
    }

    public HeadersSetting contentType(MediaType mediaType) {
        return withHeaders.contentType(mediaType);
    }

    public HeadersSetting contentType(String contentType) {
        return withHeaders.contentType(contentType);
    }

    public HeadersSetting contentTypeJson() {
        return withHeaders.contentTypeJson();
    }

    public HeadersSetting contentTypeXml() {
        return withHeaders.contentTypeXml();
    }

    public HeadersSetting expires(long expires) {
        return withHeaders.expires(expires);
    }

    public HeadersSetting lastModified(long lastModified) {
        return withHeaders.lastModified(lastModified);
    }

    public HeadersSetting location(URI location) {
        return withHeaders.location(location);
    }

    public HeadersSetting header(String headerName, String headerValue) {
        return withHeaders.header(headerName, headerValue);
    }

    public HeadersSetting headers(Map values) {
        return withHeaders.headers(values);
    }

    public HeadersSetting headers(HttpHeaders httpHeaders) {
        return withHeaders.headers(httpHeaders);
    }
}
