package com.ofg.infrastructure.web.resttemplate.fluent.options;

import com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.HeadersHaving;

/**
 * {@link org.springframework.http.HttpMethod#OPTIONS} HTTP method allows receiving requests with body what
 * {@link com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.ResponseExtracting} interface provides.
 * Additionally it gives the possibility to easily retrieve the {@link org.springframework.http.HttpHeaders#ALLOW}
 * header via {@link com.ofg.infrastructure.web.resttemplate.fluent.options.AllowHeaderReceiving} interface.
 */
public interface ResponseReceivingOptionsMethod extends HeadersHaving<ResponseReceivingOptionsMethod> {
}
