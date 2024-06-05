package com.intercorpretail.AsyncGenericRestLibrary.components.invokers;

import com.intercorpretail.AsyncGenericRestLibrary.components.handler.RequestHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;
import java.util.Map;

public class PutInvoker extends Invoker {


    public PutInvoker(Class<?> serviceClass, RequestHandler asyncRequestHandler, RequestHandler syncRequestHandler) {
        super(serviceClass, asyncRequestHandler, syncRequestHandler);
    }

    @Override
    protected ResponseEntity<?> handleRequest(RequestHandler requestHandler, Method method, HttpHeaders headers, Map<String, String> queryMap, Object body) {
        return requestHandler.put(method, body, headers, queryMap);
    }
}
