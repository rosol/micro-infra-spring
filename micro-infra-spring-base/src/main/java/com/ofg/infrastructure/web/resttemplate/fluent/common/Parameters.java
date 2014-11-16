package com.ofg.infrastructure.web.resttemplate.fluent.common;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.util.Map;

public class Parameters {
    public String host;
    public Object request;
    public URI url;
    public String urlTemplate;
    public Object[] urlVariablesArray;
    public Map<String, ?> urlVariablesMap;
    public HttpEntity httpEntity;
    public HttpHeaders headers;

    public boolean hasUrl() {
        return url != null;
    }

    public boolean hasUrlTemplate() {
        return StringUtils.isNotBlank(urlTemplate);
    }

    public boolean hasHttpEntity() {
        return httpEntity != null;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this).toString();
    }
}

