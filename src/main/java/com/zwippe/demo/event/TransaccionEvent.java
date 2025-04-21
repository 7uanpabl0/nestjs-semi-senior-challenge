package com.zwippe.demo.event;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;
import java.math.BigDecimal;


public record TransaccionEvent(
        UUID usuarioId,
        BigDecimal monto,
        String descripcion
) implements NotificacionEvent {

    @Override
    public String getTipo() {
        return "TRANSACCION";
    }

    @Override
    public UUID getUsuarioId() {
        return usuarioId;
    }

    @JsonCreator
    public TransaccionEvent(
            @JsonProperty("usuarioId") UUID usuarioId,
            @JsonProperty("monto") BigDecimal monto,
            @JsonProperty("descripcion") String descripcion
    ) {
        this.usuarioId = usuarioId;
        this.monto = monto;
        this.descripcion = descripcion;
    }
}