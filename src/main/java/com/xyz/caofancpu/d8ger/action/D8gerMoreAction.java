package com.xyz.caofancpu.d8ger.action;

import com.intellij.ide.browsers.BrowserLauncher;
import com.intellij.ide.browsers.WebBrowserManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.xyz.caofancpu.d8ger.core.EasterEggCodeTemplateEnum;
import com.xyz.caofancpu.d8ger.util.CollectionUtil;
import com.xyz.caofancpu.d8ger.util.ConstantUtil;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * What encounter to pain me ever belongs to see you now.
 * You can find me anytime 'cause I'm everywhere.
 *
 * @author caofanCPU
 */
public class D8gerMoreAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Editor currentEditor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project currentProject = e.getRequiredData(CommonDataKeys.PROJECT);
        final Document currentDocument = currentEditor.getDocument();
        List<EasterEggCodeTemplateEnum> contentEnumList = exactContent(currentDocument);
        if (CollectionUtil.isEmpty(contentEnumList)) {
            WriteCommandAction.runWriteCommandAction(currentProject, () -> currentDocument.setText(currentDocument.getText() + "Welcome to my github!"));
            BrowserLauncher.getInstance().browse("https://github.com/caofanCPU", WebBrowserManager.getInstance().getFirstActiveBrowser());
            return;
        }
        WriteCommandAction.runWriteCommandAction(currentProject, () -> currentDocument.setText(CollectionUtil.join(CollectionUtil.transToList(contentEnumList, EasterEggCodeTemplateEnum::getTemplateCode), ConstantUtil.DOUBLE_NEXT_LINE)));
    }

    private List<EasterEggCodeTemplateEnum> exactContent(@NonNull Document currentDocument) {
        List<EasterEggCodeTemplateEnum> contentEnumList = new ArrayList<>(8);
        String text = currentDocument.getText();
        if (StringUtils.containsIgnoreCase(text, EasterEggCodeTemplateEnum.D8GER_CONFIG_FILE_KEY.getCodeKey())) {
            contentEnumList.add(EasterEggCodeTemplateEnum.D8GER_CONFIG_FILE_KEY);
        }
        if (StringUtils.containsIgnoreCase(text, EasterEggCodeTemplateEnum.REGEX_CONFIG_FILE_KEY.getCodeKey())) {
            contentEnumList.add(EasterEggCodeTemplateEnum.REGEX_CONFIG_FILE_KEY);
        }
        return contentEnumList;
    }
}
