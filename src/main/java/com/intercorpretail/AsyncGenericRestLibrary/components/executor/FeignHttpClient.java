package com.intercorpretail.AsyncGenericRestLibrary.components.executor;

import com.intercorpretail.AsyncGenericRestLibrary.components.client.GenericFeignClient;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

public class FeignHttpClient extends AbstractAuthHttpClient  {
    protected final GenericFeignClient feignClient;
    private final Map<HttpMethod, Function<RequestData, ResponseEntity<?>>> methodToExecutor;
    public FeignHttpClient(GenericFeignClient feignClient, String url) {
        super(url);
        this.feignClient = feignClient;
        this.methodToExecutor = new HashMap<>();
        methodToExecutor.put(HttpMethod.GET, this::executeGet);
        methodToExecutor.put(HttpMethod.POST, this::executePost);
        methodToExecutor.put(HttpMethod.PUT, this::executePut);
        methodToExecutor.put(HttpMethod.DELETE, this::executeDelete);
        methodToExecutor.put(HttpMethod.PATCH, this::executePatch);
        methodToExecutor.put(HttpMethod.HEAD, this::executeHead);
        methodToExecutor.put(HttpMethod.OPTIONS, this::executeOptions);
        methodToExecutor.put(HttpMethod.TRACE, this::executeTrace);
    }



    @Override
    public ResponseEntity<?> execute(HttpMethod method, String path, @Nullable Object body, @Nullable Map<String, String> queryParams, @Nullable HttpHeaders headers) {
     if(headers == null ){
         return executeWithoutAuth(method, path, body, queryParams, null);
     }
        String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null) {
            if (authHeader.startsWith("Basic ")) {
                String base64Credentials = authHeader.substring("Basic ".length()).trim();
                byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
                String credentials = new String(credDecoded, StandardCharsets.UTF_8);
                final String[] values = credentials.split(":", 2);
                String username = values[0];
                String password = values.length > 1 ? values[1] : null;
                return executeWithBasicAuth(method, path, body, queryParams, headers, username, password);
            } else if (authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring("Bearer ".length()).trim();
                return executeWithBearerToken(method, path, body, queryParams, headers, token);
            }
        }
        return executeWithoutAuth(method, path, body, queryParams, headers);
    }



    public ResponseEntity<?> executeWithoutAuth(HttpMethod method, String path, Object body, Map<String, String> queryParams, HttpHeaders headers) {
        Function<RequestData, ResponseEntity<?>> executor = methodToExecutor.get(method);
        if (executor == null) {
            throw new UnsupportedOperationException("Unsupported HTTP method: " + method);
        }
        return executor.apply(new RequestData(path, body, queryParams, headers, null, null, null));
    }

    @Override
    protected ResponseEntity<?> executeWithBasicAuth(HttpMethod method, String path, Object body, Map<String, String> queryParams, HttpHeaders headers, String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.add("Authorization", authHeader);
        return executeWithoutAuth(method, path, body, queryParams, headers);
    }

    public  ResponseEntity<?> executeWithBearerToken(HttpMethod method, String path, Object body, Map<String, String> queryParams, HttpHeaders headers, String token) {
        headers.add("Authorization", "Bearer " + token);
        return executeWithoutAuth(method, path, body, queryParams, headers);
    }


    private ResponseEntity<?> executeGet(RequestData requestData) {
        return feignClient.getWithHeadersAndQueryParams(requestData.path, requestData.headers, requestData.queryParams);
    }

    private ResponseEntity<?> executePost(RequestData requestData) {
        return feignClient.postWithHeadersAndQueryParams(requestData.path, requestData.body, requestData.headers, requestData.queryParams);
    }

    private ResponseEntity<?> executePut(RequestData requestData) {
        return feignClient.putWithHeadersAndQueryParams(requestData.path, requestData.body, requestData.headers, requestData.queryParams);
    }

    private ResponseEntity<?> executeDelete(RequestData requestData) {

        return feignClient.deleteWithHeadersAndQueryParams(requestData.path, requestData.headers, requestData.queryParams);
    }

    private ResponseEntity<?> executePatch(RequestData requestData) {
        return feignClient.patchWithHeadersAndQueryParams(requestData.path, requestData.body, requestData.headers, requestData.queryParams);
    }

    private ResponseEntity<?> executeHead(RequestData requestData) {
        return feignClient.headWithHeadersAndQueryParams(requestData.path, requestData.headers, requestData.queryParams);
    }

    private ResponseEntity<?> executeOptions(RequestData requestData) {
        return feignClient.optionsWithHeadersAndQueryParams(requestData.path, requestData.headers, requestData.queryParams);
    }

    private ResponseEntity<?> executeTrace(RequestData requestData) {
        return feignClient.traceWithHeadersAndQueryParams(requestData.path, requestData.headers, requestData.queryParams);
    }


    private record RequestData(String path, Object body, Map<String, String> queryParams, HttpHeaders headers, String username, String password, String token) {
    }
}