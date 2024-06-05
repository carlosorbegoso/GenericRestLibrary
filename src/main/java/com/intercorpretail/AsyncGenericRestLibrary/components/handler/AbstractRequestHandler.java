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
        return execute(method, path, queryParams, headers, body);
    }
    protected abstract ResponseEntity<?> execute(Method method, String path, Map<String, String> queryParams, HttpHeaders headers, Object body);
}