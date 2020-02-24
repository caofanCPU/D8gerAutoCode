package com.xyz.caofancpu.d8ger.util;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.impl.file.PsiDirectoryFactory;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import lombok.NonNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * IDEA platform file system operation tools
 *
 * @author caofanCPU
 **/
public class IdeaPlatformFileTreeUtil {

    /**
     * Create a file forcibly, delete the source file before creating it
     *
     * @param psiDirectory
     * @param project
     * @param dotFileName  for example: a.java | b.xml | c.sql | d.txt | e.mp3
     * @param content
     * @return
     */
    public static PsiJavaFile forceCreateJavaFile(@NonNull PsiDirectory psiDirectory, @NonNull Project project, @NonNull String dotFileName, @NonNull CharSequence content) {
        PsiFile originFile = psiDirectory.findFile(dotFileName);
        if (Objects.nonNull(originFile)) {
            originFile.delete();
        }
        return createJavaFile(project, dotFileName, content);
    }

    /**
     * Create file, throw exception when source file exists
     *
     * @param project
     * @param dotFileName
     * @param content
     * @return
     */
    public static PsiJavaFile createJavaFile(@NonNull Project project, @NonNull String dotFileName, @NonNull CharSequence content) {
        return (PsiJavaFile) PsiFileFactory.getInstance(project).createFileFromText(dotFileName, JavaFileType.INSTANCE, content);
    }

    /**
     * Formatting code
     *
     * @param project
     * @param psiElement File which needs to be formatted
     */
    public static void format(@NonNull Project project, @NonNull PsiElement psiElement) {
        CodeStyleManager.getInstance(project).reformat(psiElement);
    }

    /**
     * Find class
     *
     * @param className class name
     * @return
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
     * Get or create a subdirectory
     *
     * @param project           current project
     * @param subDirVirtualFile subdirectory file name
     * @return
     */
    public static PsiDirectory getOrCreateSubDir(@NonNull Project project, @NonNull VirtualFile subDirVirtualFile) {
        return PsiDirectoryFactory.getInstance(project).createDirectory(subDirVirtualFile);
    }

    /**
     * Get or create a subdirectory
     *
     * @param currentProject        current project
     * @param directoryRelativePath directoryRelativePath refer project's root path, see: Project.getBasePath()
     * @return
     */
    public static PsiDirectory getOrCreateSubDirByPath(@NonNull Project currentProject, @NonNull String directoryRelativePath) {
        String projectRootPath = currentProject.getBasePath();
        String fileDirAbsolutePath = VerbalExpressionUtil.correctUrl(projectRootPath + File.separator + directoryRelativePath);
        VirtualFile fileByIoFileDir = LocalFileSystem.getInstance().findFileByIoFile(new File(fileDirAbsolutePath));
        if (Objects.isNull(fileByIoFileDir) || !fileByIoFileDir.exists()) {
            return null;
        }
        PsiDirectory result = null;
        try {
            result = IdeaPlatformFileTreeUtil.getOrCreateSubDir(currentProject, fileByIoFileDir);
        } catch (Throwable e) {
            // this exception means the directory of directoryRelativePath does't exist! so just ignore it
        }
        return result;
    }

    /**
     * Create child files
     *
     * @param currentVirtualFile
     * @param subVirtualFileName
     * @return
     */
    public static VirtualFile getOrCreateSubVirtualFile(@NonNull VirtualFile currentVirtualFile, @NonNull String subVirtualFileName) {
        VirtualFile child = currentVirtualFile.findChild(subVirtualFileName);
        if (Objects.isNull(child)) {
            try {
                child = currentVirtualFile.createChildDirectory(null, subVirtualFileName);
            } catch (IOException e) {
                // When the creation fails, the directory indicated by the currentVirtualFile root shall prevail
                child = currentVirtualFile;
            }
        }
        return child;
    }

    /**
     * Create a sub file directory
     *
     * @param project
     * @param currentVirtualFile
     * @param subVirtualFileName
     * @return
     */
    public static PsiDirectory getOrCreateSubDir(@NonNull Project project, @NonNull VirtualFile currentVirtualFile, @NonNull String subVirtualFileName) {
        return getOrCreateSubDir(project, getOrCreateSubVirtualFile(currentVirtualFile, subVirtualFileName));
    }

    /**
     * Create a sub file directory which class type is PsiDirectory
     *
     * @param currentPsiDir
     * @param subDirName
     * @return
     */
    public static PsiDirectory getOrCreateSubDir(@NonNull PsiDirectory currentPsiDir, @NonNull String subDirName) {
        return Optional.ofNullable(currentPsiDir.findSubdirectory(subDirName)).orElseGet(() -> currentPsiDir.createSubdirectory(subDirName));
    }
}
