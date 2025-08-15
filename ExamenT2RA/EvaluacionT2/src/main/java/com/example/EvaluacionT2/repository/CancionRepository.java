package com.example.EvaluacionT2.repository;

import com.example.EvaluacionT2.model.Cancion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CancionRepository extends JpaRepository<Cancion, Integer> {


    Optional<Cancion> findByTituloAndArtista_IdArtista(String titulo, Integer idArtista);

    @Query("SELECT c FROM Cancion c WHERE c.artista.generoMusical = :genero")
    List<Cancion> findByGenero(String genero);

    @Query("SELECT c FROM Cancion c ORDER BY c.numeroReproducciones DESC")
    List<Cancion> cancionMasReproducida();

    @Query("""
            SELECT c.artista.nombreArtista, COUNT(c)
              FROM Cancion c
          GROUP BY c.artista.nombreArtista
          ORDER BY COUNT(c) DESC
           """)
    List<Object[]> artistaConMasCanciones();

    @Query("""
            SELECT c.artista.generoMusical, AVG(c.duracionSegundos)
              FROM Cancion c
          GROUP BY c.artista.generoMusical
           """)
    List<Object[]> promedioDuracionPorGenero();

    @Query("""
            SELECT c.artista.paisOrigen, SUM(c.numeroReproducciones)
              FROM Cancion c
          GROUP BY c.artista.paisOrigen
           """)
    List<Object[]> totalReproduccionesPorPais();

    @Query("""
            SELECT c.artista.nombreArtista, SUM(c.numeroReproducciones) as total
              FROM Cancion c
          GROUP BY c.artista.nombreArtista
          ORDER BY total DESC
           """)
    List<Object[]> topArtistas(Pageable pageable);
}

