package com.ubam.streaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoBienvenida(String destino, String nombre, String rol) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setFrom("TUCORREO@gmail.com"); // Tu correo
        mensaje.setTo(destino);
        mensaje.setSubject("¡Bienvenido a CineNube!");

        String texto = "Hola " + nombre + ",\n\n"
                + "Tu cuenta como " + rol + " ha sido registrada con éxito en nuestra plataforma.\n"
                + "¡Gracias por unirte a CineNube!\n\n"
                + "Saludos del equipo de soporte.";

        mensaje.setText(texto);

        // Enviamos el correo
        mailSender.send(mensaje);
    }
}