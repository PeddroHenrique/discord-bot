package br.com.predio.discordbot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class TokenRetriver {

    private static final String propertiesFile = "application.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = TokenRetriver.class.getClassLoader().getResourceAsStream(propertiesFile)) {
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
