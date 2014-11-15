package com.ofg.infrastructure.scheduling;

import com.ofg.infrastructure.correlationid.CorrelationIdUpdater;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.UUID;

/**
 * Aspect that sets correlationId for running threads executing methods annotated with {@link org.springframework.scheduling.annotation.Scheduled} annotation.
 * For every execution of scheduled method a new, i.e. unique one, value of correlationId will be set.
 */
@Aspect
public class ScheduledTaskWithCorrelationIdAspect {
    @Before("execution (@org.springframework.scheduling.annotation.Scheduled  * *.*(..))")
    public void setNewCorrelationIdOnThread(JoinPoint joinPoint) throws Throwable {
        String correlationId = UUID.randomUUID().toString();
        CorrelationIdUpdater.updateCorrelationId(correlationId);
    }

}
