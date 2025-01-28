package com.vulcan.resources;

import com.vulcan.model.Alumno;
import com.vulcan.repository.AlumnoRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/alumnos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlumnoResource {

    private final AlumnoRepository alumnoRepository;

    public AlumnoResource(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @GET
    public List<Alumno> getAll() {
        return alumnoRepository.listAll();
    }

    @POST
    @Transactional
    public void add(Alumno alumno) {
        if (alumno.getCurso() == null || alumno.getCurso().getId() == null) {
            throw new WebApplicationException("El curso asociado es obligatorio", 400);
        }
        alumnoRepository.persist(alumno);
    }
}
