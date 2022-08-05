package com.atguigu.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
@Slf4j
public class StreamMQMain8812
{
    @Value("${server.port}")
    private int port;

    @Bean
    public Consumer<String> onReceive() {
        return (message) -> {
            log.info("From {}, Receive message: {}", port, message);
        };
    }

    public static void main(String[] args)
    {
        SpringApplication.run(StreamMQMain8812.class,args);
    }
}