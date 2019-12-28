package com.xyz.caofancpu.d8ger.core;

/**
 * 生成文件枚举
 *
 * @author caofanCPU
 */
public enum KeyEnum {
    MO,
    SWAGGER_MO,
    MO_MAPPER,
    MO_EXAMPLE,
    MO_MAPPER_XML,
    MO_SERVICE_INTERFACE,
    MO_SERVICE_IMPL,
    MO_CONTROLLER,
    MO_SQL,

    ;

    KeyEnum() {}

    /**
     * 需要导入枚举类的JAVA文件
     *
     * @param key
     * @return
     */
    public static boolean needImportEnumClass(KeyEnum key) {
        return MO == key || SWAGGER_MO == key || MO_EXAMPLE == key;
    }

    /**
     * 不需要创建文件
     *
     * @param key
     * @return
     */
    public static boolean ignoreCreateFile(KeyEnum key) {
        return MO_CONTROLLER == key;
    }
}
