package com.xyz.caofancpu.d8ger.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;

/**
 * 常用符号
 *
 * @author caofanCPU
 */
public class ConstantUtil {

    /**
     * 枚举名称后缀
     */
    public static final String ENUM_SUFFIX = "Enum";

    /**
     * 英文逗号
     */
    public static final String ENGLISH_COMMA = ",";

    /**
     * 英文句号
     */
    public static final String ENGLISH_STOP = ".";

    /**
     * 空串
     */
    public static final String EMPTY = StringUtils.EMPTY;

    /**
     * 单个空格符
     */
    public static final String SPACE = StringUtils.SPACE;

    /**
     * Tab: 4个空格
     */
    public static final String TAB = CollectionUtil.join(Collections.nCopies(4, SPACE), EMPTY);

    /**
     * 两个Tab
     */
    public static final String DOUBLE_TAB = TAB + TAB;

    /**
     * 三个Tab
     */
    public static final String TRIPLE_TAB = DOUBLE_TAB + TAB;

    /**
     * 四个Tab 四元
     */
    public static final String QUATERNARY_TAB = TRIPLE_TAB + TAB;

    /**
     * 五个Tab
     */
    public static final String PENTA_TAB = QUATERNARY_TAB + TAB;

    /**
     * 换行
     */
    public static final String NEXT_LINE = "\n";

    /**
     * 隔一行
     */
    public static final String DOUBLE_NEXT_LINE = NEXT_LINE + NEXT_LINE;

    /**
     * 英文分号
     */
    public static final String ENGLISH_SEMICOLON = ";";

    /**
     * java文件后缀
     */
    public static final String JAVA_FILE_SUFFIX = ".java";

    /**
     * xml文件后缀
     */
    public static final String XML_FILE_SUFFIX = ".xml";

    /**
     * sql文件后缀
     */
    public static final String SQL_FILE_SUFFIX = ".sql";

    /**
     * SQL主键字段
     */
    public static final String SQL_ID = "id";

    /**
     * SQL主键字段默认定义
     */
    public static final String SQL_ID_DEFAULT_DEFINITION = TAB + "id bigint unsigned auto_increment comment 'id' primary key" + ENGLISH_COMMA + NEXT_LINE;

    /**
     * SQL创建时间字段
     */
    public static final String SQL_CREATE_TIME = "createTime";

    /**
     * SQL创建时间字段默认定义
     */
    public static final String SQL_CREATE_TIME_DEFAULT_DEFINITION = TAB + "create_time datetime default CURRENT_TIMESTAMP null comment '创建时间'" + ENGLISH_COMMA + NEXT_LINE;

    /**
     * SQL更新时间字段
     */
    public static final String SQL_UPDATE_TIME = "updateTime";

    /**
     * SQL更新时间字段默认定义
     */
    public static final String SQL_UPDATE_TIME_DEFAULT_DEFINITION = TAB + "update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'" + NEXT_LINE;

    /**
     * Mo名称后缀
     */
    public static final String MO_SUFFIX = "Mo";

    /**
     * SwaggerMo名称后缀
     */
    public static final String SWAGGER_MO_SUFFIX = "SwaggerMo";

    /**
     * MoMapper名称后缀
     */
    public static final String MO_MAPPER_NAME_SUFFIX = "Mapper";

    /**
     * MoExample名称后缀
     */
    public static final String MO_EXAMPLE_NAME_SUFFIX = "Example";

    /**
     * MoService接口名称后缀
     */
    public static final String MO_SERVICE_INTERFACE_NAME_SUFFIX = "Service";

    /**
     * MoService实现名称后缀
     */
    public static final String MO_SERVICE_IMPL_NAME_SUFFIX = "ServiceImpl";

    /**
     * MoController名称后缀
     */
    public static final String MO_CONTROLLER_NAME_SUFFIX = "Controller";

    /**
     * 自动代码文件目录名称
     */
    public static final String GENERATE_DIR = "D8AutoCode";

    /**
     * 默认修饰符
     */
    public static final String DEFAULT_ACCESS_MODIFIER = "private";

    /**
     * 通知展示ID
     */
    public static final String NOTIFICATION_GROUP_VIEW_ID = "xtools";

    /**
     * 配置文件名
     */
    public static final String D8GER_CONFIG_FILE_NAME = "d8ger.properties";

    /**
     * author配置key
     */
    public static final String CONFIG_AUTHOR_KEY = "author";

    /**
     * 默认author
     */
    public static final String DEFAULT_AUTHOR = "D8ger";
}
