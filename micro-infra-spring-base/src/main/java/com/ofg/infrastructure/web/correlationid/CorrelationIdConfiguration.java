package com.ofg.infrastructure.web.correlationid;

import groovy.transform.CompileStatic;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Registers beans that add correlation id to requests
 *
 * @see com.ofg.infrastructure.web.correlationid.CorrelationIdAspect
 * @see com.ofg.infrastructure.web.correlationid.CorrelationIdFilter
 */
@Configuration
@CompileStatic
public class CorrelationIdConfiguration {
    @Bean
    public CorrelationIdAspect correlationIdAspect() {
        return new CorrelationIdAspect();
    }

    @Bean
    public FilterRegistrationBean correlationHeaderFilter() {
        return new FilterRegistrationBean(new CorrelationIdFilter());
    }

}
