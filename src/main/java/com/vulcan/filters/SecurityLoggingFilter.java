package com.vulcan.filters;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class SecurityLoggingFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) {
        System.out.println("Usuario autenticado: " + requestContext.getSecurityContext().getUserPrincipal());
        System.out.println("Es admin: " + requestContext.getSecurityContext().isUserInRole("admin"));
    }
}
