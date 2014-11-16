package com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor;

/**
 * Interface that gives a nice DSL
 */
public interface Executable<T> {
    T andExecuteFor();
}
