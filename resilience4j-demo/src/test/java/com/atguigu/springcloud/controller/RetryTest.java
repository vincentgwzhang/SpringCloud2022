package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.AbstractRetryTest;
import com.atguigu.springcloud.dto.CommonResult;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * RetryTest 的意思是说，错误以后，会自动重新调用多少次
 */
public class RetryTest extends AbstractRetryTest {

	@Test
	public void backendAshouldRetryThreeTimes() {
		int CALL_TIMES = new Random().nextInt(10) + 2;

		int currentCount = (int)getCurrentCount(FAILED_WITH_RETRY, BACKEND_A);
		for (int index = 1; index <= CALL_TIMES; index ++) {
			restTemplate.getForEntity("/resilience4j/testFailure", CommonResult.class);
		}
		int currentCount2 = (int)getCurrentCount(FAILED_WITH_RETRY, BACKEND_A);
		assertThat(currentCount2).isEqualTo(currentCount + CALL_TIMES);
	}

	@Test
	public void backendAshouldSucceedWithoutRetry() {
		int CALL_TIMES = new Random().nextInt(10) + 2;
		int currentCount = (int)getCurrentCount(SUCCESS_WITHOUT_RETRY, BACKEND_A);

		for (int index = 1; index <= CALL_TIMES; index ++) {
			restTemplate.getForEntity("/resilience4j/testSuccess", CommonResult.class);
		}

		int currentCount2 = (int)getCurrentCount(SUCCESS_WITHOUT_RETRY, BACKEND_A);
		assertThat(currentCount2).isEqualTo(currentCount + CALL_TIMES);
	}

}
