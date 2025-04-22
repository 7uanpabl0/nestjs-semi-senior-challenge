package com.zwippe.demo.kafka;

import com.zwippe.demo.event.LoginEvent;
import com.zwippe.demo.event.SeguridadEvent;
import com.zwippe.demo.event.TransaccionEvent;
import com.zwippe.demo.model.Notificacion;
import com.zwippe.demo.model.Plantilla;
import com.zwippe.demo.repository.NotificacionRepository;
import com.zwippe.demo.service.mail.EmailSenderService;
import com.zwippe.demo.service.mail.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class NotificacionConsumer {

    @Autowired
    private NotificacionRepository repository;

    @Autowired
    private MailService mailService;

    @Autowired
    private EmailSenderService emailSenderService;


    @KafkaListener(topics = "notificaciones-topic", groupId = "notificaciones-consumer")
    public void consumirEvento(ConsumerRecord<String, Object> record) {
        String key = record.key();
        Object value = record.value();

        if ("LOGIN".equals(key) && value instanceof LoginEvent) {
        manejarLogin((LoginEvent) value);
    } else if ("TRANSACCION".equals(key) && value instanceof TransaccionEvent) {
        manejarTransaccion((TransaccionEvent) value);
    } else if ("SEGURIDAD".equals(key) && value instanceof SeguridadEvent) {
        manejarCodigo((SeguridadEvent) value);
    } else {
        log.warn("Tipo de evento no reconocido o clave incorrecta: {}", key);
    }
}

private void manejarTransaccion(TransaccionEvent evento) {
    Notificacion n = new Notificacion();
    n.setUsuarioId(evento.getUsuarioId());
    n.setTipo("TRANSACCION");
    n.setDetalle(evento.toString());
    n.setFecha(LocalDateTime.now());
    repository.save(n);
    log.info("💰 Transacción procesada: {}", evento);
    enviarCorreoConPlantilla(String.valueOf(n.getUsuarioId()),n.getTipo());
}

private void manejarLogin(LoginEvent evento) {
        Notificacion n = new Notificacion();
        n.setUsuarioId(evento.getUsuarioId());
        n.setTipo("LOGIN");
        n.setDetalle(evento.toString());
        n.setFecha(LocalDateTime.now());
        repository.save(n);
        log.info("🔐 Login procesado: {}", evento);
        enviarCorreoConPlantilla(String.valueOf(n.getUsuarioId()),n.getTipo());
    }

    private void manejarCodigo(SeguridadEvent evento) {
        Notificacion n = new Notificacion();
        n.setUsuarioId(evento.getUsuarioId());
        n.setTipo("SEGURIDAD");
        n.setDetalle(evento.toString());
        n.setFecha(LocalDateTime.now());
        repository.save(n);
        log.info("📩 Código de seguridad procesado: {}", evento);
    }

    public void enviarCorreoConPlantilla(String name, String monto) {
        Map<String, String> valores = Map.of(
                "nombre", name,
                "tipo", monto
        );

        Optional<Plantilla> plantillaOpt = mailService.obtenerPlantilla("email", "transferencia");

        if (plantillaOpt.isPresent()) {
            Plantilla plantilla = plantillaOpt.get();
            String contenidoFinal = mailService.reemplazarPlaceholders(plantilla.getContenido(), valores);
            //emailSenderService.enviarCorreo("destinatario@dominio.com", "Transferencia automática", contenidoFinal);
            log.info("📩 EMAIL ENVIADO: {}", contenidoFinal);
        } else {
            throw new RuntimeException("Plantilla no encontrada");
        }
    }
}