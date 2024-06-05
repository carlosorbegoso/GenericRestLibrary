package com.intercorpretail.AsyncGenericRestLibrary.components.invokers;

import com.intercorpretail.AsyncGenericRestLibrary.components.handler.RequestHandler;
import com.intercorpretail.AsyncGenericRestLibrary.exceptions.HttpClientException;
import com.intercorpretail.AsyncGenericRestLibrary.exceptions.MethodNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

public abstract class Invoker {
    protected final RequestHandler asyncRequestHandler;
    protected final RequestHandler syncRequestHandler;
    protected final Class<?> serviceClass;

    public Invoker(Class<?> serviceClass, RequestHandler asyncRequestHandler, RequestHandler syncRequestHandler) {
        this.serviceClass = serviceClass;
        this.asyncRequestHandler = asyncRequestHandler;
        this.syncRequestHandler = syncRequestHandler;
    }

    public ResponseEntity<?> invoke(String methodName, HttpHeaders headers, Map<String, String> queryParams, Object body) {
        try {
            boolean isAsync = methodName.endsWith("Async");
            Method method = Arrays.stream(serviceClass.getMethods())
                    .filter(m -> m.getName().equals(methodName))
                    .findFirst()
                    .orElseThrow(MethodNotFoundException::new);

            RequestHandler requestHandler = isAsync ? asyncRequestHandler : syncRequestHandler;
            return handleRequest(requestHandler, method, headers, queryParams, body);
        } catch (HttpClientException e) {
            throw new HttpClientException("An HttpClientException occurred: ", e);
        } catch (MethodNotFoundException e) {
            throw new MethodNotFoundException("Method not found: ", e);
        } catch (Exception e) {
            throw new HttpClientException("An unexpected error occurred: ", e);
        }
    }

    protected abstract ResponseEntity<?> handleRequest(RequestHandler requestHandler, Method method, HttpHeaders headers, Map<String, String> queryParams, Object body);
}