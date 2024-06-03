package com.intercorpretail.AsyncGenericRestLibrary.components.executor.strategy;


import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface HttpMethodStrategy {
    ResponseEntity<?> execute(String path, Object body, Map<String, String> queryParams, HttpHeaders headers);
}