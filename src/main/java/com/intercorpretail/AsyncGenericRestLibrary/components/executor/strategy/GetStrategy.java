package com.intercorpretail.AsyncGenericRestLibrary.components.executor.strategy;

import com.intercorpretail.AsyncGenericRestLibrary.components.client.GenericFeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * This class implements the HttpMethodStrategy interface and provides a strategy  for executing a GET request using a GenericFeignClient
 */
public class GetStrategy implements HttpMethodStrategy {
    /*
     * The GenericFeign client used the execute requests
     */
    private final GenericFeignClient feignClient;

    /*
     * Constructs for the GetStrategy class
     */
    public GetStrategy(GenericFeignClient feignClient) {
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
        if (queryParams == null && headers == null) {
            return feignClient.get(path);
        } else if (queryParams != null && headers == null) {
            return feignClient.getWithQueryParams(path, queryParams);
        } else if (queryParams == null) {
            return feignClient.getWithHeaders(path, headers);
        }

        return feignClient.getWithHeadersAndQueryParams(path, headers, queryParams);
    }
}
