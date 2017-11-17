package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class PropertyReader {
    public static String loadProperty(String propertyName) {
        String property = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader
                .getResourceAsStream("environment.properties")) {
            Properties props = new Properties();
            props.load(resourceStream);
            property = props.getProperty(propertyName);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidPropertiesFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return property;
    }

}
