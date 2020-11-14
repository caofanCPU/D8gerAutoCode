package com.xyz.caofancpu.d8ger.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.xyz.caofancpu.d8ger.util.CollectionUtil;
import com.xyz.caofancpu.d8ger.util.ConstantUtil;
import com.xyz.caofancpu.d8ger.util.VerbalExpressionUtil;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Make Ascii text graph easy enough
 *
 * @author D8GER
 */
public class GraphEasyAction extends AnAction {

    /**
     * Convert ascii text graph definition, such as
     * C.mapper.List[D].classify.Map<F, List[G]>
     * |
     * v
     * [C]-- mapper -->[List\[D\]]-- classify -->[Map\<F, List\[G\]\>]
     *
     * @return
     */
    private static String convertGraphEasyDSL(String source) {
        String[] split = source.split(VerbalExpressionUtil.NEXT_LINE_REGEX.pattern());
        List<String> resultList = new ArrayList<>();
        for (String defineText : split) {
            String initGraphEasy = defineText.replaceAll(VerbalExpressionUtil.KEY_COLLECTION_EDGE_SYMBOL_REGEX.pattern(), ConstantUtil.DOUBLE_ESCAPES + VerbalExpressionUtil.REPLACE_MATCH_RESULT_SYMBOL);
            String[] elements = initGraphEasy.split(VerbalExpressionUtil.KEY_ENGLISH_DOT_REGEX.pattern());
            StringBuilder itemResult = new StringBuilder(ConstantUtil.EMPTY);
            for (int i = 0; i < elements.length; i++) {
                if (i % 2 == 0) {
                    // this is graph node
                    itemResult.append(ConstantUtil.ENGLISH_MID_LEFT_BRACKET).append(elements[i]).append(ConstantUtil.ENGLISH_MID_RIGHT_BRACKET);
                } else {
                    // this is graph edge with it's note
                    itemResult.append(ConstantUtil.FILL_EMPTY_PLACE_HOLDER).append(ConstantUtil.SPACE).append(elements[i]).append(ConstantUtil.SPACE).append(ConstantUtil.FILL_EMPTY_PLACE_HOLDER).append(ConstantUtil.GREATER);
                }
            }
            resultList.add(itemResult.toString());
        }
        return CollectionUtil.join(resultList, ConstantUtil.NEXT_LINE);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Editor currentEditor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project currentProject = e.getRequiredData(CommonDataKeys.PROJECT);
        final Document currentDocument = currentEditor.getDocument();
        final SelectionModel selectionModel = currentEditor.getSelectionModel();

        // Perform a refresh of the current file
        WriteCommandAction.runWriteCommandAction(currentProject, () -> executeGraphEasyRender(currentDocument, selectionModel));
    }

    /**
     * Rewrite files for easy graph dsl
     *
     * @param currentDocument
     * @param selectionModel
     */
    private void executeGraphEasyRender(@NonNull Document currentDocument, @NonNull SelectionModel selectionModel) {
        String sectionText = selectionModel.getSelectedText();
        if (StringUtils.isNotBlank(sectionText)) {
            String replacement = convertGraphEasyDSL(sectionText);
            currentDocument.replaceString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd(), replacement);
        } else {
            currentDocument.setText(convertGraphEasyDSL(currentDocument.getText()));
        }
    }

}
