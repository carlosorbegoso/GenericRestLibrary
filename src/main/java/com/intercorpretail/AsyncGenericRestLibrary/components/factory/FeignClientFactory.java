package com.intercorpretail.AsyncGenericRestLibrary.components.factory;


import com.intercorpretail.AsyncGenericRestLibrary.components.client.GenericFeignClient;
import com.intercorpretail.AsyncGenericRestLibrary.components.executor.config.HttpClientConfig;
import feign.Feign;
import feign.Request;
import feign.codec.Decoder;
import feign.gson.GsonEncoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.http.converter.json.GsonHttpMessageConverter;


public class FeignClientFactory {
    public GenericFeignClient create(String baseUrl) {
        HttpClientConfig defaultConfig = new HttpClientConfig();
        // Set default values for the config
        defaultConfig.setTimeout(5000);
        defaultConfig.setFollowRedirects(true);
        return create(baseUrl, defaultConfig);
    }

    public GenericFeignClient create(String baseUrl, HttpClientConfig config) {
        HttpMessageConverters httpMessageConverters = new HttpMessageConverters(new GsonHttpMessageConverter());
        Decoder decoder = new ResponseEntityDecoder(new SpringDecoder(() -> httpMessageConverters));
        return Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(decoder)
                .options(new Request.Options(config.getTimeout(), config.getTimeout()))
                .target(GenericFeignClient.class, baseUrl);
    }
}