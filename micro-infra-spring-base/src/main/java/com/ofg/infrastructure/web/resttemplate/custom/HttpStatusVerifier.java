package com.ofg.infrastructure.web.resttemplate.custom;

import org.springframework.http.HttpStatus;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

public class HttpStatusVerifier {
    /**
     * Verifies whether the passed {@link HttpStatus} is either client or server error
     *
     * @param status
     */
    public static boolean isError(HttpStatus status) {
        return asList(CLIENT_ERROR, SERVER_ERROR).contains(status.series());
    }

}
