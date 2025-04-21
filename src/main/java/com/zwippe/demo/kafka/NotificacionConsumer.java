package com.zwippe.demo.kafka;

import com.zwippe.demo.event.LoginEvent;
import com.zwippe.demo.event.NotificacionEvent;
import com.zwippe.demo.event.SeguridadEvent;
import com.zwippe.demo.event.TransaccionEvent;
import com.zwippe.demo.model.Notificacion;
import com.zwippe.demo.repository.NotificacionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class NotificacionConsumer {

    @Autowired
    private NotificacionRepository repository;

    @KafkaListener(topics = "notificaciones-topic", groupId = "notificaciones-consumer")
    public void consumirEvento(NotificacionEvent evento) {
        Notificacion n = new Notificacion();
        n.setUsuarioId(evento.getUsuarioId());
        n.setTipo(evento.getTipo());
        n.setDetalle(evento.toString());
        n.setFecha(LocalDateTime.now());
        repository.save(n);

        switch (evento.getTipo()) {
            case "LOGIN" -> log.info("🔐 Login recibido: {}", evento);
            case "TRANSACCION" -> log.info("💰 Transacción recibida: {}", evento);
            case "SEGURIDAD" -> log.info("📩 Código de seguridad: {}", evento);
        }
    }
    private void manejarTransaccion(TransaccionEvent evento) {
        log.info("📥 Transacción recibida: {}", evento);
    }

    private void manejarLogin(LoginEvent evento) {
        log.info("🔐 Login detectado: {}", evento);
    }

    private void manejarCodigo(SeguridadEvent evento) {
        log.info("📩 Código de seguridad: {}", evento);
    }
}
