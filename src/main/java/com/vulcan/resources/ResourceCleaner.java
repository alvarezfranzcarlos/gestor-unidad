package com.vulcan.resources;

import io.quarkus.runtime.ShutdownEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ResourceCleaner {

    private final List<Connection> connections = new ArrayList<>();

    // Método para registrar una conexión (puedes llamarlo al iniciar tu app)
    public void registerConnection(String jdbcUrl, String user, String password) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
            connections.add(connection);
            System.out.println("Conexión registrada: " + jdbcUrl);
        } catch (SQLException e) {
            System.err.println("Error al registrar conexión: " + e.getMessage());
        }
    }

    // Método para observar el evento de apagado de Quarkus
    public void onShutdown(@Observes ShutdownEvent event) {
        System.out.println("Liberando recursos...");
        closeAllConnections();
    }

    // Método privado para cerrar todas las conexiones registradas
    private void closeAllConnections() {
        for (Connection connection : connections) {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Conexión cerrada correctamente.");
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        connections.clear(); // Limpia la lista de conexiones
    }
}
