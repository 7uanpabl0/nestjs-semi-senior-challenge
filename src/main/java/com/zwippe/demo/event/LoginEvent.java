package com.zwippe.demo.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class LoginEvent {

    private final UUID usuarioId;
    private final String ip;
    private final LocalDateTime fecha;

    @JsonCreator
    public LoginEvent(
            @JsonProperty("usuarioId") UUID usuarioId,
            @JsonProperty("ip") String ip,
            @JsonProperty("fecha") LocalDateTime fecha
    ) {
        this.usuarioId = usuarioId;
        this.ip = ip;
        this.fecha = fecha;
    }
}
