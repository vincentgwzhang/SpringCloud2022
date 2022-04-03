package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.AbstractCircuitBreakerTest;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.junit.jupiter.api.Test;

public class PredicateTest extends AbstractCircuitBreakerTest {

    @Test
    public void testPredicate() {
        for (int index = 1; index <= 10; index ++) {
            this.restTemplate.getForObject("/predicate", String.class);
            if (index < 10) {
                checkHealthStatus(BACKEND_B, CircuitBreaker.State.CLOSED);
            }
        }
        checkHealthStatus(BACKEND_B, CircuitBreaker.State.OPEN);
    }

}
