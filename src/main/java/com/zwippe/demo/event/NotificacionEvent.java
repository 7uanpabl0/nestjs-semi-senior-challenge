package com.zwippe.demo.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = LoginEvent.class, name = "LOGIN"),
        @JsonSubTypes.Type(value = TransaccionEvent.class, name = "TRANSACCION"),
        @JsonSubTypes.Type(value = SeguridadEvent.class, name = "SEGURIDAD")
})
public interface NotificacionEvent {
    String getTipo();
    UUID getUsuarioId();
}