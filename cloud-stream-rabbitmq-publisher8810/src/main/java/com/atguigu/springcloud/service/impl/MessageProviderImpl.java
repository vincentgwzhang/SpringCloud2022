package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

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

    private AtomicInteger id = new AtomicInteger();

    @Override
    public void send()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String content = String.format("[app-publisher], time: %s, message id = %d", dateFormat.format(new Date()), id.incrementAndGet());
        streamBridge.send("senderBindingName", content);
    }
}