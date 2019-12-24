package com.xyz.caofancpu.d8ger.core;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.xyz.caofancpu.d8ger.util.ConstantUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * 自动生成代码核心类
 *
 * @author caofanCPU
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
     * 自动代码生成目录
     */
    private PsiDirectory d8AutoCodeDir;
    /**
     * 原始MO文件对象
     */
    private PsiJavaFile originMoJavaFile;
    /**
     * MO文件对象
     */
    private PsiJavaFile moJavaFile;
    /**
     * Mapper文件对象
     */
    private PsiJavaFile moMapperJavaFile;
    /**
     * Mapper.xml文件对象
     */
    private PsiFile moMapperXMLFile;
    /**
     * ServiceInterface文件对象
     */
    private PsiJavaFile moServiceInterfaceJavaFile;
    /**
     * ServiceImpl文件对象
     */
    private PsiJavaFile moServiceImplJavaFile;
    /**
     * Controller文件对象
     */
    private PsiJavaFile moControllerJavaFile;

    /**
     * 文件名称nameKeyMap
     */
    private Map<NameKeyEnum, String> nameKeyMap = new HashMap<>(16, 0.75f);

    /**
     * Java文件名与文件后缀名pairMatchMap
     */
    private Map<NameKeyEnum, NameKeyEnum> javaPairMatchMap = new HashMap<>(8, 0.75f);

    /**
     * Mapper文件名与SQL-XML
     */
    private Map<NameKeyEnum, NameKeyEnum> xmlPairMatchMap = new HashMap<>(2, 0.5f);

    public static D8gerAutoCoding build(@NonNull Project currentProject, @NonNull Module currentModule, @NonNull PsiDirectory d8gerAutoCodeDir, @NonNull PsiJavaFile moJavaFile) {
        return new D8gerAutoCoding()
                .setCurrentProject(currentProject)
                .setCurrentModule(currentModule)
                // 创建自动代码目录
                .setD8AutoCodeDir(d8gerAutoCodeDir)
                // 设置Mo类型
                .setOriginMoJavaFile(moJavaFile)
                // 设置名称
                .buildMap();
    }

    public D8gerAutoCoding buildMap() {
        nameKeyMap.put(NameKeyEnum.MO_NAME, this.getMoName());
        nameKeyMap.put(NameKeyEnum.MO_FILE_NAME, wrapJavaFileSuffix(nameKeyMap.get(NameKeyEnum.MO_NAME)));
        nameKeyMap.put(NameKeyEnum.MO_MAPPER_NAME, this.getMoName().concat(ConstantUtil.MO_MAPPER_NAME_SUFFIX));
        nameKeyMap.put(NameKeyEnum.MO_MAPPER_FILE_NAME, wrapJavaFileSuffix(nameKeyMap.get(NameKeyEnum.MO_MAPPER_NAME)));
        nameKeyMap.put(NameKeyEnum.MO_MAPPER_XML_FILE_NAME, nameKeyMap.get(NameKeyEnum.MO_MAPPER_NAME).concat(ConstantUtil.XML_FILE_SUFFIX));
        nameKeyMap.put(NameKeyEnum.MO_SERVICE_INTERFACE_NAME, this.getMoName().concat(ConstantUtil.MO_SERVICE_INTERFACE_NAME_SUFFIX));
        nameKeyMap.put(NameKeyEnum.MO_SERVICE_INTERFACE_FILE_NAME, wrapJavaFileSuffix(nameKeyMap.get(NameKeyEnum.MO_SERVICE_INTERFACE_NAME)));
        nameKeyMap.put(NameKeyEnum.MO_SERVICE_IMPL_NAME, this.getMoName().concat(ConstantUtil.MO_SERVICE_IMPL_NAME_SUFFIX));
        nameKeyMap.put(NameKeyEnum.MO_SERVICE_IMPL_FILE_NAME, wrapJavaFileSuffix(nameKeyMap.get(NameKeyEnum.MO_SERVICE_IMPL_NAME)));
        nameKeyMap.put(NameKeyEnum.MO_CONTROLLER_NAME, this.getMoName().concat(ConstantUtil.MO_CONTROLLER_NAME_SUFFIX));
        nameKeyMap.put(NameKeyEnum.MO_CONTROLLER_FILE_NAME, wrapJavaFileSuffix(nameKeyMap.get(NameKeyEnum.MO_CONTROLLER_NAME)));

        javaPairMatchMap.put(NameKeyEnum.MO_NAME, NameKeyEnum.MO_FILE_NAME);
        javaPairMatchMap.put(NameKeyEnum.MO_MAPPER_NAME, NameKeyEnum.MO_MAPPER_FILE_NAME);
        javaPairMatchMap.put(NameKeyEnum.MO_SERVICE_INTERFACE_NAME, NameKeyEnum.MO_SERVICE_INTERFACE_FILE_NAME);
        javaPairMatchMap.put(NameKeyEnum.MO_SERVICE_IMPL_NAME, NameKeyEnum.MO_SERVICE_IMPL_FILE_NAME);
        javaPairMatchMap.put(NameKeyEnum.MO_CONTROLLER_NAME, NameKeyEnum.MO_CONTROLLER_FILE_NAME);

        xmlPairMatchMap.put(NameKeyEnum.MO_MAPPER_NAME, NameKeyEnum.MO_MAPPER_XML_FILE_NAME);
        return this;
    }

    private String wrapJavaFileSuffix(@NonNull String fileName) {
        return fileName.concat(ConstantUtil.JAVA_FILE_SUFFIX);
    }

    /**
     * Mo名称
     *
     * @return
     */
    public String getMoName() {
        return originMoJavaFile.getClasses()[0].getName();
    }

    public enum NameKeyEnum {
        MO_NAME(1),
        MO_FILE_NAME(2),
        MO_MAPPER_NAME(3),
        MO_MAPPER_FILE_NAME(4),
        MO_MAPPER_XML_FILE_NAME(5),
        MO_SERVICE_INTERFACE_NAME(6),
        MO_SERVICE_INTERFACE_FILE_NAME(7),
        MO_SERVICE_IMPL_NAME(8),
        MO_SERVICE_IMPL_FILE_NAME(9),
        MO_CONTROLLER_NAME(10),
        MO_CONTROLLER_FILE_NAME(11),

        ;

        private Integer value;

        NameKeyEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return this.value;
        }
    }

}
