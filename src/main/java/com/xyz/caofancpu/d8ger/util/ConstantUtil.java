package com.xyz.caofancpu.d8ger.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;

/**
 * Common symbols
 *
 * @author caofanCPU
 */
public class ConstantUtil {

    /**
     * Enum name suffix
     */
    public static final String ENUM_SUFFIX = "Enum";

    /**
     * Comma
     */
    public static final String ENGLISH_COMMA = ",";

    /**
     * Chinese Comma
     */
    public static final String CHINESE_COMMA = "，";

    /**
     * Period
     */
    public static final String ENGLISH_STOP = ".";

    /**
     * Underline
     */
    public static final String UNDERLINE = "_";

    /**
     * Empty string
     */
    public static final String EMPTY = StringUtils.EMPTY;

    /**
     * Single space character
     */
    public static final String SPACE = StringUtils.SPACE;

    /**
     * Tab: Four spaces
     */
    public static final String TAB = CollectionUtil.join(Collections.nCopies(4, SPACE), EMPTY);

    /**
     * Double Tabs
     */
    public static final String DOUBLE_TAB = TAB + TAB;

    /**
     * Triple Tabs
     */
    public static final String TRIPLE_TAB = DOUBLE_TAB + TAB;

    /**
     * Quaternary Tabs
     */
    public static final String QUATERNARY_TAB = TRIPLE_TAB + TAB;

    /**
     * Penta Tabs
     */
    public static final String PENTA_TAB = QUATERNARY_TAB + TAB;

    /**
     * Newline character
     */
    public static final String NEXT_LINE = "\n";

    /**
     * Double newlines
     */
    public static final String DOUBLE_NEXT_LINE = NEXT_LINE + NEXT_LINE;

    /**
     * Semicolon
     */
    public static final String ENGLISH_SEMICOLON = ";";

    /**
     * Java file extension
     */
    public static final String JAVA_FILE_SUFFIX = ".java";

    /**
     * xml file extension
     */
    public static final String XML_FILE_SUFFIX = ".xml";

    /**
     * sql file extension
     */
    public static final String SQL_FILE_SUFFIX = ".sql";

    /**
     * SQL ID column
     */
    public static final String SQL_ID = "id";

    /**
     * SQL ID default definition
     */
    public static final String SQL_ID_DEFAULT_DEFINITION = TAB + "id bigint unsigned auto_increment comment 'id' primary key" + ENGLISH_COMMA + NEXT_LINE;

    /**
     * SQL create time column
     */
    public static final String SQL_CREATE_TIME = "createTime";

    /**
     * SQL create time default definition
     */
    public static final String SQL_CREATE_TIME_DEFAULT_DEFINITION = TAB + "create_time datetime default CURRENT_TIMESTAMP null comment '创建时间'" + ENGLISH_COMMA + NEXT_LINE;

    /**
     * SQL update time column
     */
    public static final String SQL_UPDATE_TIME = "updateTime";

    /**
     * SQL update time default definition
     */
    public static final String SQL_UPDATE_TIME_DEFAULT_DEFINITION = TAB + "update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'" + NEXT_LINE;

    /**
     * Mo name suffix
     */
    public static final String MO_SUFFIX = "Mo";

    /**
     * SwaggerMo name suffix
     */
    public static final String SWAGGER_MO_SUFFIX = "Vo";

    /**
     * MoMapper name suffix
     */
    public static final String MO_MAPPER_NAME_SUFFIX = "Mapper";

    /**
     * MoExample name suffix
     */
    public static final String MO_EXAMPLE_NAME_SUFFIX = "Example";

    /**
     * MoService interface name suffix
     */
    public static final String MO_SERVICE_INTERFACE_NAME_SUFFIX = "Service";

    /**
     * MoService implement name suffix
     */
    public static final String MO_SERVICE_IMPL_NAME_SUFFIX = "ServiceImpl";

    /**
     * MoController name suffix
     */
    public static final String MO_CONTROLLER_NAME_SUFFIX = "Controller";

    /**
     * Auto code file directory name
     */
    public static final String GENERATE_DIR = "D8AutoCode";

    /**
     * Field access Default modifier
     */
    public static final String DEFAULT_ACCESS_MODIFIER = "private";

    /**
     * Notification group view ID
     */
    public static final String NOTIFICATION_GROUP_VIEW_ID = "xtools";

    /**
     * Config file name
     */
    public static final String D8GER_CONFIG_FILE_NAME = "d8ger.properties";

    /**
     * Author config key
     */
    public static final String CONFIG_AUTHOR_KEY = "author";

    /**
     * Default author name
     */
    public static final String DEFAULT_AUTHOR = "D8ger";

    /**
     * Interface url prefix config key
     */
    public static final String CONFIG_API_URL_PREFIX_KEY = "apiUrlPrefix";

    /**
     * Default interface url prefix
     */
    public static final String DEFAULT_API_URL_PREFIX = "/d8gerAutoCoding";

    /**
     * Language config key
     */
    public static final String CONFIG_LANGUAGE_KEY = "locale";

    /**
     * Default config language, EN(English)
     */
    public static final String DEFAULT_CONFIG_LANGUAGE = "EN";

    /**
     * Optional config language, ZN(Chinese Simplified)
     */
    public static final String OPTIONAL_CONFIG_LANGUAGE = "ZN";

    /**
     * Pagination number field name
     */
    public static final String PAGE_NUM_NAME = "pageNum";

    /**
     * Pagination size field name
     */
    public static final String PAGE_SIZE_NAME = "pageSize";
}
