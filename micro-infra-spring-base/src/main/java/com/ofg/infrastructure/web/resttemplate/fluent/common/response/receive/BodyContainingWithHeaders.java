package com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive;

import java.util.Map;

/**
 * Abstraction over {@link com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.WithHeaders} with explicitly provided type {@link com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.ResponseReceiving}
 * so that the compiler resolves types properly
 */
public class BodyContainingWithHeaders extends WithHeaders<ResponseReceiving> {
    public BodyContainingWithHeaders(ResponseReceiving parent, Map<String, String> params) {
        super(parent, params);
    }
}
