package com.xyz.caofancpu.d8ger.action;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.xyz.caofancpu.d8ger.core.AutoCodeTemplate;
import com.xyz.caofancpu.d8ger.core.D8gerAutoCoding;
import com.xyz.caofancpu.d8ger.core.KeyEnum;
import com.xyz.caofancpu.d8ger.core.ProjectEnvHandler;
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
        D8gerAutoCoding d8gerAutoCoding = ProjectEnvHandler.checkAndInitEnv(e);
        if (Objects.isNull(d8gerAutoCoding)) {
            return;
        }
        // 执行创建目录|文件
        WriteCommandAction.runWriteCommandAction(d8gerAutoCoding.getCurrentProject(), () -> generateAutoCodeFile(d8gerAutoCoding));
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
            if (KeyEnum.MO_CONTROLLER == key) {
                return;
            }
            PsiFile autoCodeFile = IdeaPlatformFileTreeUtil.forceCreateJavaFile(
                    d8gerAutoCodeDir,
                    d8gerAutoCoding.getCurrentProject(),
                    pair.getLeft(),
                    AutoCodeTemplate.render(pair.getRight(), d8gerAutoCoding.getKeyWordMatchMap())
            );
            d8gerAutoCoding.getD8AutoCodeDir().add(autoCodeFile);
            fileNameList.add(autoCodeFile.getName());
        });

        Notifications.Bus.notify(
                new Notification(ConstantUtil.NOTIFICATION_GROUP_VIEW_ID, "重建文件信息", CollectionUtil.join(fileNameList, ConstantUtil.DOUBLE_NEXT_LINE), NotificationType.INFORMATION)
        );
    }

}
