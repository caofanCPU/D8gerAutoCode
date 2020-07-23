package com.xyz.caofancpu.d8ger.util;

import org.apache.commons.lang3.StringUtils;

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

    /**
     * Check if the configuration item is configured as 'true' or 'TRUE'
     *
     * @param properties
     * @param propertyKey
     * @return
     */
    public static boolean checkConfigTakeEffect(Properties properties, String propertyKey) {
        String property = properties.getProperty(propertyKey);
        if (StringUtils.isBlank(property)) {
            // if no config, just ignore
            return false;
        }
        String noWhiteCharProperty = VerbalExpressionUtil.cleanWhiteChar(property);
        String[] itemConfigs = noWhiteCharProperty.split(ConstantUtil.ENGLISH_COMMA);
        return Boolean.parseBoolean(itemConfigs[0]);
    }

    /**
     * Detect if a configuration item contains a directory relative path,
     * if true then return it, otherwise return null.
     *
     * @param properties
     * @param propertyKey
     * @return
     */
    public static String detectConfigDirectoryPath(Properties properties, String propertyKey) {
        String property = properties.getProperty(propertyKey);
        String noWhiteCharProperty = VerbalExpressionUtil.cleanWhiteChar(property);
        String[] itemConfigs = noWhiteCharProperty.split(ConstantUtil.ENGLISH_COMMA);
        if (itemConfigs.length == 1) {
            // if there is just one item config, ignore too
            return null;
        }
        // here, need to create file, so we should check need put it into custom directory or not
        return StringUtils.isBlank(itemConfigs[1]) ? null : itemConfigs[1];
    }

    /**
     * Check and detect if a configuration item contains a directory relative path,
     * if true then return it, otherwise return null.
     *
     * @param properties
     * @param propertyKey
     * @return
     */
    public static String checkAndDetectConfigDirectoryPath(Properties properties, String propertyKey) {
        if (!checkConfigTakeEffect(properties, propertyKey)) {
            return null;
        }
        return detectConfigDirectoryPath(properties, propertyKey);
    }

}
