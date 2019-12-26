package com.xyz.caofancpu.d8ger.core;

/**
 * 模板关键字枚举
 *
 * @author caofanCPU
 */
public enum TemplateKeyWordEnum {
    MO_NAME_KEY("@MoName@"),
    UNCAPITALLIZE_MO_NAME_KEY("@uncapitallizeMoName@"),
    D8_AUTHOR_KEY("@d8Author@"),
    D8_MO_FIELD_KEY("@field@"),
    D8_SWAGGER_MO_FIELD_KEY("@swaggerField@"),

    ;


    private String name;

    TemplateKeyWordEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
