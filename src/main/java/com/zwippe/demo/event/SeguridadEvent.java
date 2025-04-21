package com.zwippe.demo.event;


import java.util.UUID;

public record SeguridadEvent(UUID usuarioId, String codigo) implements NotificacionEvent {
    @Override public String getTipo() {
        return "SEGURIDAD";
    }

    @Override
    public UUID getUsuarioId() {
        return null;
    }
}