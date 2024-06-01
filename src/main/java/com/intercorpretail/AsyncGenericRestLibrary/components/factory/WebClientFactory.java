package com.intercorpretail.AsyncGenericRestLibrary.components.factory;

import org.springframework.web.reactive.function.client.WebClient;

public class WebClientFactory {
    public WebClient create(String baseUrl) {
        return WebClient.builder().baseUrl(baseUrl).build();
    }
}
