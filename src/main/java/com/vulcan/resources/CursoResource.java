package com.vulcan.resources;

import com.vulcan.model.Curso;
import com.vulcan.repository.CursoRepository;
import com.vulcan.service.CursoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import org.jboss.logging.Logger;
import java.util.Map;
import java.util.List;

@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CursoResource {

    @Inject
    CursoRepository cursoRepository;

    @Context
    SecurityContext securityContext;

    @Inject
    CursoService cursoService;

    public CursoResource(CursoRepository cursoRepository, CursoService cursoService) {
        this.cursoRepository = cursoRepository;
        this.cursoService = cursoService;
    }

    @GET
    public List<Curso> getAll() {
        // Registrar el usuario autenticado en los logs
        String user = securityContext.getUserPrincipal() != null
                ? securityContext.getUserPrincipal().getName()
                : "anonymous";
        System.out.println("Usuario autenticado: " + user);
        return cursoRepository.listAll();
    }

    @POST
    @Transactional
    public void add(Curso curso) {
        cursoRepository.persist(curso);
    }

    @GET
    @Path("/estadisticas")
    public Map<String, Object> obtenerEstadisticas() {
        return cursoService.obtenerEstadisticas();
    }

    @GET
    @Path("/detalles")
    public List<Map<String, Object>> obtenerDetallesCursos() {
        return cursoService.obtenerDetallesCursos();
    }
}