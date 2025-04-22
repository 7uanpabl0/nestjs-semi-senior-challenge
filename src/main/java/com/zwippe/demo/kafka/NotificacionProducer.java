package com.zwippe.demo.kafka;

import com.zwippe.demo.event.LoginEvent;
import com.zwippe.demo.event.NotificacionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacionProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void enviarEvento(NotificacionEvent evento) {
        kafkaTemplate.send("notificaciones-topic", evento.getTipo(), evento);
    }

    public void enviarLogin(LoginEvent evento) {
        kafkaTemplate.send("Login-topic", evento);
    }
}
