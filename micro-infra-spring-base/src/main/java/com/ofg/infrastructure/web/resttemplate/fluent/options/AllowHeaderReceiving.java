package com.ofg.infrastructure.web.resttemplate.fluent.options;

import org.springframework.http.HttpMethod;

import java.util.Set;

/**
 * {@link HttpMethod#HEAD} is closely related to retrieving the {@link org.springframework.http.HttpHeaders#ALLOW}
 * request header. This interface provides an easy way to retrieve it.
 */
public interface AllowHeaderReceiving {
    /**
     * @return - a set of values from the {@link org.springframework.http.HttpHeaders#ALLOW} header
     */
    public abstract Set<HttpMethod> allow();
}
