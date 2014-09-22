package com.ofg.infrastructure.web.resttemplate.custom

import com.ofg.infrastructure.MicroInfraSpringQualifier
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestOperations

/**
 * Contains default configuration related to {@link RestOperations}.
 * Created instance of RestOperations is marked with @Qualifier(MicroInfraSpringQualifier.VALUE)
 * so that application using the library can create its own RestOperations without exceptions
 * being raised by Spring Framework.
 *
 * @see org.springframework.web.client.RestTemplate
 * @see RestOperations
 * @see MicroInfraSpringQualifier
 */
@TypeChecked
@Configuration
class RestTemplateConfiguration {

    @Bean
    @Qualifier(MicroInfraSpringQualifier.VALUE)
    RestOperations restTemplate() {
        return new RestTemplate()
    }

}