package com.intercorpretail.AsyncGenericRestLibrary.components;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface HttpClientStrategy {
    ResponseEntity<?>get(String url, HttpHeaders headers, Object... args);
    ResponseEntity<?>post(String url, HttpHeaders headers, Object... args);
    ResponseEntity<?>put(String url,  HttpHeaders headers, Object... args);
    ResponseEntity<?>delete(String url, HttpHeaders headers, Object... args);

}
