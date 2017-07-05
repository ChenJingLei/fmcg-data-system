package com.john.cloud.provider.rabbitmq;

import com.john.crawler.model.Task;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by cjl20 on 2017/5/14.
 */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendByName(Task task) {
        System.out.println("Sender object: " + task.toString());
        this.rabbitTemplate.convertAndSend("name-node", task);
    }

    public void sendByURL(Task task) {
        System.out.println("Sender object: " + task.toString());
        this.rabbitTemplate.convertAndSend("url-node", task);
    }


}
