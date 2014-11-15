package com.ofg.infrastructure.web.resttemplate.fluent.head;

import com.ofg.infrastructure.web.resttemplate.fluent.common.request.HttpMethod;

/**
 * {@link org.springframework.http.HttpMethod#HEAD} method base interface
 * <p/>
 * Sample execution for {@link org.springframework.http.HttpHeaders}:
 * <p/>
 * <pre>
 * httpMethodBuilder
 *    .head()
 *        .onUrlFromTemplate("client/{personalId}")
 *        .withVariables("123132123")
 *    .andExecuteFor()
 *    .httpHeaders()
 * </pre>
 * <p/>
 * Sample execution for a {@link org.springframework.http.ResponseEntity}:
 * <p/>
 * <pre>
 * httpMethodBuilder
 *    .head()
 *        .onUrl("client/123123")
 *    .andExecuteFor()
 *    .aResponseEntity()
 * </pre>
 * <p/>
 * Sample execution with headers ignoring response:
 * <p/>
 * <pre>
 * httpMethodBuilder
 *    .head()
 *    .onUrl("client/123123")
 *    .withHeaders()
 *        .contentTypeJson()
 *    .andExecuteFor()
 *        .ignoringResponse()
 * </pre>
 *
 * @see com.ofg.infrastructure.web.resttemplate.fluent.HttpMethodBuilder
 * @see HttpMethod
 * @see com.ofg.infrastructure.web.resttemplate.fluent.head.ResponseReceivingHeadMethod
 * @see com.ofg.infrastructure.web.resttemplate.fluent.head.UrlParameterizableHeadMethod
 */
public interface HeadMethod extends HttpMethod<ResponseReceivingHeadMethod, UrlParameterizableHeadMethod> {
}
