package com.xyz.caofancpu.d8ger.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 属性文件
 *
 * @author caofanCPU
 */
public class PropertiesUtil {

    public static Properties loadPropertiesFromRootResource(String propertiesFileName) {
        Properties result = new Properties();
        try {
            result.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesFileName));
        } catch (IOException e) {
            // do nothing
        }
        return result;
    }

}
