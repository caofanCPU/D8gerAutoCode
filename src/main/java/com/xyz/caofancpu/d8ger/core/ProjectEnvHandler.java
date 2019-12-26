package com.xyz.caofancpu.d8ger.core;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.FileIndexFacade;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import org.jetbrains.jps.model.java.JavaModuleSourceRootTypes;

import java.util.Objects;

/**
 * 初始化项目环境
 *
 * @author caofanCPU
 */
public class ProjectEnvHandler {

    public static D8gerAutoCoding checkAndInitEnv(AnActionEvent e) {
        PsiFile currentPsiFile = e.getData(CommonDataKeys.PSI_FILE);
        if (Objects.isNull(currentPsiFile) || !(currentPsiFile instanceof PsiJavaFile)) {
            Messages.showErrorDialog("请选中Java文件后再操作", "环境错误");
            return null;
        }

        PsiJavaFile currentJavaFile = (PsiJavaFile) currentPsiFile;
        if (currentJavaFile.getClasses().length == 0) {
            Messages.showErrorDialog("所选文件没有定义类", "环境错误");
            return null;
        }

        Project currentProject = e.getData(PlatformDataKeys.PROJECT);
        if (Objects.isNull(currentProject)) {
            Messages.showErrorDialog("当前文件不在项目中", "环境错误");
            return null;
        }

        Module currentModule = FileIndexFacade.getInstance(currentProject).getModuleForFile(currentPsiFile.getVirtualFile());
        if (Objects.isNull(currentModule)) {
            Messages.showErrorDialog("当前文件不在模块中", "环境错误");
            return null;
        }
        VirtualFile rootResource = ModuleRootManager.getInstance(currentModule).getSourceRoots(JavaModuleSourceRootTypes.RESOURCES).get(0);
        if (!rootResource.isDirectory()) {
            Messages.showErrorDialog("请为当前文件所在工程创建resource资源目录", "环境错误");
        }
        // 初始化核心类
        return D8gerAutoCoding.build(currentProject, currentModule, rootResource, currentJavaFile);
    }

}
