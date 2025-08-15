package com.example.EvaluacionT2.controller;

import com.example.EvaluacionT2.model.Cancion;
import com.example.EvaluacionT2.repository.CancionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final CancionRepository repo;

    public ReporteController(CancionRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/genero/{genero}")
    public Map<String, Object> cancionesPorGenero(@PathVariable String genero) {
        List<Cancion> canciones = repo.findByGenero(genero);
        int total = canciones.stream().mapToInt(Cancion::getNumeroReproducciones).sum();

        Map<String, Object> resp = new HashMap<>();
        resp.put("genero", genero);
        resp.put("canciones", canciones);
        resp.put("totalReproducciones", total);
        return resp;
    }

    @GetMapping("/estadisticas")
    public Map<String, Object> estadisticasGenerales() {
        Map<String, Object> resp = new HashMap<>();

        List<Cancion> topList = repo.cancionMasReproducida();
        resp.put("cancionMasReproducida", topList.isEmpty() ? null : topList.get(0));

        List<Object[]> masCanciones = repo.artistaConMasCanciones();
        resp.put("artistaConMasCanciones", masCanciones.isEmpty() ? null : Map.of(
                "artista", (String) masCanciones.get(0)[0],
                "totalCanciones", ((Number) masCanciones.get(0)[1]).longValue()
        ));

        List<Object[]> proms = repo.promedioDuracionPorGenero();
        List<Map<String, Object>> promResp = new ArrayList<>();
        for (Object[] row : proms) {
            promResp.add(Map.of(
                    "genero", row[0],
                    "promedioSegundos", ((Number) row[1]).doubleValue()
            ));
        }
        resp.put("promedioDuracionPorGenero", promResp);

        List<Object[]> porPais = repo.totalReproduccionesPorPais();
        List<Map<String, Object>> paisResp = new ArrayList<>();
        for (Object[] row : porPais) {
            paisResp.add(Map.of(
                    "pais", row[0],
                    "totalReproducciones", ((Number) row[1]).longValue()
            ));
        }
        resp.put("totalReproduccionesPorPais", paisResp);

        return resp;
    }

    @GetMapping("/top-artistas/{limite}")
    public List<Map<String, Object>> topArtistas(@PathVariable int limite) {
        List<Object[]> datos = repo.topArtistas(PageRequest.of(0, limite));
        List<Map<String, Object>> lista = new ArrayList<>();
        for (Object[] row : datos) {
            lista.add(Map.of(
                    "artista", row[0],
                    "totalReproducciones", ((Number) row[1]).longValue()
            ));
        }
        return lista;
    }
}

