package com.xyz.caofancpu.d8ger.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.xyz.caofancpu.d8ger.util.StringAlignUtil;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

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
     * @param selectionModel
     */
    private void executeCamelUnderlineConvert(@NonNull Document currentDocument, @NonNull SelectionModel selectionModel) {
        String originSql = selectionModel.getSelectedText();
        if (StringUtils.isNotBlank(originSql)) {
            String replacement = StringAlignUtil.formatMySQL(originSql);
            currentDocument.replaceString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd(), replacement);
        } else {
            currentDocument.setText(StringAlignUtil.formatMySQL(currentDocument.getText()));
        }
    }
}
