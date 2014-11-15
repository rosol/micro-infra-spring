package com.ofg.infrastructure.web.correlationid;

import com.ofg.infrastructure.correlationid.CorrelationIdHolder;
import groovy.lang.Closure;
import groovy.transform.CompileStatic;
import groovy.util.logging.Slf4j;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.UUID;

import static com.ofg.infrastructure.correlationid.CorrelationIdHolder.CORRELATION_ID_HEADER;
import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * Filter that takes the value of the {@link com.ofg.infrastructure.correlationid.CorrelationIdHolder#CORRELATION_ID_HEADER} header
 * from either request or response and sets it in the {@link com.ofg.infrastructure.correlationid.CorrelationIdHolder}. It also provides
 * that value in {@link org.apache.log4j.MDC} logging related class so that logger prints the value of
 * correlation id at each log.
 *
 * @see com.ofg.infrastructure.correlationid.CorrelationIdHolder
 * @see org.apache.log4j.MDC
 */
public class CorrelationIdFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        setupCorrelationId(request, response);
        try {
            filterChain.doFilter(request, response);
        } finally {
            cleanupCorrelationId();
        }

    }

    private void setupCorrelationId(HttpServletRequest request, HttpServletResponse response) {
        final String from = getCorrelationIdFrom(request);
        String correlationId = isNotBlank(from) ? from : getCorrelationIdFrom(response);
        correlationId = createNewCorrIdIfEmpty(correlationId);
        CorrelationIdHolder.set(correlationId);
        addCorrelationIdToResponseIfNotPresent(response, correlationId);
    }

    private String getCorrelationIdFrom(final HttpServletResponse response) {
        return withLoggingAs("response", new Closure<String>(this, this) {
            public String doCall(Object it) {
                return response.getHeader(CORRELATION_ID_HEADER);
            }

            public String doCall() {
                return doCall(null);
            }

        });
    }

    private String getCorrelationIdFrom(final HttpServletRequest request) {
        return withLoggingAs("request", new Closure<String>(this, this) {
            public String doCall(Object it) {
                return request.getHeader(CORRELATION_ID_HEADER);
            }

            public String doCall() {
                return doCall(null);
            }

        });
    }

    private String withLoggingAs(String whereWasFound, Closure correlationIdGetter) {
        String correlationId = correlationIdGetter.call();
        if (StringUtils.hasText(correlationId)) {
            MDC.put(CORRELATION_ID_HEADER, correlationId);
            log.debug("Found correlationId in " + whereWasFound + ": " + correlationId);
        }

        return correlationId;
    }

    private String createNewCorrIdIfEmpty(String currentCorrId) {
        if (!StringUtils.hasText(currentCorrId)) {
            currentCorrId = UUID.randomUUID().toString();
            MDC.put(CORRELATION_ID_HEADER, currentCorrId);
            log.debug("Generating new correlationId: " + currentCorrId);
        }

        return currentCorrId;
    }

    private void addCorrelationIdToResponseIfNotPresent(HttpServletResponse response, String correlationId) {
        if (!StringUtils.hasText(response.getHeader(CORRELATION_ID_HEADER))) {
            response.addHeader(CORRELATION_ID_HEADER, correlationId);
        }

    }

    private void cleanupCorrelationId() {
        MDC.remove(CORRELATION_ID_HEADER);
        CorrelationIdHolder.remove();
    }

    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return false;
    }

}
