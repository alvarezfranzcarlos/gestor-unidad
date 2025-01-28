package com.vulcan.repository;

import com.vulcan.model.Alumno;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlumnoRepository implements PanacheRepository<Alumno> {
}