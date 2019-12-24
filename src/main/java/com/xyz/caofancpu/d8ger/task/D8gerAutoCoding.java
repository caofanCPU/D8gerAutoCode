package com.xyz.caofancpu.d8ger.task;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.xyz.caofancpu.d8ger.util.ConstantUtil;
import com.xyz.caofancpu.d8ger.util.IdeaPlatformFileTreeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

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


    public static D8gerAutoCoding build(@NonNull Project currentProject, @NonNull Module currentModule, @NonNull PsiDirectory currentDir, @NonNull PsiJavaFile moJavaFile) {
        PsiDirectory d8gerAutoCodeDir = IdeaPlatformFileTreeUtil.getOrCreateSubDirectory(currentDir, ConstantUtil.GENERATE_DIR);
        return new D8gerAutoCoding()
                .setCurrentProject(currentProject)
                .setCurrentModule(currentModule)
                // 创建自动代码目录
                .setD8AutoCodeDir(d8gerAutoCodeDir)
                // 设置Mo类型
                .setOriginMoJavaFile(moJavaFile);
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
     * MoMapper名称
     *
     * @return
     */
    public String getMoMapperName() {
        return getMoName().concat(ConstantUtil.MO_MAPPER_NAME_SUFFIX);
    }

    /**
     * MoMapper文件名称
     *
     * @return
     */
    public String getFindMoMapperFileName() {
        return getMoName().concat(ConstantUtil.MO_MAPPER_NAME_SUFFIX).concat(ConstantUtil.JAVA_FILE_SUFFIX);
    }

    /**
     * MoService接口名称
     *
     * @return
     */
    public String getMoServiceInterfaceName() {
        return getMoName().concat(ConstantUtil.MO_SERVICE_INTERFACE_NAME_SUFFIX);
    }

    /**
     * MoMapper文件名称
     *
     * @return
     */
    public String getFindMoServiceInterfaceFileName() {
        return getMoName().concat(ConstantUtil.MO_SERVICE_INTERFACE_NAME_SUFFIX).concat(ConstantUtil.JAVA_FILE_SUFFIX);
    }

    /**
     * MoServiceImpl名称
     *
     * @return
     */
    public String getMoServiceImplName() {
        return getMoName().concat(ConstantUtil.MO_SERVICE_IMPL_NAME_SUFFIX);
    }

    /**
     * MoServiceImpl文件名称
     *
     * @return
     */
    public String getFindMoServiceImplFileName() {
        return getMoName().concat(ConstantUtil.MO_SERVICE_IMPL_NAME_SUFFIX).concat(ConstantUtil.JAVA_FILE_SUFFIX);
    }

    /**
     * MoController名称
     *
     * @return
     */
    public String getMoControllerName() {
        return getMoName().concat(ConstantUtil.MO_CONTROLLER_NAME_SUFFIX);
    }

    /**
     * MoController文件名称
     *
     * @return
     */
    public String getFindMoControllerFileName() {
        return getMoName().concat(ConstantUtil.MO_CONTROLLER_NAME_SUFFIX).concat(ConstantUtil.JAVA_FILE_SUFFIX);
    }
}
