package com.intercorpretail.AsyncGenericRestLibrary.components.invokers;

import com.intercorpretail.AsyncGenericRestLibrary.components.handler.RequestHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;

public class DeleteInvoker extends Invoker {


    public DeleteInvoker(Class<?> serviceClass, RequestHandler asyncRequestHandler, RequestHandler syncRequestHandler) {
        super(serviceClass, asyncRequestHandler, syncRequestHandler);
    }

    @Override
    protected ResponseEntity<?> handleRequest(RequestHandler requestHandler, Method method, HttpHeaders headers, Object... args) {
        return requestHandler.delete(method, headers, args);
    }
}