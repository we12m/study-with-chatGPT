package com.example.demo.controller;

import com.example.demo.service.NotificationProducer;
import com.example.demo.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationProducer notificationProducer;

    public NotificationController(NotificationService notificationService, NotificationProducer notificationProducer) {
        this.notificationService = notificationService;
        this.notificationProducer = notificationProducer;
    }

    @GetMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestParam String email) {
        notificationService.sendWelcomeEmail(email);
        return ResponseEntity.ok("Email sending initiated for " + email);
    }

    @GetMapping
    public ResponseEntity<String> sendNotification(@RequestParam String message) {
        notificationProducer.sendNotification(message);
        return ResponseEntity.ok("Notification sent: " + message);
    }
}
