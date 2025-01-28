package com.vulcan.service;

import com.vulcan.model.Curso;
import com.vulcan.repository.CursoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Map<String, Object> obtenerEstadisticas() {
        List<Curso> cursos = cursoRepository.listAll();

        int totalCursos = cursos.size();
        int totalAlumnos = cursos.stream().mapToInt(curso -> curso.getAlumnos().size()).sum();
        double promedioAlumnosPorCurso = totalCursos > 0 ? (double) totalAlumnos / totalCursos : 0;

        double promedioCapacidadCursos = cursos.stream()
                .mapToDouble(curso -> curso.getAlumnos().size() * 100.0 / curso.getCupoMaximo())
                .average()
                .orElse(0);

        Map<String, Object> estadisticas = new HashMap<>();
        estadisticas.put("totalCursos", totalCursos);
        estadisticas.put("totalAlumnos", totalAlumnos);
        estadisticas.put("promedioAlumnosPorCurso", promedioAlumnosPorCurso);
        estadisticas.put("promedioCapacidadCursos", promedioCapacidadCursos);

        return estadisticas;
    }

    public List<Map<String, Object>> obtenerDetallesCursos() {
        return cursoRepository.listAll().stream().map(curso -> {
            Map<String, Object> detalles = new HashMap<>();
            detalles.put("curso", curso.getNombre());
            detalles.put("alumnosPorCurso", curso.getAlumnos().size());
            detalles.put("capacidad", curso.getCupoMaximo());
            detalles.put("ocupacion", curso.getAlumnos().size() * 100.0 / curso.getCupoMaximo());
            return detalles;
        }).collect(Collectors.toList());
    }
}
