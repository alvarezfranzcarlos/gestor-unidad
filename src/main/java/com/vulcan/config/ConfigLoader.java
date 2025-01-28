package com.vulcan.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private final Properties properties = new Properties();

    public ConfigLoader(String profile) {
        String filename = "application-" + profile + ".properties";
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                throw new IllegalArgumentException("Archivo de configuración no encontrado: " + filename);
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar el archivo de configuración: " + filename, e);
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
