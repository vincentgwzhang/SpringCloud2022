package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.AbstractIntegrationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Bulkhead 的意思是说，能够同时被调用多少次，也可以允许等待
 *
 * 其实这个 BulkHead 是很难控制的，因为当 maxWaitDuration 数字比较大的时候，实质上能够允许更多的同时进程
 */
@Slf4j
public class BulkheadTest extends AbstractIntegrationTest {

    @Test
    public void testAllowToRunInTheSametime() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> callables = new ArrayList<>();

        for (int index = 1; index <= 10; index ++) {
            final int num = index;
            callables.add(() -> restTemplate.getForObject("/resilience4j/testBulkhead/{num}", Integer.class, num));
        }

        List<Future<Integer>> futures = executorService.invokeAll(callables);

        while (!executorService.isTerminated()) {
            executorService.shutdown();
        }

        int successCount = 0;

        for (Future<Integer> future : futures) {
            try {
                int result = future.get();
                if (result > 0) {
                    log.info("Return result 1:" + result);
                    successCount ++;
                } else {
                    log.info("Return result 2:" + result);
                }
            } catch (Exception e) {
                log.error("Error return result, " + e.getClass().getName());
            }
        }

        // maxConcurrentCalls
        assertThat(successCount).isGreaterThanOrEqualTo(3);
    }

}
