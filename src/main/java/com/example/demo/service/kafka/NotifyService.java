package com.example.demo.service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotifyService {

    private final KafkaTemplate<String, String> template;

    @Value("${kafka.topics.notification}")
    private String topic;

    public void sendNotification(String msg) {
        try {
            template.send(topic, String.valueOf(UUID.randomUUID()), msg)
                    .addCallback(result -> {
                                if (result != null) {
                                    log.info("notification send successful");
                                }
                            }, ex -> log.error("notification send error")
                    );

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
