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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 原始MoClass对象的字段列表
     */
    private List<MoField> moFieldList;

    /**
     * 文件Map, value: Pair<生成文件名, 模板字符串>
     */
    private Map<KeyEnum, Pair<String, String>> fileMap = new HashMap<>(32, 0.75f);

    /**
     * 模板关键字匹配替换Map
     */
    private Map<String, String> keyWordMatchMap = new HashMap<>(32, 0.75f);

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
        fileMap.put(KeyEnum.MO, Pair.of(this.getMoName(), AutoCodeTemplate.TEMPLATE_MO));
        fileMap.put(KeyEnum.SWAGGER_MO, Pair.of(this.getMoName().concat(ConstantUtil.SWAGGER_MO_SUFFIX), AutoCodeTemplate.TEMPLATE_SWAGGER_MO));
        fileMap.put(KeyEnum.MO_MAPPER, Pair.of(this.getMoName().concat(ConstantUtil.MO_MAPPER_NAME_SUFFIX), AutoCodeTemplate.TEMPLATE_MAPPER));
        fileMap.put(KeyEnum.MO_SERVICE_INTERFACE, Pair.of(this.getMoName().concat(ConstantUtil.MO_SERVICE_INTERFACE_NAME_SUFFIX), AutoCodeTemplate.TEMPLATE_SERVICE_INTERFACE));
        fileMap.put(KeyEnum.MO_SERVICE_IMPL, Pair.of(this.getMoName().concat(ConstantUtil.MO_SERVICE_IMPL_NAME_SUFFIX), AutoCodeTemplate.TEMPLATE_SERVICE_IMPL));
        fileMap.put(KeyEnum.MO_CONTROLLER, Pair.of(this.getMoName().concat(ConstantUtil.MO_CONTROLLER_NAME_SUFFIX), AutoCodeTemplate.TEMPLATE_CONTROLLER));
        fileMap.put(KeyEnum.MO_MAPPER_XML, Pair.of(this.getMoName().concat(ConstantUtil.MO_MAPPER_NAME_SUFFIX).concat(ConstantUtil.XML_FILE_SUFFIX), AutoCodeTemplate.TEMPLATE_MAPPER_XML));
        fileMap.put(KeyEnum.MO_SQL, Pair.of(this.getMoName().concat(ConstantUtil.SQL_FILE_SUFFIX), AutoCodeTemplate.TEMPLATE_MAPPER_XML));
        return this;
    }

    /**
     * 模板关键字Map
     *
     * @return
     */
    private D8gerAutoCoding initKeyWordMap() {
        keyWordMatchMap.put(TemplateKeyWordEnum.MO_NAME_KEY.getName(), this.getMoName());
        keyWordMatchMap.put(TemplateKeyWordEnum.UNCAPITALLIZE_MO_NAME_KEY.getName(), StringUtils.uncapitalize(this.getMoName()));
        keyWordMatchMap.put(TemplateKeyWordEnum.D8_AUTHOR_KEY.getName(), "caofanCPU");
        keyWordMatchMap.put(TemplateKeyWordEnum.D8_MO_FIELD_KEY.getName(), CollectionUtil.join(CollectionUtil.transToList(moFieldList, MoField::toString), ConstantUtil.DOUBLE_NEXT_LINE));
        keyWordMatchMap.put(TemplateKeyWordEnum.D8_SWAGGER_MO_FIELD_KEY.getName(), CollectionUtil.join(CollectionUtil.transToList(moFieldList, MoField::toSwaggerString), ConstantUtil.DOUBLE_NEXT_LINE));
        return this;
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
     * Mo名称
     *
     * @return
     */
    public String getMoName() {
        return originMoJavaFile.getClasses()[0].getName();
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
