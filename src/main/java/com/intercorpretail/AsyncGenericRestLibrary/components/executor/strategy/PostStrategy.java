package com.intercorpretail.AsyncGenericRestLibrary.components.executor.strategy;

import com.intercorpretail.AsyncGenericRestLibrary.components.client.GenericFeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * This class implements the HttpMethodStrategy interface and provides a strategy  for executing a POST request using a GenericFeignClient
 */
public class PostStrategy implements HttpMethodStrategy {
    /*
     * The GenericFeign client used the execute requests
     */
    private final GenericFeignClient feignClient;

    /**
     * Constructs for the PostStrategy class
     */
    public PostStrategy(GenericFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    /**
     * @param path        The path of the request.
     * @param body        The body of the request
     * @param queryParams The query parameters of the request
     * @param headers     The headers of the request
     * @return The response entity of the request
     */
    @Override
    public ResponseEntity<?> execute(String path, Object body, Map<String, String> queryParams, HttpHeaders headers) {
        return feignClient.postWithHeadersAndQueryParams(path, body, headers, queryParams);
    }
}
