package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.dto.CommonResult;
import com.atguigu.springcloud.dto.PaymentDTO;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("payment")
@Validated
public class PaymentController {

    private final PaymentService paymentService;

    private final DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;

    @Autowired
    public PaymentController(PaymentService paymentService, DiscoveryClient discoveryClient) {
        this.paymentService = paymentService;
        this.discoveryClient = discoveryClient;
    }

    @PostMapping(value = "create")
    public CommonResult<PaymentDTO> createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO result = paymentService.createPayment(paymentDTO);
        return new CommonResult<>(HttpStatus.OK.value(), "Create Success, port = " + port, result);
    }

    @GetMapping(value = "{id}")
    public CommonResult<PaymentDTO> getPaymentById(@PathVariable("id") Long id) {
        PaymentDTO result = paymentService.getPaymentById(id);
        if (Objects.nonNull(result)) {
            return new CommonResult<>(HttpStatus.OK.value(), "Payment retrieve success, port = " + port, result);
        } else {
            return new CommonResult<>(HttpStatus.NOT_FOUND.value(), "Payment not found, port = " + port);
        }
    }

    @GetMapping(value = "/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();//获取所有注册过的服务
        System.out.println("=================================================");
        System.out.println("Part 1: =========================================");
        for (String service : services) {
            System.out.println("**** Service = " + service);
        }
        System.out.println("Part 2: =========================================");
        List<ServiceInstance> list = discoveryClient.getInstances("cloud-payment-service".toUpperCase());
        System.out.println("=========================================");
        for (ServiceInstance serviceInstance : list) {
            System.out.println(serviceInstance.getInstanceId() + "\t" + serviceInstance.getHost() + "\t" + serviceInstance.getPort() + "\t" + serviceInstance.getUri());
        }
        System.out.println("=================================================");
        return this.discoveryClient;
    }

    @GetMapping(value = "/lb")
    public String getPaymentLB()
    {
        return port;
    }

    @GetMapping(value = "/feign/timeout")
    public String paymentFeignTimeout()
    {
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return port;
    }

    @GetMapping("/zipkin")
    public String paymentZipkin()
    {
        return "ServerPort: " + port + ": com.atguigu.springcloud.controller.PaymentController.paymentZipkin";
    }
}
