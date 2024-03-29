package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController
{
    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage()
    {
        messageProvider.send();
        return "success";
    }

    @GetMapping(value = "/sendByFunction")
    public String sendMessage2()
    {
        messageProvider.sendByFunction();
        return "success";
    }

}