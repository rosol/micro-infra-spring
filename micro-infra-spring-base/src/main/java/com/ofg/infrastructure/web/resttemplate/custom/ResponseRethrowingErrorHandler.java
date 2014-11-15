package com.ofg.infrastructure.web.resttemplate.custom;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;

/**
 * RestTemplate {@link ResponseErrorHandler} that on statuses equal to 4xx or 5xx
 * logs an error response body, status code and then rethrows exceptions {@link com.ofg.infrastructure.web.resttemplate.custom.ResponseException}
 *
 * @see org.springframework.web.client.RestTemplate
 * @see ResponseErrorHandler
 */
public class ResponseRethrowingErrorHandler implements ResponseErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return HttpStatusVerifier.isError(response.getStatusCode());
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String responseBody = getLoggedErrorResponseBody(response);
        throw new ResponseException(response.getStatusCode(), responseBody, response.getHeaders());
    }

    protected String getLoggedErrorResponseBody(ClientHttpResponse response) throws IOException {
        final InputStream body = response.getBody();
        String responseBody = (body == null ? null : IOUtils.toString(body));
        log.error("Response error: status code [" + String.valueOf(response.getStatusCode()) + "], headers [" + String.valueOf(response.getHeaders()) + "], body [" + responseBody + "]");
        return responseBody;
    }

}
