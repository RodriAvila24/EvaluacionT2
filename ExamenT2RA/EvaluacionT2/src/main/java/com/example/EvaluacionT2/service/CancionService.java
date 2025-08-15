package com.example.EvaluacionT2.service;

import com.example.EvaluacionT2.exception.BadRequestException;
import com.example.EvaluacionT2.exception.NotFoundException;
import com.example.EvaluacionT2.model.Artista;
import com.example.EvaluacionT2.model.Cancion;
import com.example.EvaluacionT2.repository.CancionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancionService {

    private final CancionRepository repo;
    private final ArtistaService artistaService;

    public CancionService(CancionRepository repo, ArtistaService artistaService) {
        this.repo = repo;
        this.artistaService = artistaService;
    }

    public List<Cancion> listar() {
        return repo.findAll();
    }

    public Cancion obtener(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Canción no encontrada"));
    }

    public Cancion crear(Cancion c) {
        if (c.getArtista() == null || c.getArtista().getIdArtista() == null) {
            throw new BadRequestException("Debe especificar el idArtista dentro de 'artista'.");
        }

        Integer idArtista = c.getArtista().getIdArtista();
        Artista art = artistaService.obtener(idArtista);

        if (repo.findByTituloAndArtista_IdArtista(c.getTitulo(), idArtista).isPresent()) {
            throw new BadRequestException("La canción ya existe para este artista.");
        }

        c.setArtista(art);
        return repo.save(c);
    }

    public Cancion actualizar(Integer id, Cancion c) {
        Cancion existente = obtener(id);

        if (c.getTitulo() != null) existente.setTitulo(c.getTitulo());
        if (c.getDuracionSegundos() != null) existente.setDuracionSegundos(c.getDuracionSegundos());
        if (c.getFechaLanzamiento() != null) existente.setFechaLanzamiento(c.getFechaLanzamiento());
        if (c.getNumeroReproducciones() != null) existente.setNumeroReproducciones(c.getNumeroReproducciones());


        if (c.getArtista() != null && c.getArtista().getIdArtista() != null) {
            Artista art = artistaService.obtener(c.getArtista().getIdArtista());
            existente.setArtista(art);
        }

        Integer idArtistaFinal = existente.getArtista().getIdArtista();
        String tituloFinal = existente.getTitulo();

        repo.findByTituloAndArtista_IdArtista(tituloFinal, idArtistaFinal)
                .filter(enBD -> !enBD.getIdCancion().equals(existente.getIdCancion()))
                .ifPresent(enBD -> {
                    throw new BadRequestException("Ya existe una canción con ese título para el artista indicado.");
                });

        return repo.save(existente);
    }

    public void eliminar(Integer id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("No se puede eliminar: canción no encontrada");
        }
        repo.deleteById(id);
    }
}
