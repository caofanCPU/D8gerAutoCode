package com.xyz.caofancpu.d8ger.util;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.xyz.caofancpu.d8ger.core.KeyEnum;
import com.xyz.caofancpu.d8ger.setting.D8gerProjectState;
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
     * @return
     */
    public static Properties loadPropertiesFromRootResource(Project currentProject) {
        Properties customerProperties = new Properties();
        D8gerProjectState settings = D8gerProjectState.getInstance(currentProject);
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
        customerProperties.setProperty(ConstantUtil.CONFIG_LANGUAGE_KEY, settings.defaultLocale);
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
