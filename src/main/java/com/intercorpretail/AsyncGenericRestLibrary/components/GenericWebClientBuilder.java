package com.intercorpretail.AsyncGenericRestLibrary.components;

import com.intercorpretail.AsyncGenericRestLibrary.components.executor.config.HttpClientConfig;

public class GenericWebClientBuilder {
    private Class<?> serviceClass;
    private HttpClientConfig config;

    public GenericWebClientBuilder() {
        this.config = new HttpClientConfig();
    }

    public GenericWebClientBuilder forService(Class<?> serviceClass) {
        this.serviceClass = serviceClass;
        return this;
    }

    public GenericWebClientBuilder withConfig(HttpClientConfig config) {
        this.config = config;
        return this;
    }

    public GenericWebClient build() {
        return new GenericWebClient(serviceClass, config);
    }
}