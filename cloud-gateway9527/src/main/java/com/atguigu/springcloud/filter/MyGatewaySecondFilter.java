package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
@Slf4j
public class MyGatewaySecondFilter implements GlobalFilter, Ordered
{
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        log.info("Order == 1, com.atguigu.springcloud.filter.MyGatewaySecondFilter is running");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder()
    {
        // The less, the higher priority level
        return 1;
    }
}