package com.intercorpretail.AsyncGenericRestLibrary.components.executor.config;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    private final HttpClientConfig config;

    public FeignConfig(HttpClientConfig config) {
        this.config = config;
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(config.getTimeout(), config.getTimeout());
    }
}