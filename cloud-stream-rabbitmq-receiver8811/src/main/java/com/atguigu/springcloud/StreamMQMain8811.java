package com.atguigu.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@SpringBootApplication
@Slf4j
public class StreamMQMain8811
{
    @Value("${server.port}")
    private int port;

    @Bean
    public Consumer<Message<String>> onReceive() {
        return (message) -> {
/*            message.getHeaders().entrySet().stream().forEach(entry -> {
                log.info("key = {}, value = {}", entry.getKey(), entry.getValue());
            });
            log.info("======================================================");*/
            log.info("From {}, Receive message: {}", port, message.getPayload());
        };
    }

    public static void main(String[] args)
    {
        SpringApplication.run(StreamMQMain8811.class,args);
    }
}