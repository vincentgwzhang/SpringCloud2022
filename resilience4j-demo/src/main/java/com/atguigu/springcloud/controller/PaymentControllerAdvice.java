package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.dto.CommonResult;
import com.atguigu.springcloud.exception.CircuitBreakerRuntimeException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class PaymentControllerAdvice {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @Autowired
    public PaymentControllerAdvice(final CircuitBreakerRegistry circuitBreakerRegistry) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult handleMethodArgumentNotValidException() {
        final CommonResult result = new CommonResult();
        result.setMessage("Invalid parameters");
        result.setCode(HttpStatus.BAD_REQUEST.value());
        return result;
    }

    @ExceptionHandler(HttpServerErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult handleHttpServerErrorException(HttpServerErrorException e) {
        final CommonResult result = new CommonResult();
        result.setMessage(e.getMessage());
        result.setCode(HttpStatus.BAD_REQUEST.value());
        return result;
    }

    @ExceptionHandler(CircuitBreakerRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult handleCircuitBreakerRuntimeException(CircuitBreakerRuntimeException e) {
        final CommonResult result = new CommonResult();
        result.setMessage(e.getMessage());
        result.setCode(HttpStatus.BAD_REQUEST.value());

        final String circuitBreakerName = e.getMessage();
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(circuitBreakerName);

        Map<String, String> mapInfo = new HashMap<>();
        mapInfo.put("name", circuitBreaker.getName());
        mapInfo.put("state", circuitBreaker.getState().name());

        result.setData(mapInfo);
        return result;
    }

}
