package com.atguigu.springcloud.service;
//https://linxianqin.com/2021/08/16/resilience4j-study-notes#resilience4j-%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static com.atguigu.springcloud.configuration.SystemVar.CIRCUITBREAKER_BACKENDA;

@Service("backendA")
@Slf4j
public class BackendAService implements IAppService {

    @Override
    @CircuitBreaker(name = CIRCUITBREAKER_BACKENDA)
    public void testCBRecordExceptions(int id) {
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "calling testCBRecordExceptions function, test successful");
        //throw new CircuitBreakerRuntimeException(CIRCUITBREAKER_BACKENDA);
    }

    @Override
    @CircuitBreaker(name = CIRCUITBREAKER_BACKENDA)
    @Retry(name = CIRCUITBREAKER_BACKENDA)
    public void testCBSuccess() {
        log.info("Nothing to do here");
    }

    @Override
    @Retry(name = CIRCUITBREAKER_BACKENDA)
    public void testCBFailure() {
        log.info("calling testCBFailure function, test successfully");
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "calling testCBFailure function, test successfully");
    }

    @Override
    @Bulkhead(name = CIRCUITBREAKER_BACKENDA, type = Bulkhead.Type.SEMAPHORE, fallbackMethod = "fallbackBulkhead")
    public int testConcurrenceRun(int num) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        log.info("Running: testConcurrenceRun: " + num);
        return num;
    }

    @Override
    @RateLimiter(name = CIRCUITBREAKER_BACKENDA, fallbackMethod = "fallbackRatelimiter")
    public int testRatelimiter(int num) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        log.info("Running: testRatelimiter: " + num);
        return num;
    }

    @Override
    @TimeLimiter(name = CIRCUITBREAKER_BACKENDA)
    public CompletableFuture<String> testTimeLimiter() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**
        CompletableFuture<String> future = new CompletableFuture<>();
        future.completeExceptionally(new IOException("BAM!"));
        return future;
         */
        return CompletableFuture.completedFuture("Hello World from backend A");
    }

    private int fallbackBulkhead(BulkheadFullException e){
        log.info("Running fail: " + e.getLocalizedMessage());
        return -2;
    }

    private int fallbackRatelimiter(RequestNotPermitted e){
        log.info("Running fail: " + e.getLocalizedMessage());
        return -6;
    }


}
