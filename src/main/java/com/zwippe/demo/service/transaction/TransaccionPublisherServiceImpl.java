package com.zwippe.demo.service.transaction;

import com.zwippe.demo.event.TransaccionEvent;
import com.zwippe.demo.service.TransaccionPublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransaccionPublisherServiceImpl implements TransaccionPublisherService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final Random random = new Random();

    @Scheduled(fixedRate = 5000)
    public void publicarEvento() {
        TransaccionEvent evento = new TransaccionEvent(
                UUID.randomUUID(),
                BigDecimal.valueOf(random.nextInt(100000) + 1000),
                "Transferencia automática"
        );

        kafkaTemplate.send("notificaciones-topic", "TRANSACCION", evento);
        log.info("🚀 Evento de TRANSACCION publicado: {}", evento);
    }
}
