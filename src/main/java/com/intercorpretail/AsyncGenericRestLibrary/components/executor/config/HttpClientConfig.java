package com.intercorpretail.AsyncGenericRestLibrary.components.executor.config;

import org.springframework.stereotype.Component;

@Component
public class HttpClientConfig {
    private Integer timeout;
    private Boolean followRedirects;

    public HttpClientConfig() {
        this.timeout = 10000;
        this.followRedirects = true;
    }

    public Integer getTimeout() {
        return timeout;
    }
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
public Boolean getFollowRedirects() {
        return followRedirects;
    }
    public void setFollowRedirects(Boolean followRedirects) {
        this.followRedirects = followRedirects;
    }

}
