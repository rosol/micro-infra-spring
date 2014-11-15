package com.ofg.infrastructure.web.exception;

import groovy.lang.Closure;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

/**
 * Advice on {@link com.ofg.infrastructure.web.exception.BadParametersException} and {@link Exception} that
 * catches uncaught exceptions, logs and present them
 *
 * @see ControllerAdvice
 * @see ExceptionHandler
 * @see com.ofg.infrastructure.web.exception.BadParametersException
 * @see com.ofg.infrastructure.web.exception.BadParameterError
 */
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class ControllerExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @ExceptionHandler(BadParametersException.class)
    @ResponseBody
    public List<BadParameterError> handleBadParametersExceptions(BadParametersException exception, HttpServletResponse response) {
        response.setStatus(SC_BAD_REQUEST);
        return getListOfBindErrors(exception);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, String> handleAnyOtherExceptions(Exception exception, HttpServletResponse response) {
        response.setStatus(SC_BAD_REQUEST);
        log.error("Unexpected exception: ", exception);
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(2);
        map.put("error", INTERNAL_ERROR);
        map.put("message", exception.getMessage());
        return map;
    }

    private List<BadParameterError> getListOfBindErrors(BadParametersException exception) {
        final List<BadParameterError> bindErrorList = new ArrayList<BadParameterError>();
        DefaultGroovyMethods.each(exception.getErrors(), new Closure<Boolean>(this, this) {
            public Boolean doCall(ObjectError error) {
                return bindErrorList.add(getBindError(error));
            }

        });
        return bindErrorList;
    }

    private BadParameterError getBindError(ObjectError error) {
        return new BadParameterError(DefaultGroovyMethods.getProperties(error).get("field").toString(), error.getDefaultMessage());
    }

    private static final String INTERNAL_ERROR = "internal error";
}
