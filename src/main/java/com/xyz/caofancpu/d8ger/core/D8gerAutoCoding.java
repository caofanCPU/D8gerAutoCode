package com.xyz.caofancpu.d8ger.core;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiJavaFile;
import com.xyz.caofancpu.d8ger.util.CollectionUtil;
import com.xyz.caofancpu.d8ger.util.ConstantUtil;
import com.xyz.caofancpu.d8ger.util.PropertiesUtil;
import com.xyz.caofancpu.d8ger.util.VerbalExpressionUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * 自动生成代码核心类
 *
 * @author caofanCPU
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class D8gerAutoCoding {
    /**
     * 当前文件所在工程
     */
    private Project currentProject;

    /**
     * 当前文件所在模块
     */
    private Module currentModule;

    /**
     * resource资源根目录文件
     */
    private VirtualFile rootResource;

    /**
     * 自动代码生成目录
     */
    private PsiDirectory d8AutoCodeDir;

    /**
     * 原始MO文件对象
     */
    private PsiJavaFile originMoJavaFile;

    /**
     * 原始MoClass对象, 默认只取第一个
     */
    private PsiClass originMoPsiClass;

    /**
     * 原始原始MoClass名称
     */
    private String originMoName;

    /**
     * 原始MoClass对象的字段列表
     */
    private List<MoField> moFieldList;

    /**
     * 文件Map, value: Pair<生成文件名, 模板字符串>
     */
    private Map<KeyEnum, Pair<String, StringBuilder>> fileMap = new HashMap<>(32, 0.75f);

    /**
     * 模板关键字匹配替换Map
     */
    private Map<String, StringBuilder> keyWordMatchMap = new HashMap<>(32, 0.75f);

    /**
     * 对外暴露的构造方法, 注意方法执行顺序
     *
     * @param currentProject
     * @param currentModule
     * @param rootResource
     * @param moJavaFile
     * @return
     */
    public static D8gerAutoCoding build(@NonNull Project currentProject, @NonNull Module currentModule, @NonNull VirtualFile rootResource, @NonNull PsiJavaFile moJavaFile) {
        return new D8gerAutoCoding()
                // 设置工程
                .setCurrentProject(currentProject)
                // 设置模块
                .setCurrentModule(currentModule)
                // 装配resource资源根目录文件
                .setRootResource(rootResource)
                // 装配当前Java文件所在目录
                .setD8AutoCodeDir(moJavaFile.getContainingDirectory())
                // 设置Mo类型
                .setOriginMoJavaFile(moJavaFile)
                // 设置MoClass类型
                .configOriginPsiClass()
                // 设置字段列表
                .initMoFieldList()
                // 设置文件Map
                .initFileMap()
                // 设置关键字Map
                .initKeyWordMap();
    }

    /**
     * 文件名Map
     *
     * @return
     */
    private D8gerAutoCoding initFileMap() {
        fileMap.put(KeyEnum.MO, Pair.of(this.getMoName().concat(ConstantUtil.MO_SUFFIX).concat(ConstantUtil.JAVA_FILE_SUFFIX), AutoCodeTemplate.TEMPLATE_MO));
        fileMap.put(KeyEnum.SWAGGER_MO, Pair.of(this.getMoName().concat(ConstantUtil.SWAGGER_MO_SUFFIX).concat(ConstantUtil.JAVA_FILE_SUFFIX), AutoCodeTemplate.TEMPLATE_SWAGGER_VO));
        fileMap.put(KeyEnum.MO_EXAMPLE, Pair.of(this.getMoName().concat(ConstantUtil.MO_EXAMPLE_NAME_SUFFIX).concat(ConstantUtil.JAVA_FILE_SUFFIX), AutoCodeTemplate.TEMPLATE_MO_EXAMPLE));
        fileMap.put(KeyEnum.MO_MAPPER, Pair.of(this.getMoName().concat(ConstantUtil.MO_MAPPER_NAME_SUFFIX).concat(ConstantUtil.JAVA_FILE_SUFFIX), AutoCodeTemplate.TEMPLATE_MAPPER));
        fileMap.put(KeyEnum.MO_SERVICE_INTERFACE, Pair.of(this.getMoName().concat(ConstantUtil.MO_SERVICE_INTERFACE_NAME_SUFFIX).concat(ConstantUtil.JAVA_FILE_SUFFIX), AutoCodeTemplate.TEMPLATE_SERVICE_INTERFACE));
        fileMap.put(KeyEnum.MO_SERVICE_IMPL, Pair.of(this.getMoName().concat(ConstantUtil.MO_SERVICE_IMPL_NAME_SUFFIX).concat(ConstantUtil.JAVA_FILE_SUFFIX), AutoCodeTemplate.TEMPLATE_SERVICE_IMPL));
        fileMap.put(KeyEnum.MO_CONTROLLER, Pair.of(this.getMoName().concat(ConstantUtil.MO_CONTROLLER_NAME_SUFFIX).concat(ConstantUtil.JAVA_FILE_SUFFIX), AutoCodeTemplate.TEMPLATE_CONTROLLER));
        fileMap.put(KeyEnum.MO_MAPPER_XML, Pair.of(this.getMoName().concat(ConstantUtil.MO_MAPPER_NAME_SUFFIX).concat(ConstantUtil.XML_FILE_SUFFIX), AutoCodeTemplate.TEMPLATE_MAPPER_XML));
        fileMap.put(KeyEnum.MO_SQL, Pair.of(this.getMoName().concat(ConstantUtil.SQL_FILE_SUFFIX), AutoCodeTemplate.TEMPLATE_MO_SQL));
        return this;
    }

    public Properties loadPropertiesFromRootResource() {
        return PropertiesUtil.loadPropertiesFromRootResource(rootResource.getPath() + File.separator + ConstantUtil.D8GER_CONFIG_FILE_NAME);
    }

    /**
     * 模板关键字Map
     *
     * @return
     */
    private D8gerAutoCoding initKeyWordMap() {
        keyWordMatchMap.put(TemplateKeyWordEnum.MO_NAME_KEY.getName(), new StringBuilder(this.getMoName()));
        keyWordMatchMap.put(TemplateKeyWordEnum.PACKAGE_NAME_KEY.getName(), new StringBuilder(this.getPackageName()));
        keyWordMatchMap.put(TemplateKeyWordEnum.UNCAPITALLIZE_MO_NAME_KEY.getName(), new StringBuilder(StringUtils.uncapitalize(this.getMoName())));
        Properties properties = loadPropertiesFromRootResource();
        keyWordMatchMap.put(TemplateKeyWordEnum.AUTHOR_KEY.getName(), StringUtils.isNotBlank(properties.getProperty(ConstantUtil.CONFIG_AUTHOR_KEY)) ? new StringBuilder(properties.getProperty(ConstantUtil.CONFIG_AUTHOR_KEY)) : new StringBuilder(ConstantUtil.DEFAULT_AUTHOR));
        String apiUrlPrefix;
        if (StringUtils.isBlank(properties.getProperty(ConstantUtil.CONFIG_API_URL_PREFIX_KEY))) {
            apiUrlPrefix = ConstantUtil.DEFAULT_API_URL_PREFIX;
        } else {
            apiUrlPrefix = VerbalExpressionUtil.correctUrl(properties.getProperty(ConstantUtil.CONFIG_API_URL_PREFIX_KEY));
        }
        // 语言配置
        if (StringUtils.isNotBlank(properties.getProperty(ConstantUtil.CONFIG_LANGUAGE_KEY)) && StringUtils.capitalize(properties.getProperty(ConstantUtil.CONFIG_LANGUAGE_KEY)).equals(ConstantUtil.OPTIONAL_CONFIG_LANGUAGE)) {
            // 注释中文化
            AutoCodeTemplate.TEMPLATE_MO = AutoCodeTemplate.ZN_TEMPLATE_MO;
            AutoCodeTemplate.TEMPLATE_SWAGGER_VO = AutoCodeTemplate.ZN_TEMPLATE_SWAGGER_VO;
            AutoCodeTemplate.TEMPLATE_MO_SQL = AutoCodeTemplate.ZN_TEMPLATE_MO_SQL;
            AutoCodeTemplate.TEMPLATE_MAPPER = AutoCodeTemplate.ZN_TEMPLATE_MAPPER;
            AutoCodeTemplate.TEMPLATE_MO_EXAMPLE = AutoCodeTemplate.ZN_TEMPLATE_MO_EXAMPLE;
            AutoCodeTemplate.TEMPLATE_MAPPER_XML = AutoCodeTemplate.ZN_TEMPLATE_MAPPER_XML;
            AutoCodeTemplate.TEMPLATE_SERVICE_INTERFACE = AutoCodeTemplate.ZN_TEMPLATE_SERVICE_INTERFACE;
            AutoCodeTemplate.TEMPLATE_SERVICE_IMPL = AutoCodeTemplate.ZN_TEMPLATE_SERVICE_IMPL;
            AutoCodeTemplate.TEMPLATE_CONTROLLER = AutoCodeTemplate.ZN_TEMPLATE_CONTROLLER;
        }

        keyWordMatchMap.put(TemplateKeyWordEnum.API_URL_PREFIX_KEY.getName(), new StringBuilder(apiUrlPrefix));
        keyWordMatchMap.put(TemplateKeyWordEnum.MO_FIELD_KEY.getName(), new StringBuilder(CollectionUtil.join(CollectionUtil.transToList(moFieldList, MoField::toString), ConstantUtil.DOUBLE_NEXT_LINE)));
        keyWordMatchMap.put(TemplateKeyWordEnum.SWAGGER_MO_FIELD_KEY.getName(), new StringBuilder(CollectionUtil.join(CollectionUtil.transToList(moFieldList, MoField::toSwaggerString), ConstantUtil.DOUBLE_NEXT_LINE)).append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE).append(wrapSwaggerPage()));
        keyWordMatchMap.put(TemplateKeyWordEnum.MO_EXAMPLE_KEY.getName(), new StringBuilder(CollectionUtil.join(CollectionUtil.transToList(moFieldList, MoField::toMoExampleDefinitionMethodString), ConstantUtil.EMPTY)));
        keyWordMatchMap.put(TemplateKeyWordEnum.SQL_MO_TABLE_KEY.getName(), new StringBuilder(VerbalExpressionUtil.sqlUnderLineName(this.getMoName())));
        keyWordMatchMap.put(TemplateKeyWordEnum.SQL_MO_COLUMN_KEY.getName(), new StringBuilder(CollectionUtil.join(CollectionUtil.transToList(moFieldList, MoField::toSqlColumnDefinitionString), ConstantUtil.NEXT_LINE)));
        if (Objects.isNull(CollectionUtil.findFirst(moFieldList, item -> item.getName().equals(ConstantUtil.SQL_ID)))) {
            keyWordMatchMap.put(TemplateKeyWordEnum.SQL_MO_ID_KEY.getName(), new StringBuilder(ConstantUtil.SQL_ID_DEFAULT_DEFINITION));
        } else {
            keyWordMatchMap.put(TemplateKeyWordEnum.SQL_MO_ID_KEY.getName(), new StringBuilder());
        }
        if (Objects.isNull(CollectionUtil.findFirst(moFieldList, item -> item.getName().equals(ConstantUtil.SQL_CREATE_TIME)))) {
            keyWordMatchMap.put(TemplateKeyWordEnum.SQL_MO_CREATE_TIME_KEY.getName(), new StringBuilder(ConstantUtil.SQL_CREATE_TIME_DEFAULT_DEFINITION));
        } else {
            keyWordMatchMap.put(TemplateKeyWordEnum.SQL_MO_CREATE_TIME_KEY.getName(), new StringBuilder());
        }
        if (Objects.isNull(CollectionUtil.findFirst(moFieldList, item -> item.getName().equals(ConstantUtil.SQL_UPDATE_TIME)))) {
            keyWordMatchMap.put(TemplateKeyWordEnum.SQL_MO_UPDATE_TIME_KEY.getName(), new StringBuilder(ConstantUtil.SQL_UPDATE_TIME_DEFAULT_DEFINITION));
        } else {
            keyWordMatchMap.put(TemplateKeyWordEnum.SQL_MO_UPDATE_TIME_KEY.getName(), new StringBuilder());
        }
        keyWordMatchMap.put(TemplateKeyWordEnum.XML_BASE_COLUMN_LIST_KEY.getName(), this.getXMLBaseColumnList());
        keyWordMatchMap.put(TemplateKeyWordEnum.XML_SELECT_BASE_COLUMN_LIST_KEY.getName(), this.getXMLSelectBaseColumnList());
        keyWordMatchMap.put(TemplateKeyWordEnum.XML_BATCH_UPDATE_NONNULL_FIELD_BY_ID_KEY.getName(), this.getXMLBatchUpdateNonNullFieldByID());
        keyWordMatchMap.put(TemplateKeyWordEnum.XML_UPDATE_NONNULL_FIELD_BY_EXAMPLE_KEY.getName(), this.getXMLUpdateNonNullFieldByExample());
        keyWordMatchMap.put(TemplateKeyWordEnum.XML_INSERT_COLUMN_LIST_KEY.getName(), this.getXMLInsertField());
        keyWordMatchMap.put(TemplateKeyWordEnum.XML_BATCH_INSERT_COLUMN_LIST_KEY.getName(), this.getXMLBatchInsertField());
        keyWordMatchMap.put(TemplateKeyWordEnum.XML_UPDATE_NONNULL_FIELD_BY_ID_KEY.getName(), this.getXMLUpdateNonNullFieldByID());
        keyWordMatchMap.put(TemplateKeyWordEnum.XML_MO_LIST_QUERY_KEY.getName(), this.getXMLMoListQuery());
        return this;
    }

    /**
     * 增强SwaggerMo, 增加分页请求字段
     *
     * @return
     */
    public String wrapSwaggerPage() {
        return ConstantUtil.TAB + "@ApiModelProperty(value = \"" + "pageNum" + "\", required = false, example = \"1\", position = " + moFieldList.size() + ")" + ConstantUtil.NEXT_LINE
                + ConstantUtil.TAB + ConstantUtil.DEFAULT_ACCESS_MODIFIER + ConstantUtil.SPACE + SupportFieldTypeEnum.INTEGER.getShortName() + ConstantUtil.SPACE + ConstantUtil.PAGE_NUM_NAME + ConstantUtil.ENGLISH_SEMICOLON + ConstantUtil.NEXT_LINE + ConstantUtil.NEXT_LINE
                + ConstantUtil.TAB + "@ApiModelProperty(value = \"" + "pageSize" + "\", required = false, example = \"10\", position = " + (moFieldList.size() + 1) + ")" + ConstantUtil.NEXT_LINE
                + ConstantUtil.TAB + ConstantUtil.DEFAULT_ACCESS_MODIFIER + ConstantUtil.SPACE + SupportFieldTypeEnum.INTEGER.getShortName() + ConstantUtil.SPACE + ConstantUtil.PAGE_SIZE_NAME + ConstantUtil.ENGLISH_SEMICOLON;
    }

    /**
     * 为提升性能, 每个文件只选择内部Key, 以期减少字符串替换次数
     *
     * @param key
     * @return
     */
    public Map<String, StringBuilder> loadEnhanceKeyWordMap(KeyEnum key) {
        Map<String, StringBuilder> resultMap = new HashMap<>(32, 0.65f);
        if (KeyEnum.FORMAT_STYLE == key) {
            return resultMap;
        }
        List<TemplateKeyWordEnum> keyWordEnumList = new ArrayList<>(TemplateKeyWordEnum.values().length);
        switch (key) {
            case MO:
                keyWordEnumList.add(TemplateKeyWordEnum.PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.AUTHOR_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_FIELD_KEY);
                break;
            case SWAGGER_MO:
                keyWordEnumList.add(TemplateKeyWordEnum.PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.AUTHOR_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.SWAGGER_MO_FIELD_KEY);
                break;
            case MO_EXAMPLE:
                keyWordEnumList.add(TemplateKeyWordEnum.PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.AUTHOR_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_EXAMPLE_KEY);
                break;
            case MO_MAPPER_XML:
                keyWordEnumList.add(TemplateKeyWordEnum.PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.XML_SELECT_BASE_COLUMN_LIST_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.SQL_MO_TABLE_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.XML_BATCH_UPDATE_NONNULL_FIELD_BY_ID_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.XML_UPDATE_NONNULL_FIELD_BY_EXAMPLE_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.XML_UPDATE_NONNULL_FIELD_BY_ID_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.XML_BASE_COLUMN_LIST_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.XML_BATCH_INSERT_COLUMN_LIST_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.XML_MO_LIST_QUERY_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.XML_INSERT_COLUMN_LIST_KEY);
                break;
            case MO_SQL:
                keyWordEnumList.add(TemplateKeyWordEnum.AUTHOR_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.SQL_MO_TABLE_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.SQL_MO_ID_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.SQL_MO_COLUMN_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.SQL_MO_CREATE_TIME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.SQL_MO_UPDATE_TIME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_NAME_KEY);
                break;
            case MO_MAPPER:
            case MO_SERVICE_INTERFACE:
            case MO_SERVICE_IMPL:
                keyWordEnumList.add(TemplateKeyWordEnum.PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.AUTHOR_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.UNCAPITALLIZE_MO_NAME_KEY);
                break;
            case MO_CONTROLLER:
                keyWordEnumList.add(TemplateKeyWordEnum.PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.AUTHOR_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.UNCAPITALLIZE_MO_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.API_URL_PREFIX_KEY);
                break;
        }
        Map<String, StringBuilder> keyWordMatchMap = getKeyWordMatchMap();
        keyWordEnumList.forEach(templateKey -> resultMap.put(templateKey.getName(), keyWordMatchMap.get(templateKey.getName())));
        return resultMap;
    }

    /**
     * 封装Mo字段对象
     *
     * @return
     */
    private D8gerAutoCoding initMoFieldList() {
        PsiField[] selfOwnedFields = this.originMoPsiClass.getFields();
        this.setMoFieldList(CollectionUtil.transToList(Arrays.asList(selfOwnedFields), MoField::new));
        // 设置字段顺序
        moFieldList.forEach(item -> item.setIndex(moFieldList.indexOf(item)));
        return this;
    }

    /**
     * 获取枚举字段类型
     *
     * @return
     */
    public List<String> getEnumTypeClassName() {
        return CollectionUtil.filterAndTransList(moFieldList, item -> SupportFieldTypeEnum.ENUM.getOriginName().equals(item.getFieldOriginTypeName()), MoField::getFieldTypeShortName);
    }

    /**
     * Mo名称
     *
     * @return
     */
    public String getMoName() {
        String originMoName = Objects.nonNull(this.getOriginMoName()) ? this.getOriginMoName() : VerbalExpressionUtil.cropMoSuffix(originMoJavaFile.getClasses()[0].getName());
        this.setOriginMoName(originMoName);
        return originMoName;
    }

    /**
     * package名
     *
     * @return
     */
    public String getPackageName() {
        return originMoJavaFile.getPackageName() + ConstantUtil.ENGLISH_STOP + ConstantUtil.GENERATE_DIR;
    }

    /**
     * SQL全列字段
     * example:
     * `id`,
     * `name`,
     * `hello_d8ger`
     *
     * @return
     */
    private StringBuilder getXMLBaseColumnList() {
        return new StringBuilder(CollectionUtil.join(CollectionUtil.transToList(moFieldList,
                item -> ConstantUtil.TRIPLE_TAB + "`" + VerbalExpressionUtil.sqlUnderLineName(item.getName()) + "`"
        ), ConstantUtil.ENGLISH_COMMA + ConstantUtil.NEXT_LINE));
    }

    /**
     * SQL全列字段
     * example:
     * `id` AS id,
     * `name` AS name,
     * `hello_d8ger` AS helloD8ger
     *
     * @return
     */
    private StringBuilder getXMLSelectBaseColumnList() {
        return new StringBuilder(CollectionUtil.join(CollectionUtil.transToList(moFieldList,
                item -> ConstantUtil.TRIPLE_TAB + "`" + VerbalExpressionUtil.sqlUnderLineName(item.getName()) + "` AS " + item.getName()
        ), ConstantUtil.ENGLISH_COMMA + ConstantUtil.NEXT_LINE));
    }

    /**
     * SQL根据主键更新非null字段
     *
     * @return
     */
    private StringBuilder getXMLBatchUpdateNonNullFieldByID() {
        return new StringBuilder(CollectionUtil.join(CollectionUtil.removeAndTransList(moFieldList,
                item -> item.getName().equals(ConstantUtil.SQL_ID),
                item -> ConstantUtil.QUATERNARY_TAB + "<if test=\"item." + item.getName() + ConstantUtil.SPACE + "!= null\">" + ConstantUtil.NEXT_LINE
                        + ConstantUtil.PENTA_TAB + "`" + VerbalExpressionUtil.sqlUnderLineName(item.getName()) + "`" + ConstantUtil.SPACE + "=" + ConstantUtil.SPACE + "#{item." + item.getName() + "}," + ConstantUtil.NEXT_LINE
                        + ConstantUtil.QUATERNARY_TAB + "</if>"
        ), ConstantUtil.NEXT_LINE));
    }

    /**
     * SQL批量更新非null字段
     *
     * @return
     */
    private StringBuilder getXMLUpdateNonNullFieldByExample() {
        return new StringBuilder(CollectionUtil.join(CollectionUtil.transToList(moFieldList,
                item -> ConstantUtil.TRIPLE_TAB + "<if test=\"record." + item.getName() + ConstantUtil.SPACE + "!= null\">" + ConstantUtil.NEXT_LINE
                        + ConstantUtil.QUATERNARY_TAB + "`" + VerbalExpressionUtil.sqlUnderLineName(item.getName()) + "`" + ConstantUtil.SPACE + "=" + ConstantUtil.SPACE + "#{record." + item.getName() + "}," + ConstantUtil.NEXT_LINE
                        + ConstantUtil.TRIPLE_TAB + "</if>"
        ), ConstantUtil.NEXT_LINE));
    }

    /**
     * SQL插入单条记录
     *
     * @return
     */
    private StringBuilder getXMLInsertField() {
        return new StringBuilder(CollectionUtil.join(CollectionUtil.transToList(moFieldList, item -> ConstantUtil.TRIPLE_TAB + "#{" + item.getName() + "}"),
                ConstantUtil.ENGLISH_COMMA + ConstantUtil.NEXT_LINE)
        );
    }

    /**
     * SQL批量插入
     *
     * @return
     */
    private StringBuilder getXMLBatchInsertField() {
        return new StringBuilder(ConstantUtil.TRIPLE_TAB).append("(").append(ConstantUtil.NEXT_LINE)
                .append(CollectionUtil.join(CollectionUtil.transToList(moFieldList, item -> ConstantUtil.QUATERNARY_TAB + "#{item." + item.getName() + "}"), ConstantUtil.ENGLISH_COMMA + ConstantUtil.NEXT_LINE))
                .append(ConstantUtil.NEXT_LINE).append(ConstantUtil.TRIPLE_TAB).append(")");
    }

    /**
     * SQL根据ID更新非null字段
     *
     * @return
     */
    private StringBuilder getXMLUpdateNonNullFieldByID() {
        return new StringBuilder(CollectionUtil.join(CollectionUtil.removeAndTransList(moFieldList,
                item -> item.getName().equals(ConstantUtil.SQL_ID),
                item -> ConstantUtil.TRIPLE_TAB + "<if test=\"" + item.getName() + ConstantUtil.SPACE + "!= null\">" + ConstantUtil.NEXT_LINE
                        + ConstantUtil.QUATERNARY_TAB + "`" + VerbalExpressionUtil.sqlUnderLineName(item.getName()) + "`" + ConstantUtil.SPACE + "=" + ConstantUtil.SPACE + "#{" + item.getName() + "}," + ConstantUtil.NEXT_LINE
                        + ConstantUtil.TRIPLE_TAB + "</if>"
        ), ConstantUtil.NEXT_LINE));
    }

    /**
     * Mo对象查询
     *
     * @return
     */
    private StringBuilder getXMLMoListQuery() {
        return new StringBuilder(CollectionUtil.join(CollectionUtil.transToList(moFieldList,
                item -> {
                    String tmp;
                    if (SupportFieldTypeEnum.STRING.getShortName().equals(item.getFieldTypeShortName())) {
                        tmp = "LIKE " + "CONCAT(#{" + item.getName() + "}, '%')";
                    } else {
                        tmp = "=" + ConstantUtil.SPACE + "#{" + item.getName() + "}";
                    }
                    return ConstantUtil.TRIPLE_TAB + "<if test=\"" + item.getName() + ConstantUtil.SPACE + "!= null\">" + ConstantUtil.NEXT_LINE
                            + ConstantUtil.QUATERNARY_TAB + "AND `" + VerbalExpressionUtil.sqlUnderLineName(item.getName()) + "`" + ConstantUtil.SPACE + tmp + ConstantUtil.NEXT_LINE
                            + ConstantUtil.TRIPLE_TAB + "</if>";
                }
        ), ConstantUtil.NEXT_LINE));
    }

    /**
     * 配置原始MoClass
     *
     * @return
     */
    public D8gerAutoCoding configOriginPsiClass() {
        return this.setOriginMoPsiClass(originMoJavaFile.getClasses()[0]);
    }

}
