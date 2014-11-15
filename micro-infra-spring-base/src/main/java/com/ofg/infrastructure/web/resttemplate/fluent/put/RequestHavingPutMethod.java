package com.ofg.infrastructure.web.resttemplate.fluent.put;

import com.ofg.infrastructure.web.resttemplate.fluent.common.request.RequestHaving;

/**
 * Base interface for {@link org.springframework.http.HttpMethod#PUT} HTTP method in terms
 * of sending a request
 *
 * @see RequestHaving
 * @see HttpEntitySending
 */
public interface RequestHavingPutMethod extends RequestHaving<ResponseReceivingPutMethod> {
}
