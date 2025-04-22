package com.zwippe.demo.plantilla;

import com.zwippe.demo.service.mail.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PlantillaServiceTest {

    @Autowired
    private MailService plantillaService;

    @Test
    void debeReemplazarCamposEnPlantilla() {
        String plantilla = "Hola {{nombre}}, transferencia de tipo {{tipo}} realizada.";
        Map<String, String> valores = Map.of(
                "nombre", "Juan",
                "tipo", "TRANSFERENCIA"
        );

        String resultado = plantillaService.reemplazarPlaceholders(plantilla, valores);

        assertThat(resultado).isEqualTo("Hola Juan, transferencia de tipo TRANSFERENCIA realizada.");
    }
}
