package com.zwippe.demo.service.auth;

import com.zwippe.demo.event.LoginEvent;
import com.zwippe.demo.event.NotificacionEvent;
import com.zwippe.demo.service.LoginPublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginPublisherServiceImpl implements LoginPublisherService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final String[] ipPool = {"192.168.0.1", "10.0.0.1", "172.16.0.1"};

    @Scheduled(fixedRate = 8000)
    public void publicarEvento() {
        LoginEvent evento = new LoginEvent(
                UUID.randomUUID(),
                ipPool[new Random().nextInt(ipPool.length)],
                LocalDateTime.now()
        );

        kafkaTemplate.send("notificaciones-topic", "LOGIN", evento);
        log.info("📢 Evento de LOGIN publicado: {}", evento);
    }
}