package com.ofg.infrastructure.camel;

import com.ofg.infrastructure.camel.aspects.CorrelationIdOnCamelRouteAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Configuration that provides {@link CorrelationIdOnCamelRouteAspect}.
 */
@Configuration
@EnableAspectJAutoProxy
public class CorrelationIdOnCamelRouteConfiguration {
    @Bean
    public CorrelationIdOnCamelRouteAspect correlationIdOnCamelRouteAspect() {
        return new CorrelationIdOnCamelRouteAspect();
    }

}
