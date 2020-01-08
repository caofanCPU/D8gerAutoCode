package com.xyz.caofancpu.d8ger.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.xyz.caofancpu.d8ger.util.JSONUtil;
import lombok.NonNull;

/**
 * 美化JSON
 *
 * @author caofanCPU
 */
public class BeautifulJSONAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Editor currentEditor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project currentProject = e.getRequiredData(CommonDataKeys.PROJECT);
        final Document currentDocument = currentEditor.getDocument();

        // 执行刷新当前文件
        WriteCommandAction.runWriteCommandAction(currentProject, () -> executeBeautyJSONRender(currentDocument));
    }

    /**
     * 重写文件, 美化JSON字符串
     *
     * @param currentDocument
     */
    private void executeBeautyJSONRender(@NonNull Document currentDocument) {
        currentDocument.setText(JSONUtil.formatStandardJSON(currentDocument.getText()));
    }
}
