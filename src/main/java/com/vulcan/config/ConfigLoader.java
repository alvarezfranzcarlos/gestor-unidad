package com.vulcan.config;

import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class ConfigLoader {

    private final Properties properties = new Properties();

    public ConfigLoader(String profile) {
        String filename = "application-" + profile + ".properties";
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                throw new IllegalArgumentException("Archivo de configuración no encontrado: " + filename);
            }
            properties.load(input);
            // Imprime todas las propiedades cargadas
            printAllProperties();
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar el archivo de configuración: " + filename, e);
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    // Método para imprimir todas las propiedades cargadas
    private void printAllProperties() {
        System.out.println("Propiedades cargadas:");
        Set<String> propertyNames = properties.stringPropertyNames();
        for (String propertyName : propertyNames) {
            System.out.println(propertyName + " = " + properties.getProperty(propertyName));
        }
    }
}
