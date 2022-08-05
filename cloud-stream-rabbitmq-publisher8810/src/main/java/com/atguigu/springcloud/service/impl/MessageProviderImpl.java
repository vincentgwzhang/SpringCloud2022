package com.atguigu.springcloud.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * 这里没有 @Service, 注意！！
 * 因为 @EnableBinding 里面引用了 BindingBeansRegistrar ，它会自动注册
 */
@Service
public class MessageProviderImpl implements IMessageProvider
{
    @Autowired
    private StreamBridge streamBridge;

    @Override
    public void send()
    {
        streamBridge.send("senderBindingName", "fucking sheet");
    }
}