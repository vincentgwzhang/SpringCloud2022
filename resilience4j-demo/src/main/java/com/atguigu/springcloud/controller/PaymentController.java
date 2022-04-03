package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.dto.CommonResult;
import com.atguigu.springcloud.dto.PaymentDTO;
import com.atguigu.springcloud.exception.CircuitBreakerRuntimeException;
import com.atguigu.springcloud.service.IAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("resilience4j")
@Slf4j
public class PaymentController {

    private final IAppService BackendAService;

    @Autowired
    public PaymentController(final IAppService BackendAService) {
        this.BackendAService = BackendAService;
    }

    @GetMapping("testCBRecordExceptions/{id}")
    public CommonResult<PaymentDTO> testCBRecordExceptions(@PathVariable int id) {
        BackendAService.testCBRecordExceptions(id);
        return new CommonResult<>(HttpStatus.OK.value(), "Get Payment 3", new PaymentDTO(1003L, "MockData 3"));
    }

    @GetMapping("testSuccess")
    public CommonResult<String> testCBSuccess() {
        BackendAService.testCBSuccess();
        return new CommonResult<>(HttpStatus.OK.value(), "testSuccess", null);
    }

    @GetMapping("testFailure")
    public CommonResult<String> testCBFailure() {
        BackendAService.testCBFailure();
        return new CommonResult<>(HttpStatus.OK.value(), "testFailure", null);
    }

    @GetMapping("testBulkhead/{num}")
    public Integer testConcurrenceRun(@PathVariable int num) {
        try {
            return BackendAService.testConcurrenceRun(num);
        } catch (Exception e) {
            log.error(e.getClass().getName() + " triggered");
        }
        return -1;
    }

    @GetMapping("testRatelimiter/{num}")
    public Integer testRatelimiter(@PathVariable int num) {
        try {
            return BackendAService.testRatelimiter(num);
        } catch (Exception e) {
            log.error(e.getClass().getName() + " triggered");
        }
        return -1;
    }

    @GetMapping("testTimeLimiter")
    public CompletableFuture<String> testRatelimiter() {
        try {
            return BackendAService.testTimeLimiter();
        } catch (Exception e) {
            log.error(e.getClass().getName() + " triggered");
        }

        CompletableFuture<String> future = new CompletableFuture<>();
        future.completeExceptionally(new IOException("BAM!"));
        return future;
    }
}
