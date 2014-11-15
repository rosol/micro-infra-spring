package com.ofg.infrastructure.web.exception;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration that initializes the {@link com.ofg.infrastructure.web.exception.ControllerExceptionHandler} as a bean
 */
@Configuration
public class ControllerExceptionConfiguration {
    @Bean
    public ControllerExceptionHandler controllerExceptionHandler() {
        return new ControllerExceptionHandler();
    }

}
