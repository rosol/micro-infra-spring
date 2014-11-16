package com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor;

import com.ofg.infrastructure.web.resttemplate.fluent.common.Parameters;

public class InvalidHttpMethodParametersException extends RuntimeException {
    public InvalidHttpMethodParametersException(Parameters params) {
        super("Invalid args [" + String.valueOf(params) + "] passed to method");
    }
}
