package com.xyz.caofancpu.d8ger.action;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiJavaFile;
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
import java.util.Optional;

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
        PsiDirectory d8gerAutoCodeDir = IdeaPlatformFileTreeUtil.getOrCreateSubDir(d8gerAutoCoding.getD8AutoCodeDir(), ConstantUtil.GENERATE_DIR);

        d8gerAutoCoding.setD8AutoCodeDir(d8gerAutoCodeDir);
        fileNameList.add(d8gerAutoCodeDir.getName());

        d8gerAutoCoding.getFileMap().forEach((key, pair) -> {
            if (skipCurrentOperation(d8gerAutoCoding, key)) {
                // 不需要创建文件
                return;
            }
            PsiJavaFile autoCodeFile = IdeaPlatformFileTreeUtil.forceCreateJavaFile(
                    d8gerAutoCodeDir,
                    d8gerAutoCoding.getCurrentProject(),
                    pair.getLeft(),
                    AutoCodeTemplate.render(pair.getRight(), d8gerAutoCoding.getKeyWordMatchMap())
            );
            // 查找并导入枚举类
            if (KeyEnum.needImportEnumClass(key)) {
                d8gerAutoCoding.getEnumTypeClassName().forEach(item -> {
                    Optional<PsiClass> optionalPsiClass = IdeaPlatformFileTreeUtil.findClass(d8gerAutoCoding.getCurrentProject(), item);
                    optionalPsiClass.ifPresent(autoCodeFile::importClass);
                    if (!skipCurrentOperation(d8gerAutoCoding, KeyEnum.FORMAT_STYLE)) {
                        // 需要格式化
                        IdeaPlatformFileTreeUtil.format(d8gerAutoCoding.getCurrentProject(), autoCodeFile);
                    }
                });
            }

            if (KeyEnum.MO_SQL == key) {
                // SQL文件也进行格式化
                IdeaPlatformFileTreeUtil.format(d8gerAutoCoding.getCurrentProject(), autoCodeFile);
            }

            d8gerAutoCoding.getD8AutoCodeDir().add(autoCodeFile);
            fileNameList.add(autoCodeFile.getName());
        });

        Notifications.Bus.notify(
                new Notification(ConstantUtil.NOTIFICATION_GROUP_VIEW_ID, "重建文件信息", CollectionUtil.join(fileNameList, ConstantUtil.DOUBLE_NEXT_LINE), NotificationType.INFORMATION)
        );
    }

    private boolean skipCurrentOperation(D8gerAutoCoding d8gerAutoCoding, KeyEnum keyEnum) {
        return !Boolean.valueOf(d8gerAutoCoding.loadPropertiesFromRootResource().getProperty(keyEnum.getKey()));
    }

}
