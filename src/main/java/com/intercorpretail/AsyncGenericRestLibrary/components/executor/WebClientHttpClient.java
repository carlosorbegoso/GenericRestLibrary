package com.intercorpretail.AsyncGenericRestLibrary.components.executor;

import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

public class WebClientHttpClient extends AbstractAuthHttpClient {
    protected final WebClient webClient;

    public WebClientHttpClient(WebClient webClient, String url) {
    super(url);
        this.webClient = webClient;

    }

    @Override
    public ResponseEntity<?> execute(HttpMethod method, String path, @Nullable Object body, @Nullable Map<String, String> queryParams, @Nullable HttpHeaders headers) {
        return executeInternal(method, path, body, queryParams, headers, null, null, null);
    }


    public ResponseEntity<?> executeInternal(HttpMethod method, String path, Object body, Map<String, String> queryParams, HttpHeaders headers, String username, String password, String token) {
        queryParams = Optional.ofNullable(queryParams)
                .orElse(Collections.emptyMap());
        headers = Optional.ofNullable(headers)
                .orElse(new HttpHeaders());
        if (username != null && password != null) {
            String  authHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
            return executeWithBasicAuth(method, path, body, queryParams, headers,authHeader);
        } else if (token != null) {
            return executeWithBearerToken(method, path, body, queryParams, headers, token);
        } else {
            return executeRequest(method, path, body, queryParams, headers);
        }
    }

    private ResponseEntity<?> executeRequest(HttpMethod method, String path, Object body, Map<String, String> queryParams, HttpHeaders headers) {
        RequestBodySpec requestBodySpec = webClient.method(method).uri(uriBuilder -> {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(this.url + "/"+ path);
            queryParams.forEach(uriComponentsBuilder::queryParam);
            return uriComponentsBuilder.build().toUri();
        });

        RequestHeadersSpec<?> requestHeadersSpec;
        if (body != null) {
            requestHeadersSpec = requestBodySpec.bodyValue(body);
        } else {
            requestHeadersSpec = requestBodySpec;
        }

        if (headers != null) {
            requestHeadersSpec = requestHeadersSpec.headers(httpHeaders -> httpHeaders.addAll(headers));
        }

        Mono<ResponseEntity<Object>> responseEntityMono = requestHeadersSpec.exchangeToMono(clientResponse -> clientResponse.toEntity(Object.class));
        return responseEntityMono.block();
    }

    @Override
    protected ResponseEntity<?> executeWithoutAuth(HttpMethod method, String path, Object body, Map<String, String> queryParams, HttpHeaders headers) {
               return executeRequest(method, path, body, queryParams, headers);
    }


    @Override
    protected ResponseEntity<?> executeWithBasicAuth(HttpMethod method, String path, Object body, Map<String, String> queryParams, HttpHeaders headers, String authHeader) {
        headers.add("Authorization", authHeader);
        return executeRequest(method, path, body, queryParams, headers);
    }

    @Override
    protected ResponseEntity<?> executeWithBearerToken(HttpMethod method, String path, Object body, Map<String, String> queryParams, HttpHeaders headers, String token) {
        headers.add("Authorization", "Bearer " + token);
        return executeRequest(method, path, body, queryParams, headers);
    }
}