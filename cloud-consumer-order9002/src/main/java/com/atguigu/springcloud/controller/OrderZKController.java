package com.atguigu.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderZKController
{
    @Value("${application.payment-service-url}")
    private String paymentServiceURL;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/consumer/discovery/client")
    public String findDifferentService() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance serviceInstance : serviceInstances) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(";host:" + serviceInstance.getHost());
            stringBuffer.append(";uri:" + serviceInstance.getUri());
            stringBuffer.append(";scheme:" + serviceInstance.getScheme());
            stringBuffer.append(";serviceID:" + serviceInstance.getServiceId());
            stringBuffer.append(";port:" + serviceInstance.getPort());
            System.out.println(stringBuffer);
        }
        return "" + serviceInstances.size();
    }

    @GetMapping(value = "/consumer/payment/zk")
    public String paymentInfo()
    {
        return restTemplate.getForObject(paymentServiceURL+"/payment/zk",String.class);
    }
}