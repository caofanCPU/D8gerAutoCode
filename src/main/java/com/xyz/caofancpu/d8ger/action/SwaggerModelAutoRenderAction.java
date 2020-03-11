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
        // split lines by \n | \r\n
        String[] codeLines = currentDocument.getText().split("(?:\\n|(?:\\r\\n))");
        List<String> wrapLineList = new ArrayList<>(codeLines.length);
        int apiModelPropertyCounter = 0;
        int apiOperationSupportCounter = 0;
        for (String item : codeLines) {
            // case 1
            if (item.contains("@ApiModelProperty(")) {
                if (!item.contains("position")) {
                    String replacer = (item.contains("()") ? "position = " : ", position = ") + (++apiModelPropertyCounter) + ")";
                    item = item.replace(")", replacer);
                } else {
                    item = VerbalExpressionUtil.regexHandleSwaggerModelProperty(item, "position = " + (++apiModelPropertyCounter));
                }
            }

            // case 2
            if (item.contains("@ApiOperationSupport(")) {
                if (!item.contains("order")) {
                    String replacer = (item.contains("()") ? "order = " : ", order = ") + (++apiOperationSupportCounter) + ")";
                    item = item.replace(")", replacer);
                } else {
                    item = VerbalExpressionUtil.regexHandleSwaggerModelProperty(item, "order = " + (++apiOperationSupportCounter));
                }
            }
            wrapLineList.add(item);
        }
        boolean isWindowsLinebreak = currentDocument.getText().contains(ConstantUtil.WINDOWS_NEXT_LINE);
        currentDocument.setText(CollectionUtil.join(wrapLineList, isWindowsLinebreak ? ConstantUtil.WINDOWS_NEXT_LINE : ConstantUtil.NEXT_LINE));
    }

}
