package com.intercorpretail.AsyncGenericRestLibrary.components.executor.strategy;


import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * This interface provides a strategy for executing a request using a GenericFeignClient
 */

public interface HttpMethodStrategy {
    /**
     * @param path        The path of the request.
     * @param body        The body of the request
     * @param queryParams The query parameters of the request
     * @param headers     The headers of the request
     * @return The response entity of the request
     */
    ResponseEntity<?> execute(String path, Object body, Map<String, String> queryParams, HttpHeaders headers);
}