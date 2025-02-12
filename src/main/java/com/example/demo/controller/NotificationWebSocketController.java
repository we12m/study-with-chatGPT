package com.example.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationWebSocketController {

    @MessageMapping("/notify") // 클라이언트가 app/notify로 호출하면 이게 호출됨
    @SendTo("/topic/notifications") //topic/notifications 구독중인 클라이언트에 발송됨
    public String sendNotification(String message) throws Exception {
        return message;
    }
}
