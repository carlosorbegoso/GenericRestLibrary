package com.intercorpretail.AsyncGenericRestLibrary.components.executor.strategy;

import com.intercorpretail.AsyncGenericRestLibrary.components.client.GenericFeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class PutStrategy implements HttpMethodStrategy{
    private final GenericFeignClient feignClient;

    public PutStrategy(GenericFeignClient feignClient) {
        this.feignClient = feignClient;
    }


    @Override
    public ResponseEntity<?> execute(String path, Object body, Map<String, String> queryParams, HttpHeaders headers) {
        return feignClient.putWithHeadersAndQueryParams(path, body, headers, queryParams);
    }
}
