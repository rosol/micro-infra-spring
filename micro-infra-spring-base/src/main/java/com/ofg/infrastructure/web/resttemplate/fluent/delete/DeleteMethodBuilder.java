package com.ofg.infrastructure.web.resttemplate.fluent.delete;

import com.ofg.infrastructure.web.resttemplate.fluent.common.Parameters;
import com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.BodylessWithHeaders;
import com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.HeadersHaving;
import com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.HeadersSetting;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the {@link org.springframework.http.HttpMethod#DELETE method} fluent API
 */
public class DeleteMethodBuilder implements DeleteMethod, UrlParameterizableDeleteMethod, ResponseReceivingDeleteMethod, HeadersHaving<ResponseReceivingDeleteMethod> {

    public static final String EMPTY_HOST = "";

    private final Parameters params = new Parameters();
    private final RestOperations restOperations;
    private final BodylessWithHeaders<ResponseReceivingDeleteMethod> withHeaders;

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
        params.url = URI.create(url);
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

    public HeadersSetting<ResponseReceivingDeleteMethod> withHeaders() {
        return withHeaders.withHeaders();
    }

    public ResponseReceivingDeleteMethod andExecuteFor() {
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
