package helpers;

import java.io.FileInputStream;
import java.util.Properties;

public class HelperLerProperties {

    private static Properties getProperties() {
        try {
            Properties properties = new Properties();
            FileInputStream propertiesFile = new FileInputStream("./src/test/resources/config.properties");
            properties.load(propertiesFile);
            return properties;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid property file path:");
        }
    }

    public static String getProperty(String property) {
        return getProperties().getProperty(property);
    }
}
