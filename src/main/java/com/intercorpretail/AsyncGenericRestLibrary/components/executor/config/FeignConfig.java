package com.intercorpretail.AsyncGenericRestLibrary.components.executor.config;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is used to configure the Feign client.
 */

@Configuration
public class FeignConfig {

    /**
     * The configuration of the HttpClient.
     */
    private final HttpClientConfig config;

    /**
     * Constructor of the class.
     */
    public FeignConfig(HttpClientConfig config) {
        this.config = config;
    }

    /**
     * Method to configure the Feign client.
     *
     * @return Request.Options
     */
    @Bean
    public Request.Options options() {
        // The timeout is set in milliseconds.
        return new Request.Options(config.getTimeout(), config.getTimeout());
    }
}