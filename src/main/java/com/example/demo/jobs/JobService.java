package com.example.demo.jobs;

import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {

    private final UserService userService;

    @Scheduled(cron = "0 0 3 ? * 2/2") // в 3 часа ночи начиная с понедельника каждые 2 дня
    public void invalidateSessions() {
        userService.invalidateSessions();
    }

    @Scheduled(fixedDelay = 3000)  // каждые 3 секунды
    public void invalidateSessionsFixedDelay() {
        userService.invalidateSessions();
    }

}
