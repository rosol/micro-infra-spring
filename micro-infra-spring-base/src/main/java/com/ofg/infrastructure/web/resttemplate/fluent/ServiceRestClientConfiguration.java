package com.ofg.infrastructure.web.resttemplate.fluent;

import com.ofg.infrastructure.discovery.ServiceResolver;
import com.ofg.infrastructure.web.resttemplate.custom.RestTemplate;
import groovy.transform.CompileStatic;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.client.RestOperations;

/**
 * Creates a bean of abstraction over {@link RestOperations}.
 *
 * @see com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
 * @see ServiceResolver
 */
@Configuration
@CompileStatic
public class ServiceRestClientConfiguration {
    @Bean
    public ServiceRestClient serviceRestClient(ServiceResolver serviceResolver) {
        return new ServiceRestClient(microInfraSpringRestTemplate(), serviceResolver);
    }

    @Bean
    public RestOperations microInfraSpringRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public static RestTemplateAutowireCandidateFalsePostProcessor disableMicroInfraSpringRestTemplateAutowiring() {
        return new RestTemplateAutowireCandidateFalsePostProcessor();
    }

    public static class RestTemplateAutowireCandidateFalsePostProcessor implements BeanFactoryPostProcessor, Ordered {
        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
            beanFactory.getBeanDefinition("microInfraSpringRestTemplate").setAutowireCandidate(false);
        }

        @Override
        public int getOrder() {
            return HIGHEST_PRECEDENCE;
        }

    }
}
