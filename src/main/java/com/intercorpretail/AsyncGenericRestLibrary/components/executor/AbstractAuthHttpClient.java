package com.intercorpretail.AsyncGenericRestLibrary.components.executor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public abstract class AbstractAuthHttpClient implements HttpClient {
    protected final String url;

    public AbstractAuthHttpClient(String url) {
        this.url = url;
    }

    protected abstract ResponseEntity<?> executeWithoutAuth(HttpMethod method, String path, Object body, Map<String, String> queryParams, HttpHeaders headers);

    protected abstract ResponseEntity<?> executeWithBasicAuth(HttpMethod method, String path, Object body, Map<String, String> queryParams, HttpHeaders headers, String authHeader);

    protected abstract ResponseEntity<?> executeWithBearerToken(HttpMethod method, String path, Object body, Map<String, String> queryParams, HttpHeaders headers, String authHeader);
}