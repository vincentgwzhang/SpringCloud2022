package com.atguigu.springcloud.service;

import com.atguigu.springcloud.dto.CommonResult;
import com.atguigu.springcloud.dto.PaymentDTO;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignServiceErrorHandler implements PaymentFeignService{

    @Override
    public CommonResult<PaymentDTO> getPaymentById(Long id) {
        return new CommonResult<>(404, "Not found",
                new PaymentDTO(-1L, "No data found"));
    }

    @Override
    public String paymentFeignTimeout() {
        return "funtion [paymentFeignTimeout] timeout";
    }
}
