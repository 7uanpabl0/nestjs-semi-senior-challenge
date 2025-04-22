package com.zwippe.demo.controller;

import com.zwippe.demo.event.LoginEvent;
import com.zwippe.demo.event.SeguridadEvent;
import com.zwippe.demo.event.TransaccionEvent;
import com.zwippe.demo.kafka.NotificacionProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionProducer producer;

    @PostMapping("/transaccion")
    public ResponseEntity<Void> transaccion(@RequestBody TransaccionEvent evento) {
        producer.enviarEvento(evento);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginEvent evento) {
        producer.enviarLogin(evento);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/seguridad")
    public ResponseEntity<Void> seguridad(@RequestBody SeguridadEvent evento) {
        producer.enviarEvento(evento);
        return ResponseEntity.ok().build();
    }
}
