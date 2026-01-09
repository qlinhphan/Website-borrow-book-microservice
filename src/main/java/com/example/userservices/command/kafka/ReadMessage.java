package com.example.userservices.command.kafka;

import java.net.SocketTimeoutException;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "default-group", topics = "topic")
public class ReadMessage {

    @KafkaHandler
    @RetryableTopic(backoff = @Backoff(value = 1000L, multiplier = 2.0), attempts = "5", autoCreateTopics = "true", include = SocketTimeoutException.class, exclude = NullPointerException.class)
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}
