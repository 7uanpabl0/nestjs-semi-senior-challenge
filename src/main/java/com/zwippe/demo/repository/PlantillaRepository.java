package com.zwippe.demo.repository;

import com.zwippe.demo.model.Plantilla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantillaRepository extends JpaRepository<Plantilla, Long> {
    Optional<Plantilla> findByTipoAndNombre(String tipo, String nombre);
}