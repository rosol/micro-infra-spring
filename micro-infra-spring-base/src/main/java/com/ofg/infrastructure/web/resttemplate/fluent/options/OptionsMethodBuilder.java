package com.ofg.infrastructure.web.resttemplate.fluent.options;

import com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.*;
import groovy.lang.Delegate;
import groovy.transform.TypeChecked;
import org.springframework.http.*;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of the {@link org.springframework.http.HttpMethod#HEAD method} fluent API
 */
public class OptionsMethodBuilder implements AllowHeaderReceiving, OptionsMethod, UrlParameterizableOptionsMethod, ResponseReceivingOptionsMethod, HeadersHaving {
    public OptionsMethodBuilder(String host, RestOperations restOperations) {
        this.restOperations = restOperations;
        params.host = host;
        withHeaders = new AllowContainingWithHeaders(this, params);
        allowHeaderExecutor = new OptionsAllowHeaderExecutor(params, restOperations);
    }

    public OptionsMethodBuilder(RestOperations restOperations) {
        this(EMPTY_HOST, restOperations);
    }

    @Override
    public ResponseReceivingOptionsMethod onUrl(URI url) {
        params.url = url;
        return this;
    }

    @Override
    public ResponseReceivingOptionsMethod onUrl(String url) {
        params.url = new URI(url);
        return this;
    }

    @Override
    public ResponseReceivingOptionsMethod httpEntity(HttpEntity httpEntity) {
        params.httpEntity = httpEntity;
        return this;
    }

    @Override
    public UrlParameterizableOptionsMethod onUrlFromTemplate(String urlTemplate) {
        params.urlTemplate = urlTemplate;
        return this;
    }

    @Override
    public ResponseReceivingOptionsMethod withVariables(Object... urlVariables) {
        params.urlVariablesArray = urlVariables;
        return this;
    }

    @Override
    public ResponseReceivingOptionsMethod withVariables(Map<String, ?> urlVariables) {
        params.urlVariablesMap = urlVariables;
        return this;
    }

    @Override
    public Set<HttpMethod> allow() {
        return allowHeaderExecutor.allow();
    }

    @Override
    public ObjectReceiving anObject() {
        return new ObjectReceiving() {
            @Override
            public <T> T ofType(Class<T> responseType) {
                final ResponseEntity<T> exchange = new OptionsExecuteForResponseTypeRelated<T>(params, restOperations, responseType).exchange();
                return (exchange == null ? null : exchange.getBody());
            }

        };
    }

    @Override
    public ResponseEntityReceiving aResponseEntity() {
        return new ResponseEntityReceiving() {
            @Override
            public <T> ResponseEntity<T> ofType(Class<T> responseType) {
                return new OptionsExecuteForResponseTypeRelated<T>(params, restOperations, responseType).exchange();
            }

        };
    }

    @Override
    public void ignoringResponse() {
        aResponseEntity().ofType(Object.class);
    }

    public HeadersSetting<ResponseReceivingOptionsMethod> withHeaders() {//todo
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

    public HeadersSetting<ResponseReceivingOptionsMethod> contentTypeJson() {//todo
    }

    public HeadersSetting<ResponseReceivingOptionsMethod> contentTypeXml() {//todo
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

    public HeadersSetting<ResponseReceivingOptionsMethod> headers(HttpHeaders httpHeaders) {//todo
    }

    public void updateHeaderParams() {//todo
    }

    public ResponseReceivingOptionsMethod andExecuteFor() {//todo
    }

    public static final String EMPTY_HOST = "";
    private final Map params = new LinkedHashMap();
    private final RestOperations restOperations;
    @Delegate
    private final AllowContainingWithHeaders withHeaders;
    @Delegate
    private final OptionsAllowHeaderExecutor allowHeaderExecutor;
}
