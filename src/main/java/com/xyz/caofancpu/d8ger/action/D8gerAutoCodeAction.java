package com.xyz.caofancpu.d8ger.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.FileIndexFacade;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.xyz.caofancpu.d8ger.task.D8gerAutoCoding;
import com.xyz.caofancpu.d8ger.template.MapperTemplateAutoCode;
import com.xyz.caofancpu.d8ger.util.ConstantUtil;
import com.xyz.caofancpu.d8ger.util.IdeaPlatformFileTreeUtil;
import lombok.NonNull;

import java.util.Objects;

/**
 * ACP(AutoCodingProgramming)编程
 *
 * @author caofanCPU
 */
public class D8gerAutoCodeAction extends AnAction {

    @Override
    public void actionPerformed(@NonNull final AnActionEvent e) {
        PsiFile currentPsiFile = e.getData(CommonDataKeys.PSI_FILE);
        if (Objects.isNull(currentPsiFile) || !(currentPsiFile instanceof PsiJavaFile)) {
            Messages.showErrorDialog("请选中Java文件后再操作", "环境错误");
            return;
        }

        PsiJavaFile currentJavaFile = (PsiJavaFile) currentPsiFile;
        if (currentJavaFile.getClasses().length == 0) {
            Messages.showErrorDialog("所选文件没有定义类", "环境错误");
            return;
        }

        Project currentProject = e.getData(PlatformDataKeys.PROJECT);
        if (Objects.isNull(currentProject)) {
            Messages.showErrorDialog("当前文件不在项目中", "环境错误");
            return;
        }

        Module currentModule = FileIndexFacade.getInstance(currentProject).getModuleForFile(currentPsiFile.getVirtualFile());
        PsiDirectory currentDir = currentJavaFile.getContainingDirectory();
        D8gerAutoCoding d8gerAutoCoding = D8gerAutoCoding.build(currentProject, currentModule, currentDir, currentJavaFile);

        WriteCommandAction.runWriteCommandAction(currentProject, () -> CommandProcessor.getInstance().executeCommand(null, () -> generateAutoCodeFile(d8gerAutoCoding), null, null));
    }

    private void generateAutoCodeFile(D8gerAutoCoding d8gerAutoCoding) {
        PsiFile originFile = d8gerAutoCoding.getD8AutoCodeDir().findFile(d8gerAutoCoding.getFindMoMapperFileName());
        if (Objects.nonNull(originFile)) {
            originFile.delete();
        }
        PsiJavaFile moMapperJavaFile = IdeaPlatformFileTreeUtil.createJavaFile(d8gerAutoCoding.getCurrentProject(), d8gerAutoCoding.getMoName().concat(ConstantUtil.MO_MAPPER_NAME_SUFFIX), MapperTemplateAutoCode.build(d8gerAutoCoding.getMoName()));
        d8gerAutoCoding.getD8AutoCodeDir().add(moMapperJavaFile);
        d8gerAutoCoding.setMoMapperJavaFile(moMapperJavaFile);

//        Notifications.Bus.notify(
//                new Notification("xtools", "文件信息", fileName, NotificationType.INFORMATION)
//        );
    }

}
