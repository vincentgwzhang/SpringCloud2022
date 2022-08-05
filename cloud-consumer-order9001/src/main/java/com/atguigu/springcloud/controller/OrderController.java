package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.dto.CommonResult;
import com.atguigu.springcloud.dto.PaymentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private final RestTemplate restTemplate;

    /**
     * value = http://CLOUD-PAYMENT-SERVICE
     *
     * Here there is a small problem: You must add @LoadBalanced in restTemplate bean
     *
     *     @Bean
     *     @LoadBalanced
     *     public RestTemplate restTemplate() {
     *         return new RestTemplate();
     *     }
     *
     */
    @Value("${application.payment-service-url}")
    private String paymentServiceURL;

    @Autowired
    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping(
            value = "create_payment",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        return this.restTemplate.postForObject(paymentServiceURL + "/payment/create", paymentDTO, CommonResult.class);
    }

    @GetMapping("{id}")
    public CommonResult<PaymentDTO> getPaymentById(@PathVariable Long id) {
        return this.restTemplate.getForObject(paymentServiceURL + "/payment/{id}", CommonResult.class, id);
    }

    @GetMapping("zipkin")
    public String paymentZipkin()
    {
        return restTemplate.getForObject(paymentServiceURL +"/payment/zipkin/", String.class);
    }

}
