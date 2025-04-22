package com.zwippe.demo.repository;

import com.zwippe.demo.model.Notificacion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NotificacionRepositoryTest {

    @Autowired
    private NotificacionRepository repository;

    @Test
    void deberiaGuardarNotificacion() {
        Notificacion notificacion = new Notificacion();
        notificacion.setUsuarioId(UUID.randomUUID());
        notificacion.setTipo("TRANSACCION");
        notificacion.setDetalle("Detalle de prueba");
        notificacion.setFecha(LocalDateTime.now());

        Notificacion saved = repository.save(notificacion);

        assertThat(saved.getId()).isNotNull();
    }
}