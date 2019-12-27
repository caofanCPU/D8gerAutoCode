package com.xyz.caofancpu.d8ger.core;

/**
 * 模板关键字枚举
 *
 * @author caofanCPU
 */
public enum TemplateKeyWordEnum {
    MO_NAME_KEY("@MoName@"),
    PACKAGE_NAME_KEY("@package@"),
    UNCAPITALLIZE_MO_NAME_KEY("@uncapitallizeMoName@"),
    AUTHOR_KEY("@d8Author@"),
    MO_FIELD_KEY("@field@"),
    SWAGGER_MO_FIELD_KEY("@swaggerField@"),
    SQL_MO_TABLE_KEY("@mo_table_name@"),
    MO_EXAMPLE_KEY("@ExampleDefinitionMethod@"),
    SQL_MO_COLUMN_KEY("@sql_column@"),
    SQL_MO_ID_KEY("@id@"),
    SQL_MO_CREATE_TIME_KEY("@create_time@"),
    SQL_MO_UPDATE_TIME_KEY("@update_time@"),

    ;


    private String name;

    TemplateKeyWordEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
