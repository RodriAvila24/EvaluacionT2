package com.example.EvaluacionT2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "artista")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artista")
    private Integer idArtista;

    @Column(name = "nombre_artista", length = 100, nullable = false)
    private String nombreArtista;

    @Column(name = "pais_origen", length = 50, nullable = false)
    private String paisOrigen;

    @Column(name = "genero_musical", length = 50, nullable = false)
    private String generoMusical;

    @Column(name = "fecha_debut")
    private LocalDate fechaDebut;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("artista") // Evita recursión infinita
    private List<Cancion> canciones;

    public Integer getIdArtista() { return idArtista; }
    public void setIdArtista(Integer idArtista) { this.idArtista = idArtista; }
    public String getNombreArtista() { return nombreArtista; }
    public void setNombreArtista(String nombreArtista) { this.nombreArtista = nombreArtista; }
    public String getPaisOrigen() { return paisOrigen; }
    public void setPaisOrigen(String paisOrigen) { this.paisOrigen = paisOrigen; }
    public String getGeneroMusical() { return generoMusical; }
    public void setGeneroMusical(String generoMusical) { this.generoMusical = generoMusical; }
    public LocalDate getFechaDebut() { return fechaDebut; }
    public void setFechaDebut(LocalDate fechaDebut) { this.fechaDebut = fechaDebut; }
    public List<Cancion> getCanciones() { return canciones; }
    public void setCanciones(List<Cancion> canciones) { this.canciones = canciones; }
}


