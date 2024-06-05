package com.intercorpretail.AsyncGenericRestLibrary.components.executor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface HttpClient {
    ResponseEntity<?> execute(HttpMethod method, String path, HttpHeaders headers, Map<String, String> queryParams, Object body);
}