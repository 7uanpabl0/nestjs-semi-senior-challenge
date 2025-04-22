package com.zwippe.demo.kafka;


import com.zwippe.demo.event.TransaccionEvent;
import com.zwippe.demo.service.transaction.TransaccionPublisherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.kafka.core.KafkaTemplate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TransaccionPublisherServiceImplTest {

    private KafkaTemplate<String, Object> kafkaTemplate;
    private TransaccionPublisherServiceImpl service;

    @BeforeEach
    void setUp() {
        kafkaTemplate = mock(KafkaTemplate.class);
        service = new TransaccionPublisherServiceImpl(kafkaTemplate);
    }

    @Test
    void deberiaPublicarEventoTransaccion() {
        service.publicarEvento();

        ArgumentCaptor<TransaccionEvent> captor = ArgumentCaptor.forClass(TransaccionEvent.class);

        verify(kafkaTemplate).send(
                argThat(topic -> topic.equals("notificaciones-topic")),
                argThat(key -> key.equals("TRANSACCION")),
                captor.capture()
        );

        TransaccionEvent eventoEnviado = captor.getValue();
        assertThat(eventoEnviado).isNotNull();
        assertThat(eventoEnviado.getUsuarioId()).isNotNull();
        assertThat(eventoEnviado.getTipo()).isEqualTo("TRANSACCION");
    }
}