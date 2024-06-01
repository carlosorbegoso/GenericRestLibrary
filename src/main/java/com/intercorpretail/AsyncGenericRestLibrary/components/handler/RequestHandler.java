package com.intercorpretail.AsyncGenericRestLibrary.components.handler;

import com.intercorpretail.AsyncGenericRestLibrary.components.executor.HttpClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;


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

    public ResponseEntity<?> get(Method method, HttpHeaders headers, Object... args) {
        return getRequestHandler.handle(method,null,headers, args);
    }

    public ResponseEntity<?> post(Method method, Object body,HttpHeaders headers, Object... args) {
        return postRequestHandler.handle(method,body,headers, args);
    }

    public ResponseEntity<?> put(Method method, Object body,HttpHeaders headers, Object... args) {
        return putRequestHandler.handle(method, body,headers, args);
    }

    public ResponseEntity<?> delete(Method method, HttpHeaders headers, Object... args) {
        return deleteRequestHandler.handle(method,null,headers, args);
    }


}
