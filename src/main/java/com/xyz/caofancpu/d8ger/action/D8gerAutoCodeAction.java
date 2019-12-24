package com.xyz.caofancpu.d8ger.action;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.FileIndexFacade;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.xyz.caofancpu.d8ger.core.D8gerAutoCoding;
import com.xyz.caofancpu.d8ger.template.MapperTemplateAutoCode;
import com.xyz.caofancpu.d8ger.util.CollectionUtil;
import com.xyz.caofancpu.d8ger.util.ConstantUtil;
import com.xyz.caofancpu.d8ger.util.IdeaPlatformFileTreeUtil;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ACP(AutoCodingProgramming)编程
 *
 * @author caofanCPU
 */
public class D8gerAutoCodeAction extends AnAction {

    @Override
    public synchronized void actionPerformed(@NonNull final AnActionEvent e) {
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
        // 初始化核心类
        D8gerAutoCoding d8gerAutoCoding = D8gerAutoCoding.build(currentProject, currentModule, currentDir, currentJavaFile);
        // 执行创建目录|文件
        WriteCommandAction.runWriteCommandAction(currentProject, () -> generateAutoCodeFile(d8gerAutoCoding));
    }

    /**
     * 执行文件创建, 所有目录|文件的写操作都必须在此方法内部, 外部使用WriteCommandAction.runWriteCommandAction包装
     *
     * @param d8gerAutoCoding
     */
    public void generateAutoCodeFile(D8gerAutoCoding d8gerAutoCoding) {
        List<String> fileNameList = new ArrayList<>();
        // 创建D8AutoCode目录
        PsiDirectory d8gerAutoCodeDir = IdeaPlatformFileTreeUtil.getOrCreateSubDirectory(d8gerAutoCoding.getD8AutoCodeDir(), ConstantUtil.GENERATE_DIR);
        d8gerAutoCoding.setD8AutoCodeDir(d8gerAutoCodeDir);
        fileNameList.add(d8gerAutoCodeDir.getName());

        d8gerAutoCoding.getJavaPairMatchMap().forEach((nameKey, fileNameKey) -> {
            PsiJavaFile moMapperJavaFile = IdeaPlatformFileTreeUtil.forceCreateJavaFile(
                    d8gerAutoCodeDir,
                    d8gerAutoCoding.getCurrentProject(),
                    d8gerAutoCoding.getNameKeyMap().get(fileNameKey),
                    d8gerAutoCoding.getNameKeyMap().get(nameKey),
                    parseTemplateContent(fileNameKey, d8gerAutoCoding.getMoName()));
            d8gerAutoCoding.getD8AutoCodeDir().add(moMapperJavaFile);
            fileNameList.add(d8gerAutoCoding.getNameKeyMap().get(fileNameKey));
        });


        Notifications.Bus.notify(
                new Notification(ConstantUtil.NOTIFICATION_GROUP_VIEW_ID, "重建文件信息", CollectionUtil.join(fileNameList, ConstantUtil.NEXT_LINE), NotificationType.INFORMATION)
        );
    }

    private String parseTemplateContent(D8gerAutoCoding.NameKeyEnum fileNameKey, @NonNull String moNameKey) {
        String content = "暂无可解析的模板: " + fileNameKey.toString();
        switch (fileNameKey) {
            case MO_FILE_NAME:
                break;
            case MO_MAPPER_FILE_NAME:
                content = MapperTemplateAutoCode.build(moNameKey);
                break;
            case MO_MAPPER_XML_FILE_NAME:
                break;
            case MO_SERVICE_INTERFACE_FILE_NAME:
                break;
            case MO_SERVICE_IMPL_FILE_NAME:
                break;
            case MO_CONTROLLER_FILE_NAME:
                break;
            default:
                break;
        }
        return content;
    }

}
