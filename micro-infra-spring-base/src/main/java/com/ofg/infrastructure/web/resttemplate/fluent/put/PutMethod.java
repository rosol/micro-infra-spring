package com.ofg.infrastructure.web.resttemplate.fluent.put;

import com.ofg.infrastructure.web.resttemplate.fluent.common.request.HttpMethod;

/**
 * {@link org.springframework.http.HttpMethod#POST} HTTP method base interface
 * <p/>
 * Sample execution for location:
 * <p/>
 * <pre>
 * httpMethodBuilder
 *    .put()
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
 *    .put()
 *        .onUrl("client/123123")
 *    .body("{'name':'smith'}")
 *    .andExecuteFor()
 *        .anObject()
 *        .ofType(String.class)
 * </pre>
 * <p/>
 * <p/>
 * Sample execution with headers ignoring response:
 * <p/>
 * <pre>
 * httpMethodBuilder
 *    .get()
 *    .onUrl("client/123123")
 *    .withHeaders()
 *        .contentTypeJson()
 *    .andExecuteFor()
 *        .ignoringResponse()
 * </pre>
 *
 * @see com.ofg.infrastructure.web.resttemplate.fluent.HttpMethodBuilder
 * @see HttpMethod
 * @see com.ofg.infrastructure.web.resttemplate.fluent.put.RequestHavingPutMethod
 * @see com.ofg.infrastructure.web.resttemplate.fluent.put.UrlParameterizablePutMethod
 */
public interface PutMethod extends HttpMethod<RequestHavingPutMethod, UrlParameterizablePutMethod> {
}
