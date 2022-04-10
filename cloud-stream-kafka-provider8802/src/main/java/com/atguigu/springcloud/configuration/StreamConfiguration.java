package com.atguigu.springcloud.configuration;

import com.atguigu.springcloud.dto.TransferObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
@Slf4j
public class StreamConfiguration {

    @Bean
    Consumer<TransferObject> receiveTransferObject() {
        return transferObject -> log.info("Received TransferObject: " + transferObject.toString());
    }

}
