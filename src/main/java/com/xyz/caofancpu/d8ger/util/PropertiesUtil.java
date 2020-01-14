package com.xyz.caofancpu.d8ger.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 属性文件
 *
 * @author caofanCPU
 */
public class PropertiesUtil {

    /**
     * 加载配置文件
     *
     * @param configFileAbsolutePath
     * @return
     */
    public static Properties loadPropertiesFromRootResource(String configFileAbsolutePath) {
        Properties customerProperties = new Properties();
        try {
            // 尝试读取配置项
            customerProperties.load(new FileInputStream(configFileAbsolutePath));
        } catch (IOException e) {
            // do nothing
        }
        return customerProperties;
    }

}
