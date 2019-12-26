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
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.xyz.caofancpu.d8ger.core.AutoCodeTemplate;
import com.xyz.caofancpu.d8ger.core.D8gerAutoCoding;
import com.xyz.caofancpu.d8ger.core.KeyEnum;
import com.xyz.caofancpu.d8ger.util.CollectionUtil;
import com.xyz.caofancpu.d8ger.util.ConstantUtil;
import com.xyz.caofancpu.d8ger.util.IdeaPlatformFileTreeUtil;
import lombok.NonNull;
import org.jetbrains.jps.model.java.JavaModuleSourceRootTypes;

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
        if (Objects.isNull(currentModule)) {
            Messages.showErrorDialog("当前文件不在模块中", "环境错误");
            return;
        }
        VirtualFile rootResource = ModuleRootManager.getInstance(currentModule).getSourceRoots(JavaModuleSourceRootTypes.RESOURCES).get(0);
        if (!rootResource.isDirectory()) {
            Messages.showErrorDialog("请为当前文件所在工程创建resource资源目录", "环境错误");
        }
        // 初始化核心类
        D8gerAutoCoding d8gerAutoCoding = D8gerAutoCoding.build(currentProject, currentModule, rootResource, currentJavaFile);
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
        PsiDirectory d8gerAutoCodeDir = IdeaPlatformFileTreeUtil.getOrCreateSubDirectory(d8gerAutoCoding.getCurrentProject(), d8gerAutoCoding.getRootResource(), ConstantUtil.GENERATE_DIR);
        d8gerAutoCoding.setD8AutoCodeDir(d8gerAutoCodeDir);
        fileNameList.add(d8gerAutoCodeDir.getName());

        d8gerAutoCoding.getFileMap().forEach((key, pair) -> {
            if (KeyEnum.MO_CONTROLLER == key
                    || KeyEnum.MO_MAPPER_XML == key
                    || KeyEnum.MO_SQL == key) {
                return;
            }

            PsiJavaFile autoCodeFile = IdeaPlatformFileTreeUtil.forceCreateJavaFile(
                    d8gerAutoCodeDir,
                    d8gerAutoCoding.getCurrentProject(),
                    pair.getLeft(),
                    AutoCodeTemplate.render(pair.getRight(), d8gerAutoCoding.getKeyWordMatchMap())
            );
            d8gerAutoCoding.getD8AutoCodeDir().add(autoCodeFile);
            fileNameList.add(autoCodeFile.getName());
        });

        Notifications.Bus.notify(
                new Notification(ConstantUtil.NOTIFICATION_GROUP_VIEW_ID, "重建文件信息", CollectionUtil.join(fileNameList, ConstantUtil.NEXT_LINE), NotificationType.INFORMATION)
        );
    }

}
