package com.ubam.streaming.controller;

import com.ubam.streaming.model.Usuario;
import com.ubam.streaming.repository.UsuarioRepository;
import com.ubam.streaming.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EmailService emailService; // Inyectamos el servicio de correo

    @GetMapping
    public List<Usuario> obtenerTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        // 1. Guardamos al usuario en la base de datos
        Usuario nuevoUsuario = repository.save(usuario);

        // 2. Enviamos el correo de notificación
        try {
            emailService.enviarCorreoBienvenida(nuevoUsuario.getCorreo(), nuevoUsuario.getNombre(), "Administrador");
            System.out.println("Correo enviado con éxito a: " + nuevoUsuario.getCorreo());
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }

        return nuevoUsuario;
    }
}