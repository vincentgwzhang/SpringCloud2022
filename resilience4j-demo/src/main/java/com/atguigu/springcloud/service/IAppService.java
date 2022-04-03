package com.atguigu.springcloud.service;

import java.util.concurrent.CompletableFuture;

public interface IAppService {

    void testCBRecordExceptions(int id);

    void testCBSuccess();

    void testCBFailure();

    int testConcurrenceRun(int num) throws InterruptedException;

    int testRatelimiter(int num) throws InterruptedException;

    CompletableFuture<String> testTimeLimiter();
}
