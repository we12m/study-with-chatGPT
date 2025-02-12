package com.example.demo.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimpleScheduler {

    // 매 분마다 실행 Cron 표현식 : 초, 분, 시, 일, 월, 요일
    @Scheduled(cron = "0 * * * * *")
    public void reportCurrentTime() {
        System.out.println("현재 시간: " + java.time.LocalDateTime.now());
    }
}
