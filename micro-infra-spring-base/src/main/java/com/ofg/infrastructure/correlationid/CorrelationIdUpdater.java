package com.ofg.infrastructure.correlationid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.lang.invoke.MethodHandles;

import static com.ofg.infrastructure.correlationid.CorrelationIdHolder.CORRELATION_ID_HEADER;

/**
 * Class that takes care of updating all necessary components with new value
 * of correlation id.
 * It sets correlationId on {@link ThreadLocal} in {@link com.ofg.infrastructure.correlationid.CorrelationIdHolder}
 * and in {@link MDC}.
 *
 * @see com.ofg.infrastructure.correlationid.CorrelationIdHolder
 * @see MDC
 */
public class CorrelationIdUpdater {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void updateCorrelationId(String correlationId) {
        if (StringUtils.hasText(correlationId)) {
            log.debug("Updating correlationId with value: [" + correlationId + "]");
            CorrelationIdHolder.set(correlationId);
            MDC.put(CORRELATION_ID_HEADER, correlationId);
        }

    }

}
