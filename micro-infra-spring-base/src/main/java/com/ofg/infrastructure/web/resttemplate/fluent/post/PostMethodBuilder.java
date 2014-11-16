package com.ofg.infrastructure.web.resttemplate.fluent.post;

import com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.LocationFindingExecutor;
import com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.*;
import groovy.lang.GString;
import org.springframework.http.*;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpMethod.POST;

/**
 * Implementation of the {@link org.springframework.http.HttpMethod#POST method} fluent API
 */
public class PostMethodBuilder extends LocationFindingExecutor implements PostMethod, RequestHavingPostMethod, ResponseReceivingPostMethod, UrlParameterizablePostMethod, HeadersSetting {
    public static final String EMPTY_HOST = "";

    private final BodyContainingWithHeaders withHeaders;

    public PostMethodBuilder(String host, RestOperations restOperations) {
        super(restOperations);
        params.host = host;
        withHeaders = new BodyContainingWithHeaders(this, params);
    }

    public PostMethodBuilder(RestOperations restOperations) {
        this(EMPTY_HOST, restOperations);
    }

    @Override
    protected HttpMethod getHttpMethod() {
        return POST;
    }

    @Override
    public RequestHavingPostMethod onUrl(URI url) {
        params.url = url;
        return this;
    }

    @Override
    public RequestHavingPostMethod onUrl(String url) {
        params.url = URI.create(url);
        return this;
    }

    @Override
    public ResponseReceivingPostMethod httpEntity(HttpEntity httpEntity) {
        params.httpEntity = httpEntity;
        return this;
    }

    @Override
    public UrlParameterizablePostMethod onUrlFromTemplate(String urlTemplate) {
        params.urlTemplate = urlTemplate;
        return this;
    }

    @Override
    public RequestHavingPostMethod withVariables(Object... urlVariables) {
        params.urlVariablesArray = urlVariables;
        return this;
    }

    @Override
    public RequestHavingPostMethod withVariables(Map<String, ?> urlVariables) {
        params.urlVariablesMap = urlVariables;
        return this;
    }

    @Override
    public ResponseReceivingPostMethod body(Object request) {
        Object requestToSet = (request instanceof GString ? ((GString) request).toString() : request);
        params.request = requestToSet;
        return this;
    }

    @Override
    public ResponseReceivingPostMethod withoutBody() {
        params.request = null;
        return this;
    }

    @Override
    public ObjectReceiving anObject() {
        return new ObjectReceiving() {
            @Override
            public <T> T ofType(Class<T> responseType) {
                final ResponseEntity<T> exchange = new PostExecuteForResponseTypeRelated<T>(params, restOperations, responseType).exchange();
                return (exchange == null ? null : exchange.getBody());
            }

        };
    }

    @Override
    public ResponseEntityReceiving aResponseEntity() {
        return new ResponseEntityReceiving() {
            @Override
            public <T> ResponseEntity<T> ofType(Class<T> responseType) {
                return new PostExecuteForResponseTypeRelated<T>(params, restOperations, responseType).exchange();
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
