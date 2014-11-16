package com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive;

import com.ofg.infrastructure.web.resttemplate.fluent.common.Parameters;

public class BodylessWithHeaders<T> extends WithHeaders<T> {
    public BodylessWithHeaders(T parent, Parameters params) {
        super(parent, params);
    }
}
