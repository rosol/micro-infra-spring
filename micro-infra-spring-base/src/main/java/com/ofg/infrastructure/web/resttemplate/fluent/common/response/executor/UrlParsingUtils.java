package com.ofg.infrastructure.web.resttemplate.fluent.common.response.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.net.URISyntaxException;

public final class UrlParsingUtils {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private UrlParsingUtils() {
        throw new UnsupportedOperationException("Can't instantiate a utility class");
    }

    static String prependSlashIfPathIsRelative(URI uri) {
        if (!uri.isAbsolute() && !uri.toString().startsWith(SLASH)) {
            return SLASH + String.valueOf(uri);
        }

        return uri.toString();
    }

    static String prependSlashIfPathDoesNotStartWithSlash(String uri) {
        if (!isUriAbsolute(uri) && !uri.startsWith(SLASH)) {
            return SLASH + uri;
        }

        return uri;
    }

    static boolean isUriAbsolute(String uri) {
        try {
            return new URI(removeVariableSignsFromUri(uri)).isAbsolute();
        } catch (URISyntaxException uriSyntaxException) {
            log.trace("Passed uri [" + uri + "] is not a valid uri. Exception [" + String.valueOf(uriSyntaxException) + "] occurred while trying to parse it");
            return false;
        }

    }

    private static String removeVariableSignsFromUri(String uri) {
        return uri.replaceAll(VAR_OPENING_SIGN, EMPTY).replaceAll(VAR_CLOSING_SIGN, EMPTY);
    }

    public static String appendPathToHost(String host, final URI path) {
        return host + UrlParsingUtils.prependSlashIfPathIsRelative(path);
    }

    public static String appendPathToHost(String host, final String path) {
        return host + UrlParsingUtils.prependSlashIfPathDoesNotStartWithSlash(path);
    }

    public static final String SLASH = "/";
    public static final String VAR_OPENING_SIGN = "\\{";
    public static final String VAR_CLOSING_SIGN = "}";
    public static final String EMPTY = "";
}
