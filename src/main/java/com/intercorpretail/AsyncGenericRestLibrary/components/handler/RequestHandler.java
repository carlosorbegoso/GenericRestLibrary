package com.intercorpretail.AsyncGenericRestLibrary.components.handler;

import com.intercorpretail.AsyncGenericRestLibrary.components.executor.HttpClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;
import java.util.Map;


public class RequestHandler {
    private final GetRequestHandler getRequestHandler;
    private final PostRequestHandler postRequestHandler;
    private final PutRequestHandler putRequestHandler;
    private final DeleteRequestHandler deleteRequestHandler;

    public RequestHandler(HttpClient request) {
        this.getRequestHandler = new GetRequestHandler(request);
        this.postRequestHandler = new PostRequestHandler(request);
        this.putRequestHandler = new PutRequestHandler(request);
        this.deleteRequestHandler = new DeleteRequestHandler(request);
    }

    public ResponseEntity<?> get(Method method, HttpHeaders headers, Map<String, String> queryParams) {
        return getRequestHandler.handle(method, headers, queryParams, null);
    }

    public ResponseEntity<?> post(Method method, HttpHeaders headers, Map<String, String> queryParams, Object body) {

        return postRequestHandler.handle(method, headers, queryParams, body);
    }

    public ResponseEntity<?> put(Method method, Object body, HttpHeaders headers, Map<String, String> queryParams) {
        return putRequestHandler.handle(method, headers, queryParams, body);
    }

    public ResponseEntity<?> delete(Method method, HttpHeaders headers, Map<String, String> queryParams) {
        return deleteRequestHandler.handle(method, headers, queryParams, null);
    }


}
