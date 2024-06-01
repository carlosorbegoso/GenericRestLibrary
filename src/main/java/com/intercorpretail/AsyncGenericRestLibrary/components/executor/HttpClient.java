package com.intercorpretail.AsyncGenericRestLibrary.components.executor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface HttpClient {
    ResponseEntity<?> execute(HttpMethod method, String path, Object o, Map<String, String> queryParams, HttpHeaders headers);


}