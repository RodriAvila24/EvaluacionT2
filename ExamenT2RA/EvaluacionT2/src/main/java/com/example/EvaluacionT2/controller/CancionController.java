package com.example.EvaluacionT2.controller;

import com.example.EvaluacionT2.model.Cancion;
import com.example.EvaluacionT2.service.CancionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    private final CancionService cancionService;

    public CancionController(CancionService cancionService) {
        this.cancionService = cancionService;
    }

    @GetMapping
    public List<Cancion> listarCanciones() {
        return cancionService.listar();
    }

    @GetMapping("/{id}")
    public Cancion obtenerCancion(@PathVariable Integer id) {
        return cancionService.obtener(id);
    }

    @PostMapping
    public Cancion crearCancion(@RequestBody Cancion cancion) {
        return cancionService.crear(cancion);
    }

    @PutMapping("/{id}")
    public Cancion actualizarCancion(@PathVariable Integer id, @RequestBody Cancion cancion) {
        return cancionService.actualizar(id, cancion);
    }

    @DeleteMapping("/{id}")
    public void eliminarCancion(@PathVariable Integer id) {
        cancionService.eliminar(id);
    }
}

