package com.zwippe.demo.service.mail;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailSenderService {

    private JavaMailSender mailSender;

    public void enviarCorreo(String para, String asunto, String contenido) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(para);
        mensaje.setSubject(asunto);
        mensaje.setText(contenido);
        mensaje.setFrom("tu.email@gmail.com");
        mailSender.send(mensaje);
    }
}
