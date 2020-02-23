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
 * Beautify JSON
 *
 * @author caofanCPU
 */
public class BeautifulJSONAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Editor currentEditor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project currentProject = e.getRequiredData(CommonDataKeys.PROJECT);
        final Document currentDocument = currentEditor.getDocument();

        // Perform a refresh of the current file
        WriteCommandAction.runWriteCommandAction(currentProject, () -> executeBeautyJSONRender(currentDocument));
    }

    /**
     * Rewrite files for beautifying JSON string
     *
     * @param currentDocument
     */
    private void executeBeautyJSONRender(@NonNull Document currentDocument) {
        currentDocument.setText(JSONUtil.formatStandardJSON(currentDocument.getText()));
    }
}
