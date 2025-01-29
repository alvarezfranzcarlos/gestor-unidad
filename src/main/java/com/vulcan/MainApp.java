package com.vulcan;

import com.vulcan.config.AppConfig;

public class MainApp {
    public static void main(String[] args) {
        // Determinar el perfil desde una variable de entorno o usar "local" por defecto
        String profile = System.getenv("quarkus.profile");
        if (profile == null) {
            profile = "prod";
        }

        // Cargar configuraciones según el perfil
        AppConfig.load(profile);

        // Usar configuraciones cargadas
        System.out.println("Perfil activo: " + profile);
        System.out.println("Base de datos URL: " + AppConfig.get("quarkus.datasource.jdbc.url"));

    }
}
