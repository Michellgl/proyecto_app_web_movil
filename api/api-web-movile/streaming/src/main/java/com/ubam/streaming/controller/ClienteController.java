package com.ubam.streaming.controller;

import com.ubam.streaming.model.Cliente;
import com.ubam.streaming.repository.ClienteRepository;
import com.ubam.streaming.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*") // Recuerda que esto es vital para conectar con Ionic
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EmailService emailService; // Inyectamos el servicio de correo

    @GetMapping
    public List<Cliente> obtenerTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Cliente guardar(@RequestBody Cliente cliente) {
        // 1. Guardamos al cliente en la base de datos
        Cliente nuevoCliente = repository.save(cliente);

        // 2. Intentamos enviar el correo electrónico
        try {
            // Pasamos "Cliente" como el rol
            emailService.enviarCorreoBienvenida(nuevoCliente.getCorreo(), nuevoCliente.getNombre(), "Cliente");
            System.out.println("Correo de bienvenida enviado a: " + nuevoCliente.getCorreo());
        } catch (Exception e) {
            System.err.println("Error al enviar el correo al cliente: " + e.getMessage());
        }

        return nuevoCliente;
    }
}