package com.ofg.infrastructure.web.resttemplate.fluent.get;

import com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.*;
import groovy.lang.Delegate;
import groovy.transform.TypeChecked;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the {@link org.springframework.http.HttpMethod#GET method} fluent API
 */
public class GetMethodBuilder implements GetMethod, UrlParameterizableGetMethod, ResponseReceivingGetMethod, HeadersHaving {
    public GetMethodBuilder(String host, RestOperations restOperations) {
        this.restOperations = restOperations;
        params.host = host;
        withHeaders = new BodyContainingWithHeaders(this, params);
    }

    public GetMethodBuilder(RestOperations restOperations) {
        this(EMPTY_HOST, restOperations);
    }

    @Override
    public ResponseReceivingGetMethod onUrl(URI url) {
        params.url = url;
        return this;
    }

    @Override
    public ResponseReceivingGetMethod onUrl(String url) {
        params.url = new URI(url);
        return this;
    }

    @Override
    public ResponseReceivingGetMethod httpEntity(HttpEntity httpEntity) {
        params.httpEntity = httpEntity;
        return this;
    }

    @Override
    public UrlParameterizableGetMethod onUrlFromTemplate(String urlTemplate) {
        params.urlTemplate = urlTemplate;
        return this;
    }

    @Override
    public ResponseReceivingGetMethod withVariables(Object... urlVariables) {
        params.urlVariablesArray = urlVariables;
        return this;
    }

    @Override
    public ResponseReceivingGetMethod withVariables(Map<String, ?> urlVariables) {
        params.urlVariablesMap = urlVariables;
        return this;
    }

    @Override
    public ObjectReceiving anObject() {
        return new ObjectReceiving() {
            @Override
            public <T> T ofType(Class<T> responseType) {
                final ResponseEntity<T> exchange = new GetExecuteForResponseTypeRelated<T>(params, restOperations, responseType).exchange();
                return (exchange == null ? null : exchange.getBody());
            }

        };
    }

    @Override
    public ResponseEntityReceiving aResponseEntity() {
        return new ResponseEntityReceiving() {
            @Override
            public <T> ResponseEntity<T> ofType(Class<T> responseType) {
                return new GetExecuteForResponseTypeRelated<T>(params, restOperations, responseType).exchange();
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

    public WithHeaders accept(List<MediaType> acceptableMediaTypes) {//todo
    }

    public WithHeaders accept(MediaType... acceptableMediaTypes) {//todo
    }

    public WithHeaders cacheControl(String cacheControl) {//todo
    }

    public WithHeaders contentType(MediaType mediaType) {//todo
    }

    public HeadersSetting<ResponseReceiving> contentType(String contentType) {//todo
    }

    public HeadersSetting<ResponseReceiving> contentTypeJson() {//todo
    }

    public HeadersSetting<ResponseReceiving> contentTypeXml() {//todo
    }

    public WithHeaders expires(long expires) {//todo
    }

    public WithHeaders lastModified(long lastModified) {//todo
    }

    public WithHeaders location(URI location) {//todo
    }

    public WithHeaders header(String headerName, String headerValue) {//todo
    }

    public WithHeaders headers(Map<String, String> values) {//todo
    }

    public HeadersSetting<ResponseReceiving> headers(HttpHeaders httpHeaders) {//todo
    }

    public void updateHeaderParams() {//todo
    }

    public static final String EMPTY_HOST = "";
    private final Map params = new LinkedHashMap();
    private final RestOperations restOperations;
    @Delegate
    private final BodyContainingWithHeaders withHeaders;
}
