package com.intercorpretail.AsyncGenericRestLibrary.components.invokers;

import com.intercorpretail.AsyncGenericRestLibrary.components.handler.RequestHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;
import java.util.Map;

public class DeleteInvoker extends Invoker {


    public DeleteInvoker(Class<?> serviceClass, RequestHandler asyncRequestHandler, RequestHandler syncRequestHandler) {
        super(serviceClass, asyncRequestHandler, syncRequestHandler);
    }

    @Override
    protected ResponseEntity<?> handleRequest(RequestHandler requestHandler, Method method, HttpHeaders headers, Map<String, String> queryParams, Object body) {
        return requestHandler.delete(method, headers, queryParams);
    }
}
