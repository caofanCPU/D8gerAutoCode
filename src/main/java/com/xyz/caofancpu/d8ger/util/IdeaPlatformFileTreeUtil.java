package com.xyz.caofancpu.d8ger.util;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import lombok.NonNull;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * IDEA平台文件系统操作工具类
 *
 * @author caofanCPU
 **/
public class IdeaPlatformFileTreeUtil {

    public static PsiJavaFile forceCreateJavaFile(@NonNull PsiDirectory psiDirectory, @NonNull Project project, @NonNull String javaFileDotName, @NonNull String javaFileName, @NonNull String content) {
        PsiFile originFile = psiDirectory.findFile(javaFileDotName);
        if (Objects.nonNull(originFile)) {
            originFile.delete();
        }
        return createJavaFile(project, javaFileName, content);
    }

    public static PsiJavaFile createJavaFile(@NonNull Project project, @NonNull String name, @NonNull String content) {
        return (PsiJavaFile) PsiFileFactory.getInstance(project).createFileFromText(name.concat(ConstantUtil.JAVA_FILE_SUFFIX), JavaFileType.INSTANCE, content);
    }

    public static Optional<String> getAnnotationValue(@NonNull PsiClass psiClass, @NonNull String annotation, @NonNull String field) {
        return Optional.ofNullable(psiClass.getAnnotation(annotation))
                .map(a -> {
                    PsiAnnotationMemberValue value = a.findAttributeValue(field);
                    return Objects.nonNull(value) ? Optional.of(value.getText()) : Optional.<String>empty();
                })
                .orElse(Optional.empty());
    }

    /**
     * 格式化代码
     *
     * @param project
     * @param psiElement 需要格式化的文件
     */
    public static void format(@NonNull Project project, @NonNull PsiElement psiElement) {
        CodeStyleManager.getInstance(project).reformat(psiElement);
    }

    /**
     * 查找类
     *
     * @param className 类名
     * @return 查找到的类
     */
    public static Optional<PsiClass> findClass(@NonNull Project project, @NonNull String className) {
        return findClass(project, className, psiClass -> true);
    }

    public static Optional<PsiClass> findClass(@NonNull Project project, @NonNull String className, Predicate<PsiClass> predicate) {
        PsiShortNamesCache shortNamesCache = PsiShortNamesCache.getInstance(project);

        int idx = className.lastIndexOf(".");
        if (-1 != idx) {
            String packageName = className.substring(0, idx);
            String name = className.substring(idx + 1);
            PsiClass[] classes = shortNamesCache.getClassesByName(name, GlobalSearchScope.allScope(project));

            if (0 != classes.length) {
                for (PsiClass aClass : classes) {
                    PsiJavaFile javaFile = (PsiJavaFile) aClass.getContainingFile();
                    if (javaFile.getPackageName().equals(packageName) && predicate.test(aClass)) {
                        return Optional.of(aClass);
                    }
                }
            }
        } else {
            PsiClass[] classes = shortNamesCache.getClassesByName(className, GlobalSearchScope.allScope(project));
            if (0 != classes.length) {
                for (PsiClass aClass : classes) {
                    if (predicate.test(aClass)) {
                        return Optional.ofNullable(aClass);
                    }
                }
            }
        }

        return Optional.empty();
    }

    /**
     * 获取或者创建子目录
     *
     * @param parentDirectory  父级目录
     * @param subDirectoryName 子目录名称
     * @return 查找到的或者创建的子目录名称
     */
    public static PsiDirectory getOrCreateSubDirectory(@NonNull PsiDirectory parentDirectory, @NonNull String subDirectoryName) {
        return Optional.ofNullable(parentDirectory.findSubdirectory(subDirectoryName)).orElseGet(() -> parentDirectory.createSubdirectory(subDirectoryName));
    }
}
