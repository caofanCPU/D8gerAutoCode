package com.xyz.caofancpu.d8ger.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.xyz.caofancpu.d8ger.util.CollectionUtil;
import com.xyz.caofancpu.d8ger.util.ConstantUtil;
import com.xyz.caofancpu.d8ger.util.VerbalExpressionUtil;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * SwaggerModel model automatic rendering
 *
 * @author caofanCPU
 */
public class SwaggerModelAutoRenderAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        final Editor currentEditor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project currentProject = e.getRequiredData(CommonDataKeys.PROJECT);
        final Document currentDocument = currentEditor.getDocument();

        // Perform a refresh of the current file
        WriteCommandAction.runWriteCommandAction(currentProject, () -> executeSwaggerRender(currentDocument));
    }

    /**
     * Rewrite file
     *
     * @param currentDocument
     */
    public void executeSwaggerRender(@NonNull Document currentDocument) {
        String[] codeLines = currentDocument.getText().split(ConstantUtil.NEXT_LINE);
        List<String> wrapLineList = new ArrayList<>(codeLines.length);
        int i = 0;
        for (String item : codeLines) {
            if (item.contains("@ApiModelProperty(")) {
                if (!item.contains("position")) {
                    item = item.replace(")", ", position = " + (i++) + ")");
                } else {
                    item = VerbalExpressionUtil.regexHandlePositionProperty(item, "position = " + i++);
                }
            }
            wrapLineList.add(item);
        }
        currentDocument.setText(CollectionUtil.join(wrapLineList, ConstantUtil.NEXT_LINE));
    }

}
