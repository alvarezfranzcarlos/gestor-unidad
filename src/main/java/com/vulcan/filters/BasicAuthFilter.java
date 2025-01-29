package com.vulcan.filters;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.security.Principal;
import java.util.Base64;

@Provider
@Priority(1)
@ApplicationScoped
public class BasicAuthFilter implements ContainerRequestFilter {

    @Inject
    @ConfigProperty(name = "app.security.username")
    String configuredUsername;

    @Inject
    @ConfigProperty(name = "app.security.password")
    String configuredPassword;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String authHeader = requestContext.getHeaderString("Authorization");

        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        String base64Credentials = authHeader.substring("Basic ".length());
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));

        String[] values = credentials.split(":", 2);
        if (values.length != 2 || !authenticate(values[0], values[1])) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        // Configurar el contexto de seguridad con el usuario autenticado
        final String username = values[0];
        requestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return () -> username;
            }

            @Override
            public boolean isUserInRole(String role) {
                return "admin".equals(role); // Ajusta según los roles necesarios
            }

            @Override
            public boolean isSecure() {
                return requestContext.getUriInfo().getRequestUri().getScheme().equals("https");
            }

            @Override
            public String getAuthenticationScheme() {
                return "Basic";
            }
        });
    }

    private boolean authenticate(String username, String password) {
        return configuredUsername.equals(username) && configuredPassword.equals(password);
    }
}
