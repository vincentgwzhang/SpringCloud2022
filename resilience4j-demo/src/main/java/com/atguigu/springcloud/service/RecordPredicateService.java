package com.atguigu.springcloud.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.atguigu.springcloud.configuration.SystemVar.CIRCUITBREAKER_BACKENDB;

@Service
@Slf4j
public class RecordPredicateService {

    @CircuitBreaker(name = CIRCUITBREAKER_BACKENDB)
    public void testPredicateChecking() {
        int m = 1 / 0;
    }

}
