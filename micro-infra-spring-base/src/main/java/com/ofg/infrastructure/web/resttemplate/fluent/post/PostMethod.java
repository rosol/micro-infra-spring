package com.ofg.infrastructure.web.resttemplate.fluent.post;

import com.ofg.infrastructure.web.resttemplate.fluent.common.request.HttpMethod;

/**
 * {@link org.springframework.http.HttpMethod#POST} HTTP method base interface
 * <p/>
 * Sample execution for location:
 * <p/>
 * <pre>
 * httpMethodBuilder
 *    .post()
 *        .onUrlFromTemplate("client/{personalId}")
 *        .withVariables("123132123")
 *    .body("{'name':'smith'}")
 *    .forLocation()
 * </pre>
 * <p/>
 * Sample execution for body of type String:
 * <p/>
 * <pre>
 * httpMethodBuilder
 *    .post()
 *        .onUrl("client/123123")
 *    .body("{'name':'smith'}")
 *    .andExecuteFor()
 *        .anObject()
 *        .ofType(String.class)
 * </pre>
 * <p/>
 * Sample execution with headers ignoring response:
 * <p/>
 * <pre>
 * httpMethodBuilder
 *    .post()
 *    .onUrl("client/123123")
 *    .body("{'name':'smith'")
 *    .withHeaders()
 *        .contentTypeJson()
 *    .andExecuteFor()
 *        .ignoringResponse()
 * </pre>
 *
 * @see com.ofg.infrastructure.web.resttemplate.fluent.HttpMethodBuilder
 * @see HttpMethod
 * @see com.ofg.infrastructure.web.resttemplate.fluent.post.RequestHavingPostMethod
 * @see com.ofg.infrastructure.web.resttemplate.fluent.post.UrlParameterizablePostMethod
 */
public interface PostMethod extends HttpMethod<RequestHavingPostMethod, UrlParameterizablePostMethod> {
}
