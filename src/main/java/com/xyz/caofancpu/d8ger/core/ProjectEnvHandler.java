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
 * Initialize the project environment parameters
 *
 * @author caofanCPU
 */
public class ProjectEnvHandler {

    public static D8gerAutoCoding checkAndInitEnv(AnActionEvent e) {
        PsiFile currentPsiFile = e.getData(CommonDataKeys.PSI_FILE);
        if (Objects.isNull(currentPsiFile) || !(currentPsiFile instanceof PsiJavaFile)) {
            Messages.showErrorDialog("No .java file chosen", "Initialize Environment Error");
            return null;
        }

        PsiJavaFile currentJavaFile = (PsiJavaFile) currentPsiFile;
        if (currentJavaFile.getClasses().length == 0) {
            Messages.showErrorDialog("No class defined in current chosen .java file", "Initialize Environment Error");
            return null;
        }

        Project currentProject = e.getData(PlatformDataKeys.PROJECT);
        if (Objects.isNull(currentProject)) {
            Messages.showErrorDialog("Not included in a project for current chosen .java file", "Initialize Environment Error");
            return null;
        }

        Module currentModule = FileIndexFacade.getInstance(currentProject).getModuleForFile(currentPsiFile.getVirtualFile());
        if (Objects.isNull(currentModule)) {
            Messages.showErrorDialog("Not included in a module for current chosen .java file", "Initialize Environment Error");
            return null;
        }
        VirtualFile rootResource = ModuleRootManager.getInstance(currentModule).getSourceRoots(JavaModuleSourceRootTypes.RESOURCES).get(0);
        if (!rootResource.isDirectory()) {
            Messages.showErrorDialog("No resource directory for the project where the current .java file is located", "Initialize Environment Error");
        }
        // Initialize the core class
        return D8gerAutoCoding.build(currentProject, currentModule, rootResource, currentJavaFile);
    }

}
