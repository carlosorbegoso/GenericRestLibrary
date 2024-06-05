package com.intercorpretail.AsyncGenericRestLibrary.components.handler;

import com.intercorpretail.AsyncGenericRestLibrary.components.executor.HttpClient;
import com.intercorpretail.AsyncGenericRestLibrary.utill.Util;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;
import java.util.Map;

public abstract class AbstractRequestHandler {
    protected final HttpClient request;

    public AbstractRequestHandler(HttpClient request) {
        this.request = request;
    }

    public ResponseEntity<?> handle(Method method, HttpHeaders headers, Map<String, String> queryParams, Object body) {
        String path = Util.getPathFromAnnotation(method);
        /*Class<?>[] parameterTypes = Util.getParameterTypes(args);

        Map<String, String> queryParams = null;

        if (parameterTypes.length == 1 && Map.class.isAssignableFrom(parameterTypes[0])) {
            queryParams = extractQueryParams(args);
            headers = extractHeaders(args);
        } else if (parameterTypes.length == 2 && Map.class.isAssignableFrom(parameterTypes[0]) && HttpHeaders.class.isAssignableFrom(parameterTypes[1])) {
            queryParams = (Map<String, String>) args[0];
            headers = (HttpHeaders) args[1];
        } else if (parameterTypes.length > 0) {
            throw new IllegalArgumentException("Invalid parameter types for method: " + method.getName());
        }*/

        return execute(method, path, queryParams, headers, body);
    }

    private Map<String, String> extractQueryParams(Object... args) {
        return args.length == 1 ? (Map<String, String>) args[0] : null;
    }

    private HttpHeaders extractHeaders(Object... args) {
        return args.length == 1 && args[0] instanceof HttpHeaders ? (HttpHeaders) args[0] : null;
    }

    protected abstract ResponseEntity<?> execute(Method method, String path, Map<String, String> queryParams, HttpHeaders headers, Object body);
}