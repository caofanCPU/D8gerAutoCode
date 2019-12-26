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
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SwaggerModel对象自动渲染
 *
 * @author caofanCPU
 */
public class SwaggerModelAutoRenderAction extends AnAction {

    /**
     * Swagger字段位置顺序正则匹配表达式
     */
    private static final Pattern SWAGGER_POSITION_PATTERN = Pattern.compile("((?:position)(?:\\s)*(?:\\=)(?:\\s)*(?:\\d)*)");

    /**
     * 正则替换
     *
     * @param originString
     * @param positionOrder
     * @return
     */
    private static String regexHandlePositionProperty(String originString, int positionOrder) {
        Matcher matcher = SWAGGER_POSITION_PATTERN.matcher(originString);
        return matcher.replaceAll("position = " + positionOrder);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Editor currentEditor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project currentProject = e.getRequiredData(CommonDataKeys.PROJECT);
        final Document currentDocument = currentEditor.getDocument();

        // 执行创建目录|文件
        WriteCommandAction.runWriteCommandAction(currentProject, () -> executeSwaggerRender(currentDocument));
    }

    /**
     * 重写文件
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
                    item = regexHandlePositionProperty(item, i++);
                }
            }
            wrapLineList.add(item);
        }
        currentDocument.setText(CollectionUtil.join(wrapLineList, ConstantUtil.NEXT_LINE));
    }

}
