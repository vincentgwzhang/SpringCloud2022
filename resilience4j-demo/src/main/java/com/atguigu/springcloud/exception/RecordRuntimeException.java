package com.atguigu.springcloud.exception;

public class RecordRuntimeException extends RuntimeException {
    public RecordRuntimeException(String message) {
        super(message);
    }
}
