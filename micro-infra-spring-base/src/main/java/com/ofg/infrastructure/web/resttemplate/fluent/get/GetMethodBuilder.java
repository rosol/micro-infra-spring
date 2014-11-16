package com.ofg.infrastructure.web.resttemplate.fluent.get;

import com.ofg.infrastructure.web.resttemplate.fluent.common.Parameters;
import com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the {@link org.springframework.http.HttpMethod#GET method} fluent API
 */
public class GetMethodBuilder implements GetMethod, UrlParameterizableGetMethod, ResponseReceivingGetMethod {

    public static final String EMPTY_HOST = "";

    private final Parameters params = new Parameters();
    private final RestOperations restOperations;
    private final BodyContainingWithHeaders withHeaders;

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
        params.url = URI.create(url);
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

    public HeadersSetting<ResponseReceiving> withHeaders() {
        return withHeaders.withHeaders();
    }

    public ResponseExtracting andExecuteFor() {
        return this;
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
