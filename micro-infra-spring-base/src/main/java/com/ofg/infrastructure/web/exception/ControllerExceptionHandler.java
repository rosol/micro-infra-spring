package com.ofg.infrastructure.web.exception;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
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
import java.util.Collection;
import java.util.LinkedHashMap;
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

    private static final String INTERNAL_ERROR = "internal error";

    @ExceptionHandler(BadParametersException.class)
    @ResponseBody
    public Collection<BadParameterError> handleBadParametersExceptions(BadParametersException exception, HttpServletResponse response) {
        response.setStatus(SC_BAD_REQUEST);
        return getListOfBindErrors(exception);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, String> handleAnyOtherExceptions(Exception exception, HttpServletResponse response) {
        response.setStatus(SC_BAD_REQUEST);
        log.error("Unexpected exception: ", exception);
        Map<String, String> map = new LinkedHashMap<String, String>(2);
        map.put("error", INTERNAL_ERROR);
        map.put("message", exception.getMessage());
        return map;
    }

    private Collection<BadParameterError> getListOfBindErrors(BadParametersException exception) {
        return Collections2.transform(exception.getErrors(), new Function<ObjectError, BadParameterError>() {
            @Override
            public BadParameterError apply(ObjectError error) {
                return getBindError(error);
            }
        });
    }

    private BadParameterError getBindError(ObjectError error) {
        //TODO: Was -> error.properties['field'].toString() but I guess sth's wrong
        return new BadParameterError(error.getObjectName(), error.getDefaultMessage());
    }
}
