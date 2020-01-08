package com.xyz.caofancpu.d8ger.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.xyz.caofancpu.d8ger.util.VerbalExpressionUtil;
import lombok.NonNull;

/**
 * 去除空白字符浓缩为一行
 *
 * @author caofanCPU
 */
public class SimplifyOneLineAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Editor currentEditor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project currentProject = e.getRequiredData(CommonDataKeys.PROJECT);
        final Document currentDocument = currentEditor.getDocument();

        // 执行刷新当前文件
        WriteCommandAction.runWriteCommandAction(currentProject, () -> executeBeautyJSONRender(currentDocument));
    }

    /**
     * 重写文件, 去除空白字符浓缩为一行
     *
     * @param currentDocument
     */
    private void executeBeautyJSONRender(@NonNull Document currentDocument) {
        currentDocument.setText(VerbalExpressionUtil.cleanWhiteChar(currentDocument.getText()));
    }
}
