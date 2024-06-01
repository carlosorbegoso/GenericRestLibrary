package com.intercorpretail.AsyncGenericRestLibrary.components.handler;

import com.intercorpretail.AsyncGenericRestLibrary.components.executor.HttpClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;
import java.util.Map;


public class GetRequestHandler extends AbstractRequestHandler {
    public GetRequestHandler(HttpClient request) {
        super(request);
    }

    @Override
    protected ResponseEntity<?> execute(Method method, String path, Object body, Map<String, String> queryParams, HttpHeaders headers) {
        return request.execute(HttpMethod.GET, path, body, queryParams, headers);
    }
}
