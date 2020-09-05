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
import com.xyz.caofancpu.d8ger.util.IdeaPlatformFileTreeUtil;
import com.xyz.caofancpu.d8ger.util.PropertiesUtil;
import com.xyz.caofancpu.d8ger.util.VerbalExpressionUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * Automatically generate code core classes
 *
 * @author caofanCPU
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class D8gerAutoCoding {
    /**
     * Project where the current file is located
     */
    private Project currentProject;

    /**
     * Module where the current file is located
     */
    private Module currentModule;

    /**
     * Resource root directory file
     */
    private VirtualFile rootResource;

    /**
     * Automatic code generation directory
     */
    private PsiDirectory d8AutoCodeDir;

    /**
     * Flag of using D8AutoCode directory or not
     */
    private boolean useDefaultDirectory;

    /**
     * Original model file object
     */
    private PsiJavaFile originMoJavaFile;

    /**
     * Original model class object, take only the first one by default
     */
    private PsiClass originMoPsiClass;

    /**
     * Original model class name
     */
    private String originMoName;

    /**
     * Field list of the original model class object
     */
    private List<MoField> moFieldList;

    /**
     * FileMap, value: Pair<Generate file name, Template string>
     */
    private Map<KeyEnum, Pair<String, StringBuilder>> fileMap = new HashMap<>(32, 0.75f);

    /**
     * Template keyword match replacement Map
     */
    private Map<String, StringBuilder> keyWordMatchMap = new HashMap<>(32, 0.75f);

    /**
     * Storage custom config directories that auto code files put into
     */
    private Map<KeyEnum, Pair<PsiDirectory, String>> customConfigAutoCodeDirMap = new HashMap<>(16, 0.75f);

    /**
     * Real need create file, just for marking
     */
    private List<KeyEnum> realNeedCreateFileKeyList = new ArrayList<>();

    /**
     * Construction methods exposed to the outside world,
     * pay attention to the method execution order
     *
     * @param currentProject
     * @param currentModule
     * @param rootResource
     * @param moJavaFile
     * @return
     */
    public static D8gerAutoCoding build(@NonNull Project currentProject, @NonNull Module currentModule, @NonNull VirtualFile rootResource, @NonNull PsiJavaFile moJavaFile) {
        return new D8gerAutoCoding()
                // Set the project
                .setCurrentProject(currentProject)
                // Set the module
                .setCurrentModule(currentModule)
                // config resource root directory file
                .setRootResource(rootResource)
                // config directory which current Java file is located
                .setD8AutoCodeDir(moJavaFile.getContainingDirectory())
                // Set model type of PsiJavaFile
                .setOriginMoJavaFile(moJavaFile)
                // Set model class type of PsiClass
                .configOriginPsiClass()
                // Set field list
                .initMoFieldList()
                // Config custom directory Map
                .initCustomConfigAutoCodeDirMap()
                // Config keyword Map
                .initKeyWordMap()
                // Config file Map
                .initFileMap();
    }

    /**
     * File name Map
     *
     * @return
     */
    private D8gerAutoCoding initFileMap() {
        fileMap.put(KeyEnum.MO, Pair.of(this.getMoName().concat(ConstantUtil.MO_SUFFIX).concat(ConstantUtil.JAVA_FILE_SUFFIX), AutoCodeTemplate.IS_EN_LOCALE ? AutoCodeTemplate.TEMPLATE_MO : AutoCodeTemplate.ZN_TEMPLATE_MO));
        fileMap.put(KeyEnum.SWAGGER_MO, Pair.of(this.getMoName().concat(ConstantUtil.SWAGGER_MO_SUFFIX).concat(ConstantUtil.JAVA_FILE_SUFFIX), AutoCodeTemplate.IS_EN_LOCALE ? AutoCodeTemplate.TEMPLATE_SWAGGER_VO : AutoCodeTemplate.ZN_TEMPLATE_SWAGGER_VO));
        fileMap.put(KeyEnum.MO_EXAMPLE, Pair.of(this.getMoName().concat(ConstantUtil.MO_EXAMPLE_NAME_SUFFIX).concat(ConstantUtil.JAVA_FILE_SUFFIX), AutoCodeTemplate.IS_EN_LOCALE ? AutoCodeTemplate.TEMPLATE_MO_EXAMPLE : AutoCodeTemplate.ZN_TEMPLATE_MO_EXAMPLE));
        fileMap.put(KeyEnum.MO_MAPPER, Pair.of(this.getMoName().concat(ConstantUtil.MO_MAPPER_NAME_SUFFIX).concat(ConstantUtil.JAVA_FILE_SUFFIX), AutoCodeTemplate.IS_EN_LOCALE ? AutoCodeTemplate.TEMPLATE_MAPPER : AutoCodeTemplate.ZN_TEMPLATE_MAPPER));
        fileMap.put(KeyEnum.MO_HANDLER, Pair.of(this.getMoName().concat(ConstantUtil.MO_HANDLER_SUFFIX).concat(ConstantUtil.JAVA_FILE_SUFFIX), AutoCodeTemplate.IS_EN_LOCALE ? AutoCodeTemplate.TEMPLATE_HANDLER : AutoCodeTemplate.ZN_TEMPLATE_HANDLER));
        fileMap.put(KeyEnum.MO_CONTROLLER, Pair.of(this.getMoName().concat(ConstantUtil.MO_CONTROLLER_NAME_SUFFIX).concat(ConstantUtil.JAVA_FILE_SUFFIX), AutoCodeTemplate.IS_EN_LOCALE ? AutoCodeTemplate.TEMPLATE_CONTROLLER : AutoCodeTemplate.ZN_TEMPLATE_CONTROLLER));
        fileMap.put(KeyEnum.MO_MAPPER_XML, Pair.of(this.getMoName().concat(ConstantUtil.MO_MAPPER_NAME_SUFFIX).concat(ConstantUtil.XML_FILE_SUFFIX), AutoCodeTemplate.IS_EN_LOCALE ? AutoCodeTemplate.TEMPLATE_MAPPER_XML : AutoCodeTemplate.ZN_TEMPLATE_MAPPER_XML));
        fileMap.put(KeyEnum.MO_SQL, Pair.of(this.getMoName().concat(ConstantUtil.SQL_FILE_SUFFIX), AutoCodeTemplate.IS_EN_LOCALE ? AutoCodeTemplate.TEMPLATE_MO_SQL : AutoCodeTemplate.ZN_TEMPLATE_MO_SQL));
        return this;
    }

    private List<KeyEnum> getAllFileKeyList() {
        List<KeyEnum> keyEnumList = new ArrayList<>();
        keyEnumList.add(KeyEnum.MO);
        keyEnumList.add(KeyEnum.SWAGGER_MO);
        keyEnumList.add(KeyEnum.MO_EXAMPLE);
        keyEnumList.add(KeyEnum.MO_MAPPER);
        keyEnumList.add(KeyEnum.MO_HANDLER);
        keyEnumList.add(KeyEnum.MO_CONTROLLER);
        keyEnumList.add(KeyEnum.MO_MAPPER_XML);
        keyEnumList.add(KeyEnum.MO_SQL);
        return keyEnumList;
    }

    /**
     * Init custom config directories that auto code files put into
     *
     * @return
     */
    private D8gerAutoCoding initCustomConfigAutoCodeDirMap() {
        Properties properties = loadPropertiesFromRootResource();
        List<KeyEnum> keyEnumList = getAllFileKeyList();
        keyEnumList.forEach(keyEnum -> {
            if (PropertiesUtil.checkConfigTakeEffect(properties, keyEnum.getKey())) {
                realNeedCreateFileKeyList.add(keyEnum);
            }
            String directoryPath = PropertiesUtil.detectConfigDirectoryPath(properties, keyEnum.getKey());
            if (StringUtils.isNotBlank(directoryPath)) {
                PsiDirectory targetDir = IdeaPlatformFileTreeUtil.getOrCreateSubDirByPath(currentProject, directoryPath);
                if (Objects.nonNull(targetDir)) {
                    String targetPackage = VerbalExpressionUtil.convertPathToPackage(directoryPath);
                    customConfigAutoCodeDirMap.put(keyEnum, Pair.of(targetDir, targetPackage));
                }
            }
        });
        return this;
    }

    /**
     * Load custom config properties
     *
     * @return
     */
    public Properties loadPropertiesFromRootResource() {
        return PropertiesUtil.loadPropertiesFromRootResource(this.currentProject);
    }

    /**
     * Template keywords Map
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

        // handle mapper's annotation
        boolean isMapper = PropertiesUtil.checkConfigTakeEffect(properties, KeyEnum.MO_MAPPER_ANNOTATION.getKey());
        Pair<String, String> mapperPair = isMapper ? ConstantUtil.BOOT_MAPPER : ConstantUtil.MVC_MAPPER;
        keyWordMatchMap.put(TemplateKeyWordEnum.MAPPER_ANNOTATION_PACKAGE_KEY.getName(), new StringBuilder(mapperPair.getLeft()));
        keyWordMatchMap.put(TemplateKeyWordEnum.MAPPER_ANNOTATION_KEY.getName(), new StringBuilder(mapperPair.getRight()));

        keyWordMatchMap.put(TemplateKeyWordEnum.API_URL_PREFIX_KEY.getName(), new StringBuilder(apiUrlPrefix));
        keyWordMatchMap.put(TemplateKeyWordEnum.MO_FIELD_KEY.getName(), new StringBuilder(CollectionUtil.join(CollectionUtil.transToList(moFieldList, MoField::toString), ConstantUtil.DOUBLE_NEXT_LINE)));
        keyWordMatchMap.put(TemplateKeyWordEnum.SWAGGER_MO_FIELD_KEY.getName(), new StringBuilder(CollectionUtil.join(CollectionUtil.transToList(moFieldList, MoField::toSwaggerString), ConstantUtil.DOUBLE_NEXT_LINE)).append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE).append(wrapSwaggerPage()));
        keyWordMatchMap.put(TemplateKeyWordEnum.MO_EXAMPLE_KEY.getName(), new StringBuilder(CollectionUtil.join(CollectionUtil.transToList(moFieldList, MoField::toMoExampleDefinitionMethodString), ConstantUtil.EMPTY)));
        keyWordMatchMap.put(TemplateKeyWordEnum.SQL_MO_TABLE_KEY.getName(), new StringBuilder(VerbalExpressionUtil.sqlUnderLineName(this.getMoName())));
        keyWordMatchMap.put(TemplateKeyWordEnum.SQL_MO_COLUMN_KEY.getName(), new StringBuilder(CollectionUtil.join(CollectionUtil.transToList(moFieldList, MoField::toSqlColumnDefinitionString), ConstantUtil.ENGLISH_COMMA + ConstantUtil.NEXT_LINE)));

        keyWordMatchMap.put(TemplateKeyWordEnum.XML_BASE_COLUMN_LIST_KEY.getName(), this.getXMLBaseColumnList());
        keyWordMatchMap.put(TemplateKeyWordEnum.XML_SELECT_BASE_COLUMN_LIST_KEY.getName(), this.getXMLSelectBaseColumnList());
        keyWordMatchMap.put(TemplateKeyWordEnum.XML_BATCH_UPDATE_NONNULL_FIELD_BY_ID_KEY.getName(), this.getXMLBatchUpdateNonNullFieldByID());
        keyWordMatchMap.put(TemplateKeyWordEnum.XML_INSERT_SELECTIVE_COLUMN_LIST_KEY.getName(), this.getXMLInsertSelectiveColumnList());
        keyWordMatchMap.put(TemplateKeyWordEnum.XML_INSERT_SELECTIVE_FIELD_LIST_KEY.getName(), this.getXMLInsertSelectiveFieldList());
        keyWordMatchMap.put(TemplateKeyWordEnum.XML_UPDATE_NONNULL_FIELD_BY_EXAMPLE_KEY.getName(), this.getXMLUpdateNonNullFieldByExample());
        keyWordMatchMap.put(TemplateKeyWordEnum.XML_INSERT_COLUMN_LIST_KEY.getName(), this.getXMLInsertField());
        keyWordMatchMap.put(TemplateKeyWordEnum.XML_BATCH_INSERT_COLUMN_LIST_KEY.getName(), this.getXMLBatchInsertField());
        keyWordMatchMap.put(TemplateKeyWordEnum.XML_UPDATE_NONNULL_FIELD_BY_ID_KEY.getName(), this.getXMLUpdateNonNullFieldByID());
        keyWordMatchMap.put(TemplateKeyWordEnum.XML_MO_LIST_QUERY_KEY.getName(), this.getXMLMoListQuery());
        return this;
    }

    /**
     * Enhance swagger model with paging request field
     *
     * @return
     */
    public String wrapSwaggerPage() {
        return ConstantUtil.TAB + "@ApiModelProperty(value = \"" + "pageNum" + "\", required = false, example = \"1\", position = " + (moFieldList.size() + 1) + ")" + ConstantUtil.NEXT_LINE
                + ConstantUtil.TAB + ConstantUtil.DEFAULT_ACCESS_MODIFIER + ConstantUtil.SPACE + SupportFieldTypeEnum.INTEGER.getShortName() + ConstantUtil.SPACE + ConstantUtil.PAGE_NUM_NAME + ConstantUtil.ENGLISH_SEMICOLON + ConstantUtil.NEXT_LINE + ConstantUtil.NEXT_LINE
                + ConstantUtil.TAB + "@ApiModelProperty(value = \"" + "pageSize" + "\", required = false, example = \"10\", position = " + (moFieldList.size() + 2) + ")" + ConstantUtil.NEXT_LINE
                + ConstantUtil.TAB + ConstantUtil.DEFAULT_ACCESS_MODIFIER + ConstantUtil.SPACE + SupportFieldTypeEnum.INTEGER.getShortName() + ConstantUtil.SPACE + ConstantUtil.PAGE_SIZE_NAME + ConstantUtil.ENGLISH_SEMICOLON;
    }

    /**
     * To improve performance, only the internal key is selected for each file to reduce the number of string replacements
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
                keyWordEnumList.add(TemplateKeyWordEnum.MO_PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.AUTHOR_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_FIELD_KEY);
                break;
            case SWAGGER_MO:
                keyWordEnumList.add(TemplateKeyWordEnum.SWAGGER_MO_PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.AUTHOR_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.SWAGGER_MO_FIELD_KEY);
                break;
            case MO_EXAMPLE:
                keyWordEnumList.add(TemplateKeyWordEnum.MO_EXAMPLE_PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.AUTHOR_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_EXAMPLE_KEY);
                break;
            case MO_MAPPER_XML:
                keyWordEnumList.add(TemplateKeyWordEnum.MAPPER_PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_EXAMPLE_PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_PACKAGE_NAME_KEY);
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
                keyWordEnumList.add(TemplateKeyWordEnum.XML_INSERT_SELECTIVE_COLUMN_LIST_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.XML_INSERT_SELECTIVE_FIELD_LIST_KEY);
                break;
            case MO_SQL:
                keyWordEnumList.add(TemplateKeyWordEnum.AUTHOR_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.SQL_MO_TABLE_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.SQL_MO_COLUMN_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_NAME_KEY);
                break;
            case MO_MAPPER:
                keyWordEnumList.add(TemplateKeyWordEnum.MAPPER_PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MAPPER_ANNOTATION_PACKAGE_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MAPPER_ANNOTATION_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_EXAMPLE_PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.AUTHOR_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.UNCAPITALLIZE_MO_NAME_KEY);
                break;
            case MO_HANDLER:
                keyWordEnumList.add(TemplateKeyWordEnum.HANDLER_PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MAPPER_PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.AUTHOR_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.UNCAPITALLIZE_MO_NAME_KEY);
                break;
            case MO_CONTROLLER:
                keyWordEnumList.add(TemplateKeyWordEnum.CONTROLLER_PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.HANDLER_PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.MO_PACKAGE_NAME_KEY);
                keyWordEnumList.add(TemplateKeyWordEnum.SWAGGER_MO_PACKAGE_NAME_KEY);
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
     * Encapsulating model field object
     *
     * @return
     */
    private D8gerAutoCoding initMoFieldList() {
        PsiField[] selfOwnedFields = this.originMoPsiClass.getFields();
        // extract original field list
        this.setMoFieldList(CollectionUtil.transToList(Arrays.asList(selfOwnedFields), MoField::new));
        // enhance by configuration
        Properties properties = loadPropertiesFromRootResource();
        // Language configuration
        if (ConstantUtil.OPTIONAL_CONFIG_LANGUAGE.equals(properties.getProperty(ConstantUtil.CONFIG_LANGUAGE_KEY))) {
            // Annotation Chinese Culture
            AutoCodeTemplate.IS_EN_LOCALE = Boolean.FALSE;
        } else {
            AutoCodeTemplate.IS_EN_LOCALE = Boolean.TRUE;
        }
        if (Objects.isNull(CollectionUtil.findFirst(moFieldList, item -> item.getName().equals(ConstantUtil.SQL_ID)))) {
            this.produceIdField();
        }
        // whether auto generate create_time and update_time column definition by custom config
        boolean sqlDetectTimeColumn = PropertiesUtil.checkConfigTakeEffect(properties, KeyEnum.SQL_DETECT_TIME_COLUMN.getKey());
        if (sqlDetectTimeColumn && Objects.isNull(CollectionUtil.findFirst(moFieldList, item -> item.getName().equals(ConstantUtil.SQL_CREATE_TIME)))) {
            this.produceCreateTimeField();
        }
        if (sqlDetectTimeColumn && Objects.isNull(CollectionUtil.findFirst(moFieldList, item -> item.getName().equals(ConstantUtil.SQL_UPDATE_TIME)))) {
            this.produceUpdateTimeField();
        }
        // Set field order
        moFieldList.forEach(item -> item.setIndex(moFieldList.indexOf(item)));
        return this;
    }

    /**
     * Get enum field type
     *
     * @return
     */
    public List<String> getEnumTypeClassName() {
        return CollectionUtil.filterAndTransList(moFieldList, item -> SupportFieldTypeEnum.ENUM.getOriginName().equals(item.getFieldOriginTypeName()), MoField::getFieldTypeShortName);
    }

    /**
     * Model name
     *
     * @return
     */
    public String getMoName() {
        String originMoName = Objects.nonNull(this.getOriginMoName()) ? this.getOriginMoName() : VerbalExpressionUtil.cropMoSuffix(originMoJavaFile.getClasses()[0].getName());
        this.setOriginMoName(originMoName);
        return originMoName;
    }

    /**
     * package name
     *
     * @return
     */
    public String getPackageName() {
        return originMoJavaFile.getPackageName() + ConstantUtil.ENGLISH_STOP + ConstantUtil.GENERATE_DIR;
    }

    /**
     * SQL-BaseColumnList, exclude primary key column `id` | `createTime` | `updateTime`
     * example:
     * `name`,
     * `hello_d8ger`
     *
     * @return
     */
    private StringBuilder getXMLBaseColumnList() {
        return new StringBuilder(CollectionUtil.join(CollectionUtil.removeAndTransList(moFieldList,
                item -> item.getName().equals(ConstantUtil.SQL_ID) || item.getName().equals(ConstantUtil.SQL_CREATE_TIME) || item.getName().equals(ConstantUtil.SQL_UPDATE_TIME),
                item -> ConstantUtil.TRIPLE_TAB + "`" + VerbalExpressionUtil.sqlUnderLineName(item.getName()) + "`"
        ), ConstantUtil.ENGLISH_COMMA + ConstantUtil.NEXT_LINE));
    }

    /**
     * SQL-SelectBaseColumnList(with alias name)
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
     * SQL-BatchUpdateNonNullFieldByID
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
     * SQL-NonNullColumnList
     *
     * @return
     */
    private StringBuilder getXMLInsertSelectiveColumnList() {
        return new StringBuilder(CollectionUtil.join(CollectionUtil.removeAndTransList(moFieldList,
                item -> item.getName().equals(ConstantUtil.SQL_ID),
                item -> ConstantUtil.TRIPLE_TAB + "<if test=\"" + item.getName() + ConstantUtil.SPACE + "!= null\">" + ConstantUtil.NEXT_LINE
                        + ConstantUtil.QUATERNARY_TAB + "`" + VerbalExpressionUtil.sqlUnderLineName(item.getName()) + "`," + ConstantUtil.NEXT_LINE
                        + ConstantUtil.TRIPLE_TAB + "</if>"
        ), ConstantUtil.NEXT_LINE));
    }

    /**
     * SQL-NonNullInsertField
     *
     * @return
     */
    private StringBuilder getXMLInsertSelectiveFieldList() {
        return new StringBuilder(CollectionUtil.join(CollectionUtil.removeAndTransList(moFieldList,
                item -> item.getName().equals(ConstantUtil.SQL_ID),
                item -> ConstantUtil.TRIPLE_TAB + "<if test=\"" + item.getName() + ConstantUtil.SPACE + "!= null\">" + ConstantUtil.NEXT_LINE
                        + ConstantUtil.QUATERNARY_TAB + "#{" + item.getName() + "}," + ConstantUtil.NEXT_LINE
                        + ConstantUtil.TRIPLE_TAB + "</if>"
        ), ConstantUtil.NEXT_LINE));
    }

    /**
     * SQL-UpdateNonNullFieldByExample, exclude primary key column `id`
     *
     * @return
     */
    private StringBuilder getXMLUpdateNonNullFieldByExample() {
        return new StringBuilder(CollectionUtil.join(CollectionUtil.removeAndTransList(moFieldList,
                item -> item.getName().equals(ConstantUtil.SQL_ID),
                item -> ConstantUtil.TRIPLE_TAB + "<if test=\"record." + item.getName() + ConstantUtil.SPACE + "!= null\">" + ConstantUtil.NEXT_LINE
                        + ConstantUtil.QUATERNARY_TAB + "`" + VerbalExpressionUtil.sqlUnderLineName(item.getName()) + "`" + ConstantUtil.SPACE + "=" + ConstantUtil.SPACE + "#{record." + item.getName() + "}," + ConstantUtil.NEXT_LINE
                        + ConstantUtil.TRIPLE_TAB + "</if>"
        ), ConstantUtil.NEXT_LINE));
    }

    /**
     * SQL-Insert, exclude primary key column `id` | `createTime` | `updateTime`
     *
     * @return
     */
    private StringBuilder getXMLInsertField() {
        return new StringBuilder(CollectionUtil.join(CollectionUtil.removeAndTransList(moFieldList,
                item -> item.getName().equals(ConstantUtil.SQL_ID) || item.getName().equals(ConstantUtil.SQL_CREATE_TIME) || item.getName().equals(ConstantUtil.SQL_UPDATE_TIME),
                item -> ConstantUtil.TRIPLE_TAB + "#{" + item.getName() + "}"
        ), ConstantUtil.ENGLISH_COMMA + ConstantUtil.NEXT_LINE));
    }

    /**
     * SQL-BatchInsertField, exclude primary key column `id` | `createTime` | `updateTime`
     *
     * @return
     */
    private StringBuilder getXMLBatchInsertField() {
        return new StringBuilder(ConstantUtil.TRIPLE_TAB).append("(").append(ConstantUtil.NEXT_LINE)
                .append(CollectionUtil.join(CollectionUtil.removeAndTransList(moFieldList,
                        item -> item.getName().equals(ConstantUtil.SQL_ID) || item.getName().equals(ConstantUtil.SQL_CREATE_TIME) || item.getName().equals(ConstantUtil.SQL_UPDATE_TIME),
                        item -> ConstantUtil.QUATERNARY_TAB + "#{item." + item.getName() + "}"
                ), ConstantUtil.ENGLISH_COMMA + ConstantUtil.NEXT_LINE))
                .append(ConstantUtil.NEXT_LINE).append(ConstantUtil.TRIPLE_TAB).append(")");
    }

    /**
     * SQL-UpdateNonNullFieldByID
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
     * SQL-QueryModelList
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
     * Config origin Model class
     *
     * @return
     */
    public D8gerAutoCoding configOriginPsiClass() {
        return this.setOriginMoPsiClass(originMoJavaFile.getClasses()[0]);
    }

    /**
     * For an optional, auto add 'id' field
     */
    private void produceIdField() {
        String comment = StringUtils.upperCase(ConstantUtil.SQL_ID);
        SupportFieldTypeEnum idFieldType = SupportFieldTypeEnum.LONG;
        MoField idField = new MoField()
                .setComment(comment)
                .setAccessModifier(ConstantUtil.DEFAULT_ACCESS_MODIFIER)
                .setFieldOriginTypeName(idFieldType.getOriginName())
                .setFieldTypeShortName(idFieldType.getShortName())
                .setFieldSqlTypeName(idFieldType.getSqlName())
                .setName(ConstantUtil.SQL_ID);
        this.moFieldList.add(0, idField);
    }

    /**
     * For an optional, auto add 'createTime' field
     */
    private void produceCreateTimeField() {
        String comment = AutoCodeTemplate.IS_EN_LOCALE ? ConstantUtil.SQL_CREATE_TIME : ConstantUtil.ZN_SQL_CREATE_TIME;
        SupportFieldTypeEnum idFieldType = SupportFieldTypeEnum.LOCAL_DATE_TIME;
        MoField idField = new MoField()
                .setComment(comment)
                .setAccessModifier(ConstantUtil.DEFAULT_ACCESS_MODIFIER)
                .setFieldOriginTypeName(idFieldType.getOriginName())
                .setFieldTypeShortName(idFieldType.getShortName())
                .setFieldSqlTypeName(idFieldType.getSqlName())
                .setName(ConstantUtil.SQL_CREATE_TIME);
        this.moFieldList.add(idField);
    }

    /**
     * For an optional, auto add 'updateTime' field
     */
    private void produceUpdateTimeField() {
        String comment = AutoCodeTemplate.IS_EN_LOCALE ? ConstantUtil.SQL_UPDATE_TIME : ConstantUtil.ZN_SQL_UPDATE_TIME;
        SupportFieldTypeEnum idFieldType = SupportFieldTypeEnum.LOCAL_DATE_TIME;
        MoField idField = new MoField()
                .setComment(comment)
                .setAccessModifier(ConstantUtil.DEFAULT_ACCESS_MODIFIER)
                .setFieldOriginTypeName(idFieldType.getOriginName())
                .setFieldTypeShortName(idFieldType.getShortName())
                .setFieldSqlTypeName(idFieldType.getSqlName())
                .setName(ConstantUtil.SQL_UPDATE_TIME);
        this.moFieldList.add(idField);
    }

}
