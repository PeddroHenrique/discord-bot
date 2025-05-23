package br.com.predio.discordbot.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class PropertiesRetriver {

    private static final String propertiesFile = "application.properties";
    private static final Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = PropertiesRetriver.class.getClassLoader().getResourceAsStream(propertiesFile)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new RuntimeException("Arquivo " + propertiesFile + " não encontrado no classpath.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo de propriedades.", e);
        }
    }

    public static String getProperties(String key) {
        return properties.getProperty(key);
    }
}
