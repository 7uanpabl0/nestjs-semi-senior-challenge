package com.zwippe.demo.service.mail;


import com.zwippe.demo.model.Plantilla;
import com.zwippe.demo.repository.PlantillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class MailService {

    @Autowired
    private PlantillaRepository plantillaRepository;

    public Plantilla guardarPlantilla(Plantilla plantilla) {
        return plantillaRepository.save(plantilla);
    }

    public Optional<Plantilla> obtenerPlantilla(String tipo, String nombre) {
        return plantillaRepository.findByTipoAndNombre(tipo, nombre);
    }

    public String reemplazarPlaceholders(String contenido, Map<String, String> valores) {
        String result = contenido;
        for (Map.Entry<String, String> entry : valores.entrySet()) {
            result = result.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return result;
    }

    public void eliminarPlantilla(Long id) {
        plantillaRepository.deleteById(id);
    }
}