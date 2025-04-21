package com.zwippe.demo.event;


import java.util.UUID;

public record LoginEvent(UUID usuarioId, String ip) implements NotificacionEvent {
    @Override public String getTipo() {
        return "LOGIN";
    }

    @Override
    public UUID getUsuarioId() {
        return null;
    }
}