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
     * 采用缓存机制
     */
    private static Properties customerProperties = null;

    /**
     * 加载配置文件
     *
     * @param configFileAbsolutePath
     * @return
     */
    public static Properties loadPropertiesFromRootResource(String configFileAbsolutePath) {
        // 先置空待GC
        customerProperties = null;
        // 再新建
        customerProperties = new Properties();
        try {
            // 尝试读取配置项
            customerProperties.load(new FileInputStream(configFileAbsolutePath));
        } catch (IOException e) {
            // do nothing
        }
        return customerProperties;
    }

}
