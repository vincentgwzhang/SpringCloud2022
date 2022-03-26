package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.dto.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("eureka")
@Slf4j
public class ServiceDiscoverController {

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/discovery")
    public CommonResult<List<String>> discovery() {
        List<String> result = new ArrayList<>();

        List<String> services = discoveryClient.getServices();
        for (String str : services) {
            log.info("service:" + str);
            result.add(str);
        }

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance serviceInstance : serviceInstances) {
            String serviceDetail = serviceInstance.getServiceId() + " | " + serviceInstance.getHost() + " | " + serviceInstance.getPort() + " | " + serviceInstance.getUri();
            log.info("serviceDetail:" + serviceDetail);
            result.add(serviceDetail);
        }

        return new CommonResult<>(HttpStatus.OK.value(), "discovery message", result);
    }

}
