package com.ofg.infrastructure.web.resttemplate.fluent.put;

import com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.LocationFindingExecutor;
import com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.*;
import groovy.lang.Delegate;
import groovy.lang.GString;
import groovy.transform.TypeChecked;
import org.springframework.http.*;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the {@link org.springframework.http.HttpMethod#PUT method} fluent API
 */
public class PutMethodBuilder extends LocationFindingExecutor implements PutMethod, RequestHavingPutMethod, ResponseReceivingPutMethod, UrlParameterizablePutMethod, HeadersSetting {
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
        params.url = new URI(url);
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
        Object requestToSet = (request instanceof GString ? ((GString) request).toString() : request);
        params.request = requestToSet;
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

    public HeadersSetting<ResponseReceiving> withHeaders() {//todo
    }

    public ResponseReceiving andExecuteFor() {//todo
    }

    public HeadersSetting accept(List acceptableMediaTypes) {//todo
    }

    public HeadersSetting accept(MediaType[] acceptableMediaTypes) {//todo
    }

    public HeadersSetting cacheControl(String cacheControl) {//todo
    }

    public HeadersSetting contentType(MediaType mediaType) {//todo
    }

    public HeadersSetting contentType(String contentType) {//todo
    }

    public HeadersSetting contentTypeJson() {//todo
    }

    public HeadersSetting contentTypeXml() {//todo
    }

    public HeadersSetting expires(long expires) {//todo
    }

    public HeadersSetting lastModified(long lastModified) {//todo
    }

    public HeadersSetting location(URI location) {//todo
    }

    public HeadersSetting header(String headerName, String headerValue) {//todo
    }

    public HeadersSetting headers(Map values) {//todo
    }

    public HeadersSetting headers(HttpHeaders httpHeaders) {//todo
    }

    public void updateHeaderParams() {//todo
    }

    public static final String EMPTY_HOST = "";
    @Delegate
    private final BodyContainingWithHeaders withHeaders;
}
