package com.example.EvaluacionT2.repository;

import com.example.EvaluacionT2.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {
}