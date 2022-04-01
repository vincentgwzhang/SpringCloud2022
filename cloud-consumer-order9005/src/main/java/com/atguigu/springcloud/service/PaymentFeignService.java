package com.atguigu.springcloud.service;

import com.atguigu.springcloud.dto.CommonResult;
import com.atguigu.springcloud.dto.PaymentDTO;
import com.external.config.FooConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * I you want to use fallback, you must add:
 *
 *         <dependency>
 *             <groupId>org.springframework.cloud</groupId>
 *             <artifactId>spring-cloud-circuitbreaker-resilience4j</artifactId>
 *         </dependency>
 *
 *         Or
 *
 *         <dependency>
 *             <groupId>org.springframework.cloud</groupId>
 *             <artifactId>spring-cloud-circuitbreaker-spring-retry</artifactId>
 *         </dependency>
 *
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE", configuration = FooConfiguration.class, fallback = PaymentFeignServiceErrorHandler.class)//这个 @FeignClient 之能够放在 interface 上面
public interface PaymentFeignService
{
    /**
     * 一定要添加这个 @GetMapping, 否则调用不起来
     */
    @GetMapping(value = "/payment/{id}")
    CommonResult<PaymentDTO> getPaymentById(@PathVariable("id") Long id);

    /**
     * 一定要添加这个 @GetMapping, 否则调用不起来
     */
    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();
}