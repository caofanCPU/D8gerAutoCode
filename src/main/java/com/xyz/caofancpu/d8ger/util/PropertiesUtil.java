package com.xyz.caofancpu.d8ger.util;

import com.intellij.openapi.diagnostic.Logger;
import com.xyz.caofancpu.d8ger.core.KeyEnum;
import com.xyz.caofancpu.d8ger.setting.D8gerState;
import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 * Properties file tool
 *
 * @author caofanCPU
 */
public class PropertiesUtil {
    private static final Logger LOG = Logger.getInstance(PropertiesUtil.class);

    /**
     * Load configuration file
     *
     * @param configFileAbsolutePath
     * @return
     */
    public static Properties loadPropertiesFromRootResource(String configFileAbsolutePath) {
        Properties customerProperties = new Properties();
        D8gerState settings = D8gerState.getInstance();
        customerProperties.setProperty(KeyEnum.MO.getKey(), settings.moCheck + ConstantUtil.ENGLISH_COMMA + settings.moPath);
        customerProperties.setProperty(KeyEnum.MO_MAPPER.getKey(), settings.mapperCheck + ConstantUtil.ENGLISH_COMMA + settings.mapperPath);
        customerProperties.setProperty(KeyEnum.MO_EXAMPLE.getKey(), settings.mapperExampleCheck + ConstantUtil.ENGLISH_COMMA + settings.mapperExamplePath);
        customerProperties.setProperty(KeyEnum.MO_MAPPER_XML.getKey(), settings.mapperXmlCheck + ConstantUtil.ENGLISH_COMMA + settings.mapperXmlPath);
        customerProperties.setProperty(KeyEnum.MO_SQL.getKey(), settings.sqlCheck + ConstantUtil.ENGLISH_COMMA + settings.sqlPath);
        customerProperties.setProperty(KeyEnum.SWAGGER_MO.getKey(), settings.voCheck + ConstantUtil.ENGLISH_COMMA + settings.voPath);
        customerProperties.setProperty(KeyEnum.MO_HANDLER.getKey(), settings.handlerCheck + ConstantUtil.ENGLISH_COMMA + settings.handlerPath);
        customerProperties.setProperty(KeyEnum.MO_CONTROLLER.getKey(), settings.controllerCheck + ConstantUtil.ENGLISH_COMMA + settings.controllerPath);
        customerProperties.setProperty(KeyEnum.FORMAT_STYLE.getKey(), settings.formatStyleCheck + ConstantUtil.EMPTY);
        customerProperties.setProperty(KeyEnum.SQL_DETECT_TIME_COLUMN.getKey(), settings.detectSQLTimeColumnCheck + ConstantUtil.EMPTY);
        customerProperties.setProperty(KeyEnum.MO_MAPPER_ANNOTATION.getKey(), settings.mapperBetterThenRepositoryCheck + ConstantUtil.EMPTY);
        customerProperties.setProperty(ConstantUtil.CONFIG_AUTHOR_KEY, settings.defaultAuthor);
        customerProperties.setProperty(ConstantUtil.CONFIG_API_URL_PREFIX_KEY, settings.defaultUrlPrefix);
        customerProperties.setProperty(ConstantUtil.CONFIG_LANGUAGE_KEY, settings.defaultUrlPrefix);
        LOG.debug(KeyEnum.MO.getKey() + ": [" + customerProperties.getProperty(KeyEnum.MO.getKey()) + "]");
        LOG.debug(KeyEnum.MO_MAPPER.getKey() + ": [" + customerProperties.getProperty(KeyEnum.MO_MAPPER.getKey()) + "]");
        LOG.debug(KeyEnum.MO_EXAMPLE.getKey() + ": [" + customerProperties.getProperty(KeyEnum.MO_EXAMPLE.getKey()) + "]");
        LOG.debug(KeyEnum.MO_MAPPER_XML.getKey() + ": [" + customerProperties.getProperty(KeyEnum.MO_MAPPER_XML.getKey()) + "]");
        LOG.debug(KeyEnum.MO_SQL.getKey() + ": [" + customerProperties.getProperty(KeyEnum.MO_SQL.getKey()) + "]");
        LOG.debug(KeyEnum.SWAGGER_MO.getKey() + ": [" + customerProperties.getProperty(KeyEnum.SWAGGER_MO.getKey()) + "]");
        LOG.debug(KeyEnum.MO_HANDLER.getKey() + ": [" + customerProperties.getProperty(KeyEnum.MO_HANDLER.getKey()) + "]");
        LOG.debug(KeyEnum.MO_CONTROLLER.getKey() + ": [" + customerProperties.getProperty(KeyEnum.MO_CONTROLLER.getKey()) + "]");
        LOG.debug(KeyEnum.FORMAT_STYLE.getKey() + ": [" + customerProperties.getProperty(KeyEnum.FORMAT_STYLE.getKey()) + "]");
        LOG.debug(KeyEnum.SQL_DETECT_TIME_COLUMN.getKey() + ": [" + customerProperties.getProperty(KeyEnum.SQL_DETECT_TIME_COLUMN.getKey()) + "]");
        LOG.debug(KeyEnum.MO_MAPPER_ANNOTATION.getKey() + ": [" + customerProperties.getProperty(KeyEnum.MO_MAPPER_ANNOTATION.getKey()) + "]");
        LOG.debug(ConstantUtil.CONFIG_AUTHOR_KEY + ": [" + customerProperties.getProperty(ConstantUtil.CONFIG_AUTHOR_KEY) + "]");
        LOG.debug(ConstantUtil.CONFIG_API_URL_PREFIX_KEY + ": [" + customerProperties.getProperty(ConstantUtil.CONFIG_API_URL_PREFIX_KEY) + "]");
        LOG.debug(ConstantUtil.CONFIG_LANGUAGE_KEY + ": [" + customerProperties.getProperty(ConstantUtil.CONFIG_LANGUAGE_KEY) + "]");
//        try {
//            // Try to read configuration items
//            customerProperties.load(new FileInputStream(configFileAbsolutePath));
//        } catch (IOException e) {
//            // do nothing
//        }
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
