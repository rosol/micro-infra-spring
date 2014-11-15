package com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor;

import java.util.Map;

public class InvalidHttpMethodParametersException extends RuntimeException {
    public InvalidHttpMethodParametersException(Map params) {
        super("Invalid args [" + String.valueOf(params) + "] passed to method");
    }
}
