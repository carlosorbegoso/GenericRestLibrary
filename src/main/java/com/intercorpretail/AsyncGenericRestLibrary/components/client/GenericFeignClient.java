package com.intercorpretail.AsyncGenericRestLibrary.components.client;

import feign.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Map;


public interface GenericFeignClient {

    @RequestLine("GET /{path}")
    ResponseEntity<?> get(@Param("path") String path);

    @RequestLine("GET /{path}")
    ResponseEntity<?> getWithQueryParams(@Param("path") String path, @QueryMap Map<String, String> queryParams);

    @RequestLine("GET /{path}")
    @Headers("Content-Type: application/json")
    ResponseEntity<?> getWithHeaders(@Param("path") String path, @HeaderMap HttpHeaders headers);

    @RequestLine("POST /{path}")
    ResponseEntity<?> post(@Param("path") String path, @Param("body") Object body);

    @RequestLine("POST /{path}")
    ResponseEntity<?> postWithQueryParams(@Param("path") String path, @Param("body") Object body, @QueryMap Map<String, String> queryParams);

    @RequestLine("POST /{path}")
    @Headers("Content-Type: application/json")
    ResponseEntity<?> postWithHeaders(@Param("path") String path, @Param("body") Object body, @HeaderMap HttpHeaders headers);


    @RequestLine("GET /{path}")
    @Headers("Content-Type: application/json")
    ResponseEntity<?> getWithHeadersAndQueryParams(@Param("path") String path, @HeaderMap HttpHeaders headers, @QueryMap Map<String, String> queryParams);

    @RequestLine("POST /{path}")
    @Headers("Content-Type: application/json")
    ResponseEntity<?> postWithHeadersAndQueryParams(@Param("path") String path, @Param("body") Object body, @HeaderMap HttpHeaders headers, @QueryMap Map<String, String> queryParams);

    @RequestLine("PUT /{path}")
    ResponseEntity<?> putWithHeadersAndQueryParams(@Param("path") String path, @Param("body") Object body, @HeaderMap HttpHeaders headers, @QueryMap Map<String, String> queryParams);

    @RequestLine("DELETE /{path}")
    ResponseEntity<?> deleteWithHeadersAndQueryParams(@Param("path") String path, @HeaderMap HttpHeaders headers, @QueryMap Map<String, String> queryParams);

    @RequestLine("PATCH /{path}")
    ResponseEntity<?> patchWithHeadersAndQueryParams(@Param("path") String path, @Param("body") Object body, @HeaderMap HttpHeaders headers, @QueryMap Map<String, String> queryParams);

    @RequestLine("HEAD /{path}")
    ResponseEntity<?> headWithHeadersAndQueryParams(@Param("path") String path, @HeaderMap HttpHeaders headers, @QueryMap Map<String, String> queryParams);

    @RequestLine("OPTIONS /{path}")
    ResponseEntity<?> optionsWithHeadersAndQueryParams(@Param("path") String path, @HeaderMap HttpHeaders headers, @QueryMap Map<String, String> queryParams);

    @RequestLine("TRACE /{path}")
    ResponseEntity<?> traceWithHeadersAndQueryParams(@Param("path") String path, @HeaderMap HttpHeaders headers, @QueryMap Map<String, String> queryParams);


}