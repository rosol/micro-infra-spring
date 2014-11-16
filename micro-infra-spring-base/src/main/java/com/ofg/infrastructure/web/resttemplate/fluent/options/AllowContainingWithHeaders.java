package com.ofg.infrastructure.web.resttemplate.fluent.options;

import com.ofg.infrastructure.web.resttemplate.fluent.common.Parameters;
import com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.WithHeaders;

/**
 * Class that provides explicit types for the {@link WithHeaders} so that the compiler
 * know what the types it should return
 */
public class AllowContainingWithHeaders extends WithHeaders<ResponseReceivingOptionsMethod> {
    public AllowContainingWithHeaders(ResponseReceivingOptionsMethod parent, Parameters params) {
        super(parent, params);
    }
}
