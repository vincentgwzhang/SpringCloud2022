package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.AbstractCircuitBreakerTest;
import com.atguigu.springcloud.dto.CommonResult;
import org.junit.jupiter.api.Test;

import static io.github.resilience4j.circuitbreaker.CircuitBreaker.State;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * CircuitBreakerTest 的意思是说，错误达到一定配置的比例以后就不会允许再被调用
 */
public class CircuitBreakerTest extends AbstractCircuitBreakerTest {

	@Test
	public void shouldOpenBackendACircuitBreaker() {
		for (int index = 1; index <= 20; index ++) {
			restTemplate.getForEntity("/resilience4j/testCBRecordExceptions/{id}", CommonResult.class, 1);

			if (index < 20) {
				checkHealthStatus(BACKEND_A, State.CLOSED);
			}
		}
		checkHealthStatus(BACKEND_A, State.OPEN);
	}

	@Test
	public void shouldCloseBackendACircuitBreaker() {
		// Looking at this parameter: permittedNumberOfCallsInHalfOpenState = 8
		transitionToOpenState(BACKEND_A);
		circuitBreakerRegistry.circuitBreaker(BACKEND_A).transitionToHalfOpenState();
		for (int index = 1; index <= 8; index ++) {
			restTemplate.getForEntity("/resilience4j/testSuccess", CommonResult.class);
			if (index < 8) {
				checkHealthStatus(BACKEND_A, State.HALF_OPEN);
			}
		}
		checkHealthStatus(BACKEND_A, State.CLOSED);
	}
}
