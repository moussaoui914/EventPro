package org.example.eventproo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EventProoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventProoApplication.class, args);
    }
}