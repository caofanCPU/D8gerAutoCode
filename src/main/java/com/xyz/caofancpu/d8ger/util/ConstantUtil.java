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
     * 空串
     */
    public static final String EMPTY = StringUtils.EMPTY;

    /**
     * 单个空格符
     */
    public static final String SPACE = StringUtils.SPACE;

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
     * SwaggerMo名称后缀
     */
    public static final String SWAGGER_MO_SUFFIX = "Mo";

    /**
     * MoMapper名称后缀
     */
    public static final String MO_MAPPER_NAME_SUFFIX = "Mapper";

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

}
