package com.example.demo.controller;

import com.example.demo.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestParam String email) {
        notificationService.sendWelcomeEmail(email);
        return ResponseEntity.ok("Email sending initiated for " + email);
    }
}
