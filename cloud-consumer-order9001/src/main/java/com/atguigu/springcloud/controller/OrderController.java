package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.dto.CommonResult;
import com.atguigu.springcloud.dto.PaymentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequestMapping("order")
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001";

    private final RestTemplate restTemplate;

    @Autowired
    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping(
            value = "create_payment",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        return this.restTemplate.postForObject(PAYMENT_URL + "/payment/create", paymentDTO, CommonResult.class);
    }

    @GetMapping("{id}")
    public CommonResult<PaymentDTO> getPaymentById(@PathVariable Long id) {
        return this.restTemplate.getForObject(PAYMENT_URL + "/payment/{id}", CommonResult.class, id);
    }

}
