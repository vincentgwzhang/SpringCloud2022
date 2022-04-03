package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.AbstractIntegrationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TimeLimiterTest extends AbstractIntegrationTest {

    @Test
    public void testTimeLimiter() {
        String result = restTemplate.getForObject("/resilience4j/testTimeLimiter", String.class);
        log.info(result);
    }

}
