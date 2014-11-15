package com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive;

import groovy.transform.CompileStatic;

import java.util.Map;

public class BodylessWithHeaders<T> extends WithHeaders<T> {
    public BodylessWithHeaders(T parent, Map<String, String> params) {
        super(parent, params);
    }
}
