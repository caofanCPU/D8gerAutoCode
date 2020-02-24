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
import com.xyz.caofancpu.d8ger.core.TemplateKeyWordEnum;
import com.xyz.caofancpu.d8ger.util.CollectionUtil;
import com.xyz.caofancpu.d8ger.util.ConstantUtil;
import com.xyz.caofancpu.d8ger.util.IdeaPlatformFileTreeUtil;
import com.xyz.caofancpu.d8ger.util.PropertiesUtil;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * ACP(AutoCodingProgramming)
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
        // Create directory | file
        WriteCommandAction.runWriteCommandAction(d8gerAutoCoding.getCurrentProject(), () -> generateAutoCodeFile(d8gerAutoCoding));
    }

    /**
     * Perform file creation, all directory | file write operations must be inside this method,
     * and externally use WriteCommandAction.runWriteCommandAction for wrapping
     *
     * @param d8gerAutoCoding
     */
    public void generateAutoCodeFile(D8gerAutoCoding d8gerAutoCoding) {
        List<String> fileNameList = new ArrayList<>();
        // 1.Complete directory and package data
        d8gerAutoCoding.getFileMap().keySet().forEach(key -> {
            Pair<PsiDirectory, String> packagePair = d8gerAutoCoding.getCustomConfigAutoCodeDirMap().get(key);
            if (Objects.isNull(packagePair)) {
                if (!d8gerAutoCoding.isUseDefaultDirectory()) {
                    // Create D8AutoCode directory
                    PsiDirectory d8gerAutoCodeDir = IdeaPlatformFileTreeUtil.getOrCreateSubDir(d8gerAutoCoding.getD8AutoCodeDir(), ConstantUtil.GENERATE_DIR);
                    d8gerAutoCoding.setD8AutoCodeDir(d8gerAutoCodeDir);
                    d8gerAutoCoding.setUseDefaultDirectory(true);
                    fileNameList.add(d8gerAutoCoding.getD8AutoCodeDir().getName());
                }
                d8gerAutoCoding.getCustomConfigAutoCodeDirMap().put(key, Pair.of(d8gerAutoCoding.getD8AutoCodeDir(), d8gerAutoCoding.getPackageName()));
            }
        });

        // 2.Replace keyword data
        handleCustomPackageKeyWordMap(d8gerAutoCoding);

        // 3.Just force file
        d8gerAutoCoding.getFileMap().forEach((key, pair) -> {
            if (skipCurrentOperation(d8gerAutoCoding, key)) {
                // don't create file
                return;
            }
            PsiDirectory targetDirectory = d8gerAutoCoding.getCustomConfigAutoCodeDirMap().get(key).getLeft();
            PsiJavaFile autoCodeFile = IdeaPlatformFileTreeUtil.forceCreateJavaFile(
                    targetDirectory,
                    d8gerAutoCoding.getCurrentProject(),
                    pair.getLeft(),
                    AutoCodeTemplate.render(pair.getRight(), d8gerAutoCoding.loadEnhanceKeyWordMap(key))
            );
            // Find and import enum classes
            if (KeyEnum.needImportEnumClass(key)) {
                d8gerAutoCoding.getEnumTypeClassName().forEach(item -> {
                    Optional<PsiClass> optionalPsiClass = IdeaPlatformFileTreeUtil.findClass(d8gerAutoCoding.getCurrentProject(), item);
                    optionalPsiClass.ifPresent(autoCodeFile::importClass);
                    if (!skipCurrentOperation(d8gerAutoCoding, KeyEnum.FORMAT_STYLE)) {
                        // do code formatting
                        IdeaPlatformFileTreeUtil.format(d8gerAutoCoding.getCurrentProject(), autoCodeFile);
                    }
                });
            }
            // This step is very important which would cause fail of creating file if it was ignored
            targetDirectory.add(autoCodeFile);
            fileNameList.add(autoCodeFile.getName());
        });

        Notifications.Bus.notify(
                new Notification(ConstantUtil.NOTIFICATION_GROUP_VIEW_ID, "Look Rebuilding file here", CollectionUtil.join(fileNameList, ConstantUtil.DOUBLE_NEXT_LINE), NotificationType.INFORMATION)
        );
    }

    /**
     * Skip current operation or not
     *
     * @param d8gerAutoCoding
     * @param keyEnum
     * @return
     */
    private boolean skipCurrentOperation(D8gerAutoCoding d8gerAutoCoding, KeyEnum keyEnum) {
        return !PropertiesUtil.checkConfigTakEffect(d8gerAutoCoding.loadPropertiesFromRootResource(), keyEnum.getKey());
    }

    /**
     * Handle replacing package names by custom directory configs
     */
    private void handleCustomPackageKeyWordMap(D8gerAutoCoding d8gerAutoCoding) {
        Map<String, StringBuilder> keyWordMatchMap = d8gerAutoCoding.getKeyWordMatchMap();
        d8gerAutoCoding.getCustomConfigAutoCodeDirMap().forEach((key, pair) -> {
            TemplateKeyWordEnum targetPackageKey = null;
            switch (key) {
                case MO:
                    targetPackageKey = TemplateKeyWordEnum.MO_PACKAGE_NAME_KEY;
                    break;
                case MO_MAPPER:
                    targetPackageKey = TemplateKeyWordEnum.MAPPER_PACKAGE_NAME_KEY;
                    break;
                case MO_EXAMPLE:
                    targetPackageKey = TemplateKeyWordEnum.MO_EXAMPLE_PACKAGE_NAME_KEY;
                    break;
                case SWAGGER_MO:
                    targetPackageKey = TemplateKeyWordEnum.SWAGGER_MO_PACKAGE_NAME_KEY;
                    break;
                case MO_SERVICE_INTERFACE:
                    targetPackageKey = TemplateKeyWordEnum.SERVICE_INTERFACE_PACKAGE_NAME_KEY;
                    break;
                case MO_SERVICE_IMPL:
                    targetPackageKey = TemplateKeyWordEnum.SERVICE_IMPLEMENT_PACKAGE_NAME_KEY;
                    break;
                case MO_CONTROLLER:
                    targetPackageKey = TemplateKeyWordEnum.CONTROLLER_PACKAGE_NAME_KEY;
                    break;
                default:
                    break;
            }
            if (Objects.nonNull(targetPackageKey)) {
                keyWordMatchMap.put(targetPackageKey.getName(), new StringBuilder(pair.getRight()));
            }
        });
    }

}
