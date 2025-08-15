package com.example.EvaluacionT2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cancion", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"titulo", "id_artista"})
})
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cancion")
    private Integer idCancion;

    @Column(length = 150, nullable = false)
    private String titulo;

    @Column(name = "duracion_segundos", nullable = false)
    private Integer duracionSegundos;

    @Column(name = "fecha_lanzamiento")
    private LocalDate fechaLanzamiento;

    @Column(name = "numero_reproducciones", nullable = false)
    private Integer numeroReproducciones = 0;

    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false)
    @JsonIgnoreProperties("canciones") // Evita que al traer la canción, el artista traiga todas sus canciones
    private Artista artista;


    public Integer getIdCancion() { return idCancion; }
    public void setIdCancion(Integer idCancion) { this.idCancion = idCancion; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public Integer getDuracionSegundos() { return duracionSegundos; }
    public void setDuracionSegundos(Integer duracionSegundos) { this.duracionSegundos = duracionSegundos; }
    public LocalDate getFechaLanzamiento() { return fechaLanzamiento; }
    public void setFechaLanzamiento(LocalDate fechaLanzamiento) { this.fechaLanzamiento = fechaLanzamiento; }
    public Integer getNumeroReproducciones() { return numeroReproducciones; }
    public void setNumeroReproducciones(Integer numeroReproducciones) { this.numeroReproducciones = numeroReproducciones; }
    public Artista getArtista() { return artista; }
    public void setArtista(Artista artista) { this.artista = artista; }
}



