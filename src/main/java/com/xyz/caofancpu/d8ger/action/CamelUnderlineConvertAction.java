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
import com.xyz.caofancpu.d8ger.util.DateUtil;
import com.xyz.caofancpu.d8ger.util.VerbalExpressionUtil;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

/**
 * 1.Camel-Underline convert
 * 2.Uppercase-Lowercase convert
 * 3.All of the convert by shortcut alt + shift + ctl + 'U'
 *
 * @author caofanCPU
 */
public class CamelUnderlineConvertAction extends AnAction {
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
        if (!selectionModel.hasSelection()) {
            selectionModel.selectWordAtCaret(true);
        }

        int selectionStart = selectionModel.getSelectionStart();
        int selectionEnd = selectionModel.getSelectionEnd();
        String originWord = currentDocument.getText(new TextRange(selectionStart, selectionEnd));
        String replacement = handleReplacement(originWord);
        currentDocument.replaceString(selectionStart, selectionEnd, replacement);
        selectionModel.removeSelection();
    }

    /**
     * TimeStamp - LocalDateTime
     *
     * @param originWord
     * @return
     */
    private String handleReplacement(@NonNull String originWord) {
        String result = null;
        try {
            // First, 1600069557000 to 2020-09-14 15:45:57.000
            Long milliSeconds = Long.valueOf(originWord);
            result = DateUtil.enhanceToLocalDateTime(milliSeconds);
        } catch (Exception e) {
            // ignore
            try {
                result = DateUtil.enhanceParseMilliSeconds(originWord);
            } catch (Exception exception) {
                // ignore
            }
        }
        if (StringUtils.isBlank(result)) {
            result = VerbalExpressionUtil.camelUnderLineNameConverter(originWord);
        }
        return result;
    }
}
