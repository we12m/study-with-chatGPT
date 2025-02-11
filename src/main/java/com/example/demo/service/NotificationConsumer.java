package com.example.demo.service;

import com.example.demo.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @RabbitListener(queues =  RabbitConfig.NOTIFICATION_QUEUE)
    public void receiveNotitication(String message) {
        try {
            // 10초(10000밀리초) 지연
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Received notification: " + message);
    }
}
