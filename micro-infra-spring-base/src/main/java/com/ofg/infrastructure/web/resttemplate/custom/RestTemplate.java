package com.ofg.infrastructure.web.resttemplate.custom;

import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

/**
 * Default implementation of RestTemplate {@see RestTemplate} with custom
 * <ul>
 * <li>Error handling {@link com.ofg.infrastructure.web.resttemplate.custom.ResponseRethrowingErrorHandler}</li>
 * <li>Request factory
 * <ul>
 * <li>@link BufferingClientHttpRequestFactory} - so that we can access request's body several times throughout request's processing</li>
 * <li>with default {@link org.springframework.http.client.ClientHttpRequestFactory} - {@link SimpleClientHttpRequestFactory}</li>
 * </ul>
 * </li>
 * </ul>
 *
 * @see com.ofg.infrastructure.web.resttemplate.custom.RestTemplate
 * @see com.ofg.infrastructure.web.resttemplate.custom.ResponseRethrowingErrorHandler
 * @see BufferingClientHttpRequestFactory
 * @see org.springframework.http.client.ClientHttpRequestFactory
 */
public class RestTemplate extends org.springframework.web.client.RestTemplate {
    public RestTemplate() {
        setErrorHandler(new ResponseRethrowingErrorHandler());
        setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    }
}
