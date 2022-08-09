package com.atguigu.springcloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
@Slf4j
public class StreamConfiguration {

    @Bean
    public Function<String, String> publishInformationFromFunction() {
        return String::toUpperCase;
    }

    @Bean
    public Consumer<String> onReceive() {
        return message -> log.info("[onReceive]Receive message: {}", message);
    }

    @Bean
    public Consumer<String> onReceive2() {
        return message -> log.info("[onReceive2] Receive message: {}", message);
    }

}
