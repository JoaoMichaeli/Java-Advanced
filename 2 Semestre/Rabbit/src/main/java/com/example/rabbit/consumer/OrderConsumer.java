package com.example.rabbit.consumer;

import com.example.rabbit.config.RabbitConfig;
import com.example.rabbit.model.OrderCreatedMessage;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;


@Component
public class OrderConsumer {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void processOrderCreated(OrderCreatedMessage message) {
    }

}
