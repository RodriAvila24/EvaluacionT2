package com.example.EvaluacionT2.service;

import com.example.EvaluacionT2.exception.NotFoundException;
import com.example.EvaluacionT2.model.Artista;
import com.example.EvaluacionT2.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistaService {
    private final ArtistaRepository repo;

    public ArtistaService(ArtistaRepository repo) {
        this.repo = repo;
    }

    public List<Artista> listar() {
        return repo.findAll();
    }

    public Artista obtener(Integer id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Artista no encontrado"));
    }

    public Artista crear(Artista a) {
        return repo.save(a);
    }

    public Artista actualizar(Integer id, Artista a) {
        Artista existente = obtener(id);
        existente.setNombreArtista(a.getNombreArtista());
        existente.setPaisOrigen(a.getPaisOrigen());
        existente.setGeneroMusical(a.getGeneroMusical());
        existente.setFechaDebut(a.getFechaDebut());
        return repo.save(existente);
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
