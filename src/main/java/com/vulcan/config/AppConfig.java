package com.vulcan.config;

public class AppConfig {

    private static ConfigLoader configLoader;

    public static void load(String profile) {
        configLoader = new ConfigLoader(profile);
    }

    public static String get(String key) {
        if (configLoader == null) {
            throw new IllegalStateException("El archivo de configuración no ha sido cargado.");
        }
        return configLoader.get(key);
    }
}
