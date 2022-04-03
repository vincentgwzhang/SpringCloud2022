package com.atguigu.springcloud.exception;

public class CircuitBreakerRuntimeException extends RuntimeException {
    public CircuitBreakerRuntimeException(String message) {
        super(message);
    }
}