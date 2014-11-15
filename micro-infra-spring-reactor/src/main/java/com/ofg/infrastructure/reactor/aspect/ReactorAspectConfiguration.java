package com.ofg.infrastructure.reactor.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration that registers {@link com.ofg.infrastructure.reactor.aspect.ReactorAspect} as a Spring bean.
 * That way, combined with {@link com.ofg.infrastructure.reactor.event.ReactorEvent} and {@link reactor.spring.annotation.Selector}
 * annotations correlationId will be set for applications using Spring Reactor.
 *
 * @see com.ofg.infrastructure.reactor.aspect.ReactorAspect
 */
@Configuration
public class ReactorAspectConfiguration {
    @Bean
    public ReactorAspect reactorAspect() {
        return new ReactorAspect();
    }

}
