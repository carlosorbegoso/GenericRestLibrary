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

    public ResponseEntity<?> handle(Method method,Object body, HttpHeaders headers, Object... args) {
        String path = Util.getPathFromAnnotation(method);
        Class<?>[] parameterTypes = Util.getParameterTypes(args);

        Map<String, String> queryParams = null;

        if (parameterTypes.length == 1 && Map.class.isAssignableFrom(parameterTypes[0])) {
           if(args.length == 1){
               if(args[0] instanceof HttpHeaders){
                   headers = (HttpHeaders) args[0];
               }
           }

            queryParams = (Map<String, String>) args[0];
        } else if (parameterTypes.length == 1 && HttpHeaders.class.isAssignableFrom(parameterTypes[0])) {
            headers = (HttpHeaders) args[0];
        } else if (parameterTypes.length == 2 && Map.class.isAssignableFrom(parameterTypes[0]) && HttpHeaders.class.isAssignableFrom(parameterTypes[1])) {
            queryParams = (Map<String, String>) args[0];
            headers = (HttpHeaders) args[1];
        } else if (parameterTypes.length > 0) {
            throw new IllegalArgumentException("Invalid parameter types for method: " + method.getName());
        }

        return execute(method, path, body, queryParams, headers);
    }

    protected abstract ResponseEntity<?> execute(Method method, String path, Object body, Map<String, String> queryParams, HttpHeaders headers);
}