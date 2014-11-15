package com.ofg.infrastructure.scheduling;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Registers beans related to task scheduling.
 *
 * @see com.ofg.infrastructure.scheduling.ScheduledTaskWithCorrelationIdAspect
 */
@Configuration
@EnableScheduling
@EnableAspectJAutoProxy
public class TaskSchedulingConfiguration {
    @Bean
    public ScheduledTaskWithCorrelationIdAspect scheduledTaskPointcut() {
        return new ScheduledTaskWithCorrelationIdAspect();
    }

}
