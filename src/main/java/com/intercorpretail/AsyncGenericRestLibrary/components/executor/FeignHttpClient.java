package com.intercorpretail.AsyncGenericRestLibrary.components.executor;

import com.intercorpretail.AsyncGenericRestLibrary.components.client.GenericFeignClient;
import com.intercorpretail.AsyncGenericRestLibrary.components.executor.strategy.*;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class FeignHttpClient extends AbstractAuthHttpClient {
    protected final GenericFeignClient feignClient;
    private final Map<HttpMethod, HttpMethodStrategy> methodToStrategy;

    public FeignHttpClient(GenericFeignClient feignClient, String url) {
        super(url);
        this.feignClient = feignClient;
        this.methodToStrategy = new HashMap<>();
        methodToStrategy.put(HttpMethod.GET, new GetStrategy(feignClient));
        methodToStrategy.put(HttpMethod.POST, new PostStrategy(feignClient));
        methodToStrategy.put(HttpMethod.PUT, new PutStrategy(feignClient));
        methodToStrategy.put(HttpMethod.DELETE, new DeleteStrategy(feignClient));

    }


    @Override
    public ResponseEntity<?> execute(HttpMethod method, String path, @Nullable Object body, @Nullable Map<String, String> queryParams, @Nullable HttpHeaders headers) {

        if (headers != null && headers.getFirst(HttpHeaders.AUTHORIZATION) != null) {
            String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader.startsWith("Basic ")) {
                return executeWithBasicAuth(method, path, body, queryParams, headers, authHeader);
            } else if (authHeader.startsWith("Bearer ")) {
                return executeWithBearerToken(method, path, body, queryParams, headers, authHeader);
            }

        }
        return executeWithoutAuth(method, path, body, queryParams, headers);
    }

    @Override
    public ResponseEntity<?> executeWithBasicAuth(HttpMethod method, String path, Object body, Map<String, String> queryParams, HttpHeaders headers, String authHeader) {
       // headers.add("Authorization", authHeader);
        return executeWithoutAuth(method, path, body, queryParams, headers);
    }

    @Override
    public ResponseEntity<?> executeWithBearerToken(HttpMethod method, String path, Object body, Map<String, String> queryParams, HttpHeaders headers, String authHeader) {
        headers.add("Authorization", authHeader);
        return executeWithoutAuth(method, path, body, queryParams, headers);
    }

    @Override
    public ResponseEntity<?> executeWithoutAuth(HttpMethod method, String path, Object body, Map<String, String> queryParams, HttpHeaders headers) {
        HttpMethodStrategy strategy = methodToStrategy.get(method);
        if (strategy == null) {
            throw new UnsupportedOperationException("Unsupported HTTP method: " + method);
        }
        return strategy.execute(path, body, queryParams, headers);
    }
}