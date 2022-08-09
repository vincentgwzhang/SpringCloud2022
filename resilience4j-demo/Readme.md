@resilience4j.ratelimiter
限流模式: 不说成功失败，而是在规定的时间内只能被调动的次数 ---> 这个值可以通过压力测试解决

@resilience4j.timelimiter
超时模式: 等待时间，超过就返回

@resilience4j.retry
重试模式

@resilience4j.circuitbreaker