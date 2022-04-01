package com.external.config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;

public class FooConfiguration {

    @Bean
    Logger.Level feignLoggerLevel()
    {
        return Logger.Level.FULL;
    }

    /**
     * Use when client have Spring security framework
     *
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password");
    }
    */

}
