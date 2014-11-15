package com.ofg.infrastructure.web.resttemplate.fluent.delete;

import com.ofg.infrastructure.web.resttemplate.fluent.common.request.HttpMethod;

/**
 * {@link org.springframework.http.HttpMethod#DELETE} method base interface
 * <p/>
 * Sample execution for a {@link org.springframework.http.ResponseEntity}:
 * <p/>
 * <pre>
 * httpMethodBuilder
 *    .delete()
 *        .onUrl("client/123123")
 *    .andExecuteFor()
 *        .aResponseEntity()
 * </pre>
 * <p/>
 * Sample execution with headers ignoring response:
 * <p/>
 * <pre>
 * httpMethodBuilder
 *    .delete()
 *    .onUrl("client/123123")
 *    .withHeaders()
 *        .contentTypeJson()
 *    .andExecuteFor()
 *        .ignoringResponse()
 * </pre>
 *
 * @see com.ofg.infrastructure.web.resttemplate.fluent.HttpMethodBuilder
 * @see HttpMethod
 * @see com.ofg.infrastructure.web.resttemplate.fluent.delete.ResponseReceivingDeleteMethod
 * @see com.ofg.infrastructure.web.resttemplate.fluent.delete.UrlParameterizableDeleteMethod
 */
public interface DeleteMethod extends HttpMethod<ResponseReceivingDeleteMethod, UrlParameterizableDeleteMethod> {
}
