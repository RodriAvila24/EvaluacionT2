package com.example.EvaluacionT2.controller;

import com.example.EvaluacionT2.model.Artista;
import com.example.EvaluacionT2.service.ArtistaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {

    private final ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping
    public List<Artista> listarArtistas() {
        return artistaService.listar();
    }

    @GetMapping("/{id}")
    public Artista obtenerArtista(@PathVariable Integer id) {
        return artistaService.obtener(id);
    }

    @PostMapping
    public Artista crearArtista(@RequestBody Artista artista) {
        return artistaService.crear(artista);
    }

    @PutMapping("/{id}")
    public Artista actualizarArtista(@PathVariable Integer id, @RequestBody Artista artista) {
        return artistaService.actualizar(id, artista);
    }

    @DeleteMapping("/{id}")
    public void eliminarArtista(@PathVariable Integer id) {
        artistaService.eliminar(id);
    }
}

