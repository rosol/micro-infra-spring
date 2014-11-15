package com.ofg.infrastructure.web.resttemplate.fluent.options;

import com.ofg.infrastructure.web.resttemplate.fluent.common.request.HttpMethod;

/**
 * {@link org.springframework.http.HttpMethod#OPTIONS} method base interface
 * <p/>
 * Sample execution for allow:
 * <p/>
 * <pre>
 * httpMethodBuilder
 *    .options()
 *        .onUrlFromTemplate("client/{personalId}")
 *        .withVariables("123132123")
 *    .andExecuteFor()
 *    .allow()
 * </pre>
 * <p/>
 * Sample execution for body of type String:
 * <p/>
 * <pre>
 * httpMethodBuilder
 *    .options()
 *        .onUrl("client/123123")
 *    .andExecuteFor()
 *        .anObject()
 *        .ofType(String.class)
 * </pre>
 * <p/>
 * Sample execution with headers ignoring response:
 * <p/>
 * <pre>
 * httpMethodBuilder
 *    .options()
 *    .onUrl("client/123123")
 *    .withHeaders()
 *        .contentTypeJson()
 *    .andExecuteFor()
 *        .ignoringResponse()
 * </pre>
 *
 * @see com.ofg.infrastructure.web.resttemplate.fluent.HttpMethodBuilder
 * @see HttpMethod
 * @see com.ofg.infrastructure.web.resttemplate.fluent.options.ResponseReceivingOptionsMethod
 * @see com.ofg.infrastructure.web.resttemplate.fluent.options.UrlParameterizableOptionsMethod
 */
public interface OptionsMethod extends HttpMethod<ResponseReceivingOptionsMethod, UrlParameterizableOptionsMethod> {
}
