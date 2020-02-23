package com.xyz.caofancpu.d8ger.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Properties file tool
 *
 * @author caofanCPU
 */
public class PropertiesUtil {

    /**
     * Load configuration file
     *
     * @param configFileAbsolutePath
     * @return
     */
    public static Properties loadPropertiesFromRootResource(String configFileAbsolutePath) {
        Properties customerProperties = new Properties();
        try {
            // Try to read configuration items
            customerProperties.load(new FileInputStream(configFileAbsolutePath));
        } catch (IOException e) {
            // do nothing
        }
        return customerProperties;
    }

}
