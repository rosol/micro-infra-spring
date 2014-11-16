package com.ofg.infrastructure.web.resttemplate.fluent.put;

import com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor.LocationReceiving;
import com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.HttpEntitySending;
import com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.ResponseExtracting;
import com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.ResponseReceiving;

/**
 * The {@link org.springframework.http.HttpMethod#POST} HTTP method can send a message with a body
 * and can put for location.
 *
 * @see ResponseReceiving
 * @see LocationReceiving
 * @see HttpEntitySending
 */
public interface ResponseReceivingPutMethod extends ResponseReceiving, ResponseExtracting, LocationReceiving, HttpEntitySending<ResponseReceivingPutMethod> {
}
