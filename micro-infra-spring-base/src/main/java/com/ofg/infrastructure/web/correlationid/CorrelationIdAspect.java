package com.ofg.infrastructure.web.correlationid;

import com.ofg.infrastructure.correlationid.CorrelationCallable;
import com.ofg.infrastructure.correlationid.CorrelationIdHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Aspect that adds correlation id to
 * <p/>
 * <ul>
 * <li>{@link org.springframework.web.bind.annotation.RestController} annotated classes
 * with public {@link Callable} methods</li>
 * <li>{@link org.springframework.stereotype.Controller} annotated classes
 * with public {@link Callable} methods</li>
 * <li>explicit {@link org.springframework.web.client.RestOperations}.exchange(..) method calls</li>
 * </ul>
 * <p/>
 * For controllers an around aspect is created that wraps the {@link Callable#call()} method execution
 * in {@link com.ofg.infrastructure.correlationid.CorrelationCallable#withCorrelationId(java.util.concurrent.Callable)}
 * <p/>
 * For {@link org.springframework.web.client.RestOperations} we are wrapping all executions of the
 * <b>exchange</b> methods and we are extracting {@link HttpHeaders} from the passed {@link HttpEntity}.
 * Next we are adding correlation id header {@link com.ofg.infrastructure.correlationid.CorrelationIdHolder#CORRELATION_ID_HEADER} with
 * the value taken from {@link com.ofg.infrastructure.correlationid.CorrelationIdHolder}. Finally the method execution proceeds.
 *
 * @see org.springframework.web.bind.annotation.RestController
 * @see org.springframework.stereotype.Controller
 * @see org.springframework.web.client.RestOperations
 * @see com.ofg.infrastructure.correlationid.CorrelationIdHolder
 * @see com.ofg.infrastructure.web.correlationid.CorrelationIdFilter
 */
@Aspect
public class CorrelationIdAspect {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final int HTTP_ENTITY_PARAM_INDEX = 2;

    @Pointcut("@target(org.springframework.web.bind.annotation.RestController)")
    private void anyRestControllerAnnotated() {
    }

    @Pointcut("@target(org.springframework.stereotype.Controller)")
    private void anyControllerAnnotated() {
    }

    @Pointcut("execution(public java.util.concurrent.Callable *(..))")
    private void anyPublicMethodReturningCallable() {
    }

    @Pointcut("(anyRestControllerAnnotated() || anyControllerAnnotated()) && anyPublicMethodReturningCallable()")
    private void anyControllerOrRestControllerWithPublicAsyncMethod() {
    }

    @Around("anyControllerOrRestControllerWithPublicAsyncMethod()")
    public Object wrapWithCorrelationId(ProceedingJoinPoint pjp) throws Throwable {
        final Callable callable = (Callable) pjp.proceed();
        log.debug("Wrapping callable with correlation id [" + CorrelationIdHolder.get() + "]");
        return CorrelationCallable.withCorrelationId(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return callable.call();
            }
        });
    }

    @Pointcut("execution(public * org.springframework.web.client.RestOperations.exchange(..))")
    private void anyExchangeRestOperationsMethod() {
    }

    @Around("anyExchangeRestOperationsMethod()")
    public Object wrapWithCorrelationIdForRestOperations(ProceedingJoinPoint pjp) throws Throwable {
        String correlationId = CorrelationIdHolder.get();
        log.debug("Wrapping RestTemplate call with correlation id [" + correlationId + "]");
        HttpEntity httpEntity = (HttpEntity) pjp.getArgs()[HTTP_ENTITY_PARAM_INDEX];
        HttpEntity newHttpEntity = createNewHttpEntity(httpEntity, correlationId);
        List<Object> newArgs = modifyHttpEntityInMethodArguments(pjp, newHttpEntity);
        return pjp.proceed(newArgs.toArray());
    }

    @SuppressWarnings("unchecked")
    private HttpEntity createNewHttpEntity(HttpEntity httpEntity, String correlationId) {
        HttpHeaders newHttpHeaders = new HttpHeaders();
        newHttpHeaders.putAll(httpEntity.getHeaders());
        newHttpHeaders.add(CorrelationIdHolder.CORRELATION_ID_HEADER, correlationId);
        return new HttpEntity(httpEntity.getBody(), newHttpHeaders);
    }

    private List<Object> modifyHttpEntityInMethodArguments(ProceedingJoinPoint pjp, final HttpEntity newHttpEntity) {
        final List<Object> newArgs = new ArrayList<Object>();
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (i != HTTP_ENTITY_PARAM_INDEX) {
                newArgs.add(i, args[i]);
            } else {
                newArgs.add(i, newHttpEntity);
            }
        }
        return newArgs;
    }
}
