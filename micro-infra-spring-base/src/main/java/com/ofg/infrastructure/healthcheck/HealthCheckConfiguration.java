package com.ofg.infrastructure.healthcheck;

import com.ofg.infrastructure.discovery.ServiceResolver;
import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Registers {@link com.ofg.infrastructure.healthcheck.PingController} (the microservice health check controller) and {@link com.ofg.infrastructure.healthcheck.CollaboratorsConnectivityController} (provider of a state of microservice connection with dependent services).
 *
 * @see com.ofg.infrastructure.healthcheck.PingController
 * @see com.ofg.infrastructure.healthcheck.CollaboratorsConnectivityController
 */
@Configuration
public class HealthCheckConfiguration {
    @Bean
    public PingController pingController() {
        return new PingController();
    }

    @Bean
    public CollaboratorsConnectivityController collaboratorsConnectivityController(ServiceRestClient serviceRestClient, ServiceResolver serviceResolver) {
        return new CollaboratorsConnectivityController(serviceRestClient, serviceResolver);
    }

}
