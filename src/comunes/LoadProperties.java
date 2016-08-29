/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author Vidal
 */
public class LoadProperties {

    /**
     * pathProperties
     */
    private final String pathProperties;
    /**
     * inputStreamProperties
     */
    private InputStream inputStreamProperties;
    /**
     * propiedades
     */
    private Properties propiedades;

    /**
     *
     * @param pathProperties
     */
    public LoadProperties(final String pathProperties) {
        this.pathProperties = pathProperties;
    }

    /**
     * loadFileProperties
     *
     * @throws java.io.IOException
     */
    public void loadFileProperties() throws IOException {
        try {
            propiedades = new Properties();
            inputStreamProperties = getClass().getClassLoader()
                    .getResourceAsStream(pathProperties);

            if (inputStreamProperties != null) {
                propiedades.load(inputStreamProperties);
            } else {
                throw new FileNotFoundException("property file '"
                        + pathProperties + "' not found in the classpath");
            }
            inputStreamProperties.close();
        } catch (FileNotFoundException exception) {
            throw exception;
        }
    }

    /**
     * getPropertieValue
     *
     * @param key
     * @return
     */
    public String getPropertieValue(final String key) {
        String value = null;
        if (propiedades != null && key != null) {
            value = propiedades.getProperty(key);
        }
        return value;
    }

    /**
     * getPropertiesValues
     *
     * @return
     */
    public Map<String, String> getPropertiesValues() {
        final Map<String, String> mapProperties = new HashMap<>();
        final Set<String> namesProperties;
        String value;
        if (propiedades != null) {
            namesProperties = propiedades.stringPropertyNames();
            for (final String key : namesProperties) {
                value = propiedades.getProperty(key);
                mapProperties.put(key, value);
            }
        }
        return mapProperties;
    }

}
