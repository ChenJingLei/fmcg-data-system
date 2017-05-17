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
    public Queue taskQueue() {
        return new Queue("task-node");
    }
}
