package com.ubam.streaming.controller;

import com.ubam.streaming.model.Pelicula;
import com.ubam.streaming.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin(origins = "*")
public class PeliculaController {

    @Autowired
    private PeliculaRepository repository;

    @GetMapping
    public List<Pelicula> obtenerTodas() {
        return repository.findAll();
    }

    @PostMapping
    public Pelicula guardar(@RequestBody Pelicula pelicula) {
        return repository.save(pelicula);
    }
}