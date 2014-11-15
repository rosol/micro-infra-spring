package com.ofg.infrastructure.web.resttemplate.fluent.head;

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
 * Implementation of the {@link org.springframework.http.HttpMethod#HEAD method} fluent API
 */
public class HeadMethodBuilder implements HeadMethod, UrlParameterizableHeadMethod, ResponseReceivingHeadMethod, HeadersHaving {
    public HeadMethodBuilder(String host, RestOperations restOperations) {
        this.restOperations = restOperations;
        params.host = host;
        withHeaders = new BodylessWithHeaders<ResponseReceivingHeadMethod>(this, params);
    }

    public HeadMethodBuilder(RestOperations restOperations) {
        this(EMPTY_HOST, restOperations);
    }

    @Override
    public ResponseReceivingHeadMethod onUrl(URI url) {
        params.url = url;
        return this;
    }

    @Override
    public ResponseReceivingHeadMethod onUrl(String url) {
        params.url = new URI(url);
        return this;
    }

    @Override
    public ResponseReceivingHeadMethod httpEntity(HttpEntity httpEntity) {
        params.httpEntity = httpEntity;
        return this;
    }

    @Override
    public UrlParameterizableHeadMethod onUrlFromTemplate(String urlTemplate) {
        params.urlTemplate = urlTemplate;
        return this;
    }

    @Override
    public ResponseReceivingHeadMethod withVariables(Object... urlVariables) {
        params.urlVariablesArray = urlVariables;
        return this;
    }

    @Override
    public ResponseReceivingHeadMethod withVariables(Map<String, ?> urlVariables) {
        params.urlVariablesMap = urlVariables;
        return this;
    }

    @Override
    public ResponseEntity aResponseEntity() {
        return new HeadExecuteForResponseTypeRelated(params, restOperations).exchange();
    }

    @Override
    public HttpHeaders httpHeaders() {
        final ResponseEntity<Object> exchange = new HeadExecuteForResponseTypeRelated(params, restOperations).exchange();
        return (exchange == null ? null : exchange.getHeaders());
    }

    @Override
    public void ignoringResponse() {
        aResponseEntity();
    }

    public HeadersSetting<ResponseReceivingHeadMethod> withHeaders() {//todo
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

    public HeadersSetting<ResponseReceivingHeadMethod> contentTypeJson() {//todo
    }

    public HeadersSetting<ResponseReceivingHeadMethod> contentTypeXml() {//todo
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

    public HeadersSetting<ResponseReceivingHeadMethod> headers(HttpHeaders httpHeaders) {//todo
    }

    public void updateHeaderParams() {//todo
    }

    public ResponseReceivingHeadMethod andExecuteFor() {//todo
    }

    public static final String EMPTY_HOST = "";
    private final Map params = new LinkedHashMap();
    private final RestOperations restOperations;
    @Delegate
    private final BodylessWithHeaders<ResponseReceivingHeadMethod> withHeaders;
}
