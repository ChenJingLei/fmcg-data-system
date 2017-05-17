package com.john.cloud.provider.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cjl20 on 2017/5/14.
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue NameQueue() {
        return new Queue("name-node");
    }

    @Bean
    public Queue URLQueue() {
        return new Queue("url-node");
    }
}
