package com.xyz.caofancpu.d8ger.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.xyz.caofancpu.d8ger.util.VerbalExpressionUtil;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

/**
 * 1.Remove whitespace
 * 2.Reduce to one line
 *
 * @author caofanCPU
 */
public class SimplifyOneLineAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Editor currentEditor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project currentProject = e.getRequiredData(CommonDataKeys.PROJECT);
        final Document currentDocument = currentEditor.getDocument();
        final SelectionModel selectionModel = currentEditor.getSelectionModel();

        // Perform a refresh of the current file
        WriteCommandAction.runWriteCommandAction(currentProject, () -> executeBeautyJSONRender(currentDocument, selectionModel));
    }

    /**
     * Rewrite files for removing whitespace and reducing to one line
     *
     * @param currentDocument
     * @param selectionModel
     */
    private void executeBeautyJSONRender(@NonNull Document currentDocument, @NonNull SelectionModel selectionModel) {
        String sectionText = selectionModel.getSelectedText();
        boolean handleSection = true;
        if (StringUtils.isBlank(sectionText)) {
            sectionText = currentDocument.getText();
            handleSection = false;
        }
        boolean isJSON = VerbalExpressionUtil.JSON_STRING_JUDGE_REGEX.matcher(sectionText).matches();
        String replacement = isJSON ? VerbalExpressionUtil.cleanJSONWhiteChar(sectionText) : VerbalExpressionUtil.cleanWhiteChar(sectionText);
        if (handleSection) {
            currentDocument.replaceString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd(), replacement);
        } else {
            currentDocument.setText(replacement);
        }
    }
}
