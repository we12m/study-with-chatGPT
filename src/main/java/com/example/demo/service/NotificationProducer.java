package com.example.demo.service;

import com.example.demo.config.RabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    private final AmqpTemplate amqpTemplate;

    public NotificationProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendNotification(String message) {
        System.out.println("Sending notification: " + message);
        amqpTemplate.convertAndSend(RabbitConfig.NOTIFICATION_QUEUE, message);
        System.out.println("Notification sent: " + message);
    }
}
