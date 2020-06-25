package com.xyz.caofancpu.d8ger.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.xyz.caofancpu.d8ger.util.StringAlignUtil;
import lombok.NonNull;

/**
 * Beautiful MySQL
 *
 * @author caofanCPU
 */
public class BeautifulMySQLAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Editor currentEditor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project currentProject = e.getRequiredData(CommonDataKeys.PROJECT);
        final Document currentDocument = currentEditor.getDocument();
        final SelectionModel selectionModel = currentEditor.getSelectionModel();

        // Perform a refresh of the selected words
        WriteCommandAction.runWriteCommandAction(currentProject, () -> executeCamelUnderlineConvert(currentDocument, selectionModel));
    }

    /**
     * Rewrite files for removing whitespace and reducing to one line
     *
     * @param currentDocument
     */
    private void executeCamelUnderlineConvert(@NonNull Document currentDocument, @NonNull SelectionModel selectionModel) {
        if (!selectionModel.hasSelection()) {
            selectionModel.selectWordAtCaret(true);
        }

        int selectionStart = selectionModel.getSelectionStart();
        int selectionEnd = selectionModel.getSelectionEnd();
        String originSql = currentDocument.getText(new TextRange(selectionStart, selectionEnd));
        String replacement = StringAlignUtil.formatMySQL(originSql);
        currentDocument.replaceString(selectionStart, selectionEnd, replacement);
        selectionModel.removeSelection();
    }
}
