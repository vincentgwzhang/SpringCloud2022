package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("vault")
public class VaultController {

    @Value("${dbpassword}")
    private String dbPassword = "";

    @RequestMapping("/")
    public String home() {
        return dbPassword;
    }

}
