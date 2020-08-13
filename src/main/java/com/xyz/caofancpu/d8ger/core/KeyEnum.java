package com.xyz.caofancpu.d8ger.core;

/**
 * Generate file enum
 *
 * @author caofanCPU
 */
public enum KeyEnum {
    MO("autoCreateMo"),
    SWAGGER_MO("autoCreateSwaggerMo"),
    MO_MAPPER("autoCreateMapper"),
    MO_EXAMPLE("autoCreateExample"),
    MO_MAPPER_XML("autoCreateXML"),
    MO_HANDLER("autoCreateHandler"),
    MO_CONTROLLER("autoCreateController"),
    MO_SQL("autoCreateDefinitionSQL"),
    FORMAT_STYLE("autoFormatStyle"),
    SQL_DETECT_TIME_COLUMN("autoDetectSQLTimeColumn"),
    MO_MAPPER_ANNOTATION("mapperBetterThenRepository"),
    ;

    private String key;

    KeyEnum(String key) {
        this.key = key;
    }

    /**
     * Judge create file or not
     *
     * @param key
     * @return
     */
    @Deprecated
    public static boolean ignoreCreateFile(KeyEnum key) {
        return MO_CONTROLLER == key;
    }

    /**
     * Judge import enumeration classes or not in Java files
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
