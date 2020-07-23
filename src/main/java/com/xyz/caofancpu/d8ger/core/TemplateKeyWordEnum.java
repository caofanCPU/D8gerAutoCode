package com.xyz.caofancpu.d8ger.core;

/**
 * Template keyword enum
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
    XML_BASE_COLUMN_LIST_KEY("@BaseColumnList@"),
    XML_SELECT_BASE_COLUMN_LIST_KEY("@SelectBaseColumnList@"),
    XML_BATCH_UPDATE_NONNULL_FIELD_BY_ID_KEY("@BatchUpdateNonNullFieldByID@"),
    XML_UPDATE_NONNULL_FIELD_BY_ID_KEY("@UpdateNonNullFieldByID@"),
    XML_UPDATE_NONNULL_FIELD_BY_EXAMPLE_KEY("@UpdateNonNullFieldByExample@"),
    XML_INSERT_COLUMN_LIST_KEY("@InsertField@"),
    XML_BATCH_INSERT_COLUMN_LIST_KEY("@BatchInsertField@"),
    XML_INSERT_SELECTIVE_COLUMN_LIST_KEY("@NonNullColumnList@"),
    XML_INSERT_SELECTIVE_FIELD_LIST_KEY("@NonNullInsertField@"),
    XML_MO_LIST_QUERY_KEY("@MoListQuery@"),
    API_URL_PREFIX_KEY("@apiUrlPrefix@"),

    MO_PACKAGE_NAME_KEY("@moPackage@"),
    MAPPER_PACKAGE_NAME_KEY("@mapperPackage@"),
    MAPPER_ANNOTATION_KEY("@MapperAnnotation@"),
    MAPPER_ANNOTATION_PACKAGE_KEY("@MapperAnnotationPackage@"),
    MO_EXAMPLE_PACKAGE_NAME_KEY("@moExamplePackage@"),
    SWAGGER_MO_PACKAGE_NAME_KEY("@swaggerMoPackage@"),
    SERVICE_INTERFACE_PACKAGE_NAME_KEY("@serviceInterfacePackage@"),
    SERVICE_IMPLEMENT_PACKAGE_NAME_KEY("@serviceImplPackage@"),
    CONTROLLER_PACKAGE_NAME_KEY("@controllerPackage@"),
    ;


    private String name;

    TemplateKeyWordEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
