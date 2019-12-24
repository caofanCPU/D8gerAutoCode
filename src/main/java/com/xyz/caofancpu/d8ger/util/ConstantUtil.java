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
     * 英文句号
     */
    public static final String ENGLISH_FULL_STOP = ".";

    /**
     * 英文逗号
     */
    public static final String ENGLISH_COMMA = ",";

    /**
     * 空串
     */
    public static final String EMPTY = StringUtils.EMPTY;

    /**
     * 单个空格符
     */
    public static final String SPACE = StringUtils.SPACE;

    /**
     * EXCEL中汉字的缩进间距，设置为4个空格
     */
    public static final String CHINESE_INDENT = CollectionUtil.join(Collections.nCopies(4, SPACE), EMPTY);

    /**
     * 英文分号
     */
    public static final String ENGLISH_SEMICOLON = ";";

    /**
     * 等号
     */
    public static final String EQUAL = "=";

    /**
     * java文件后缀
     */
    public static final String JAVA_FILE_SUFFIX = ".java";

    /**
     * xml文件后缀
     */
    public static final String XML_FILE_SUFFIX = ".xml";

    /**
     * Mo名称后缀
     */
    public static final String MO_NAME_SUFFIX = "Mo";

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
     * 列表变量后缀名
     */
    public static final String LIST = "List";

    /**
     * 通知展示ID
     */
    public static final String NOTIFICATION_GROUP_VIEW_ID = "xtools";

    /**
     * Tab4个空格
     */
    public static final String TAB = SPACE + SPACE + SPACE + SPACE;

    /**
     * 换行
     */
    public static final String NEXT_LINE = "\n";



}
