package com.intercorpretail.AsyncGenericRestLibrary.components.factory;


import com.intercorpretail.AsyncGenericRestLibrary.components.client.GenericFeignClient;
import feign.Feign;
import feign.codec.Decoder;
import feign.gson.GsonEncoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.http.converter.json.GsonHttpMessageConverter;


public class FeignClientFactory {
    public GenericFeignClient create(String baseUrl) {
        HttpMessageConverters httpMessageConverters = new HttpMessageConverters(new GsonHttpMessageConverter());
        Decoder decoder = new ResponseEntityDecoder(new SpringDecoder(() -> httpMessageConverters));
        return Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(decoder)
                .target(GenericFeignClient.class, baseUrl);
    }
}