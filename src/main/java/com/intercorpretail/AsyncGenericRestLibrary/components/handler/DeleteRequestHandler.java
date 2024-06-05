package com.intercorpretail.AsyncGenericRestLibrary.components.handler;

import com.intercorpretail.AsyncGenericRestLibrary.components.executor.HttpClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;
import java.util.Map;

public class DeleteRequestHandler extends AbstractRequestHandler {


    public DeleteRequestHandler(HttpClient request) {
        super(request);
    }

    @Override
    protected ResponseEntity<?> execute(Method method, String path, Map<String, String> queryParams, HttpHeaders headers, Object body) {
        return request.execute(HttpMethod.DELETE, path, headers, queryParams, null);
    }


}
