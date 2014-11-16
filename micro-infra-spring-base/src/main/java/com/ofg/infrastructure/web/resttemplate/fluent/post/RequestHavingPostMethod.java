package com.ofg.infrastructure.web.resttemplate.fluent.post;

import com.ofg.infrastructure.web.resttemplate.fluent.common.request.RequestHaving;
import com.ofg.infrastructure.web.resttemplate.fluent.common.response.receive.HttpEntitySending;

/**
 * Base interface for {@link org.springframework.http.HttpMethod#POST} HTTP method in terms
 * of sending a request
 *
 * @see com.ofg.infrastructure.web.resttemplate.fluent.post.ResponseReceivingPostMethod
 */
public interface RequestHavingPostMethod extends RequestHaving<ResponseReceivingPostMethod>, HttpEntitySending<ResponseReceivingPostMethod> {
}
