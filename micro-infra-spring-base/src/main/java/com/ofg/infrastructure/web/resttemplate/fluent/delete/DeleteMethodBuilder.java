package com.ofg.infrastructure.web.resttemplate.fluent.delete;

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
 * Implementation of the {@link org.springframework.http.HttpMethod#DELETE method} fluent API
 */
public class DeleteMethodBuilder implements DeleteMethod, UrlParameterizableDeleteMethod, ResponseReceivingDeleteMethod, HeadersHaving {
    public DeleteMethodBuilder(String host, RestOperations restOperations) {
        this.restOperations = restOperations;
        params.host = host;
        withHeaders = new BodylessWithHeaders<ResponseReceivingDeleteMethod>(this, params);
    }

    public DeleteMethodBuilder(RestOperations restOperations) {
        this(EMPTY_HOST, restOperations);
    }

    @Override
    public ResponseReceivingDeleteMethod onUrl(URI url) {
        params.url = url;
        return this;
    }

    @Override
    public ResponseReceivingDeleteMethod onUrl(String url) {
        params.url = new URI(url);
        return this;
    }

    @Override
    public ResponseReceivingDeleteMethod httpEntity(HttpEntity httpEntity) {
        params.httpEntity = httpEntity;
        return this;
    }

    @Override
    public UrlParameterizableDeleteMethod onUrlFromTemplate(String urlTemplate) {
        params.urlTemplate = urlTemplate;
        return this;
    }

    @Override
    public ResponseReceivingDeleteMethod withVariables(Object... urlVariables) {
        params.urlVariablesArray = urlVariables;
        return this;
    }

    @Override
    public ResponseReceivingDeleteMethod withVariables(Map<String, ?> urlVariables) {
        params.urlVariablesMap = urlVariables;
        return this;
    }

    @Override
    public ResponseEntity aResponseEntity() {
        return new DeleteExecuteForResponseTypeRelated(params, restOperations).exchange();
    }

    @Override
    public void ignoringResponse() {
        aResponseEntity();
    }

    public HeadersSetting<ResponseReceivingDeleteMethod> withHeaders() {//todo
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

    public HeadersSetting<ResponseReceivingDeleteMethod> contentTypeJson() {//todo
    }

    public HeadersSetting<ResponseReceivingDeleteMethod> contentTypeXml() {//todo
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

    public HeadersSetting<ResponseReceivingDeleteMethod> headers(HttpHeaders httpHeaders) {//todo
    }

    public void updateHeaderParams() {//todo
    }

    public ResponseReceivingDeleteMethod andExecuteFor() {//todo
    }

    public static final String EMPTY_HOST = "";
    private final Map params = new LinkedHashMap();
    private final RestOperations restOperations;
    @Delegate
    private final BodylessWithHeaders<ResponseReceivingDeleteMethod> withHeaders;
}
