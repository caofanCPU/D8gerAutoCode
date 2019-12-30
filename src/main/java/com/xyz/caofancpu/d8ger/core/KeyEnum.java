package com.xyz.caofancpu.d8ger.core;

/**
 * 生成文件枚举
 *
 * @author caofanCPU
 */
public enum KeyEnum {
    MO("autoCreateMo"),
    SWAGGER_MO("autoCreateSwaggerMo"),
    MO_MAPPER("autoCreateMapper"),
    MO_EXAMPLE("autoCreateExample"),
    MO_MAPPER_XML("autoCreateXML"),
    MO_SERVICE_INTERFACE("autoCreateServiceInterface"),
    MO_SERVICE_IMPL("autoCreateServiceImpl"),
    MO_CONTROLLER("autoCreateController"),
    MO_SQL("autoCreateDefinitionSQL"),
    FORMAT_STYLE("autoFormatStyle"),
    ;

    private String key;

    KeyEnum(String key) {
        this.key = key;
    }

    /**
     * 不需要创建文件
     *
     * @param key
     * @return
     */
    @Deprecated
    public static boolean ignoreCreateFile(KeyEnum key) {
        return MO_CONTROLLER == key;
    }

    /**
     * 需要导入枚举类的JAVA文件
     *
     * @param key
     * @return
     */
    public static boolean needImportEnumClass(KeyEnum key) {
        return MO == key || SWAGGER_MO == key || MO_EXAMPLE == key;
    }

    public String getKey() {
        return key;
    }
}
