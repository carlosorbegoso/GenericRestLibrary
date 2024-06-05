package com.intercorpretail.AsyncGenericRestLibrary.components;

import com.intercorpretail.AsyncGenericRestLibrary.annotations.RestService;
import com.intercorpretail.AsyncGenericRestLibrary.components.client.GenericFeignClient;
import com.intercorpretail.AsyncGenericRestLibrary.components.executor.FeignHttpClient;
import com.intercorpretail.AsyncGenericRestLibrary.components.executor.WebClientHttpClient;
import com.intercorpretail.AsyncGenericRestLibrary.components.executor.config.HttpClientConfig;
import com.intercorpretail.AsyncGenericRestLibrary.components.factory.FeignClientFactory;
import com.intercorpretail.AsyncGenericRestLibrary.components.factory.WebClientFactory;
import com.intercorpretail.AsyncGenericRestLibrary.components.handler.RequestHandler;
import com.intercorpretail.AsyncGenericRestLibrary.components.invokers.DeleteInvoker;
import com.intercorpretail.AsyncGenericRestLibrary.components.invokers.GetInvoker;
import com.intercorpretail.AsyncGenericRestLibrary.components.invokers.PostInvoker;
import com.intercorpretail.AsyncGenericRestLibrary.components.invokers.PutInvoker;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

/**
 * This class represents a generic web client that can be used to make HTTP requests.
 * It uses the RequestHandler to handle the request and can be configured to use either Feign or WebClient as the HTTp client.
 */
public class GenericWebClient {
    private final GetInvoker getInvoker;
    private final PostInvoker postInvoker;
    private final PutInvoker putInvoker;
    private final DeleteInvoker deleteInvoker;


    /**
     * Constructs a new GenericWebClient.
     *
     * @param serviceClass the class of the service to be used
     */
    public GenericWebClient(Class<?> serviceClass, HttpClientConfig config) {
        RestService restService = serviceClass.getAnnotation(RestService.class);
        String baseUrl = restService != null ? restService.baseUrl() : "";

        // create instance the web client
        WebClient webClient = new WebClientFactory().create(baseUrl);

        if(config == null){
            config = new HttpClientConfig();
        }

        // create instance the Feign client
        GenericFeignClient feignClient = new FeignClientFactory().create(baseUrl, config);


        // create instance  of the request handler with the appropriate client
        RequestHandler asyncRequestHandler = new RequestHandler(new WebClientHttpClient(webClient, baseUrl));
        RequestHandler syncRequestHandler = new RequestHandler(new FeignHttpClient(feignClient , baseUrl));


        this.getInvoker = new GetInvoker(serviceClass, asyncRequestHandler, syncRequestHandler);
        this.postInvoker = new PostInvoker(serviceClass, asyncRequestHandler, syncRequestHandler);
        this.putInvoker = new PutInvoker(serviceClass, asyncRequestHandler, syncRequestHandler);
        this.deleteInvoker = new DeleteInvoker(serviceClass, asyncRequestHandler, syncRequestHandler);
    }


    public ResponseEntity<?> get(HttpHeaders headers, Map<String, String> queryParams) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return getInvoker.invoke(methodName, headers, queryParams, null);
    }

    public ResponseEntity<?> get(HttpHeaders headers) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return getInvoker.invoke(methodName, headers, null, null);
    }

    public ResponseEntity<?> get(Map<String, String> queryParams) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return getInvoker.invoke(methodName, null, queryParams, null);
    }

    public ResponseEntity<?> get() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return getInvoker.invoke(methodName, null, null, null);
    }

    /**
     * Makes a POST request
     *
     * @return of ResponseEntity of Type
     */
    public ResponseEntity<?> post(HttpHeaders headers, Map<String, String> queryParams, Object body) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return postInvoker.invoke(methodName, headers, queryParams, body);
    }

    public ResponseEntity<?> post(Map<String, String> queryParams, Object body) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return postInvoker.invoke(methodName, null, queryParams, body);
    }

    public ResponseEntity<?> post(HttpHeaders headers, Object body) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return postInvoker.invoke(methodName, headers, null, body);
    }

    public ResponseEntity<?> post(Object body) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println(postInvoker.invoke(methodName, null, null, body));
        return postInvoker.invoke(methodName, null, null, body);
    }

    /**
     * Makes a PUT request
     * @return of ResponseEntity of Type
     */
    public ResponseEntity<?> put(HttpHeaders headers,Object body) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return putInvoker.invoke(methodName, headers, null, body);
    }
    public ResponseEntity<?> put(Map<String,String>queryParam,Object body) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return putInvoker.invoke(methodName, null, queryParam, body);
    }
    public ResponseEntity<?> put(HttpHeaders headers,Map<String,String>queryParam,Object body) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return putInvoker.invoke(methodName, headers, queryParam, body);
    }

    /**
     * Makes a DELETE request
     * @return of ResponseEntity of Type
     */
    public ResponseEntity<?> delete(HttpHeaders headers,Object body) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return deleteInvoker.invoke(methodName, headers, null, body);
    }
    public ResponseEntity<?> delete(Map<String,String>queryParam,Object body) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return deleteInvoker.invoke(methodName, null, queryParam, body);
    }
    public ResponseEntity<?> delete(HttpHeaders headers,Map<String,String>queryParam,Object body) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return deleteInvoker.invoke(methodName, headers, queryParam, body);
    }

}
