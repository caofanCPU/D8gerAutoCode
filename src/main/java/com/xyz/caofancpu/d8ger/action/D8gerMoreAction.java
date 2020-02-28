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
import com.xyz.caofancpu.d8ger.util.StringAlignUtil;
import com.xyz.caofancpu.d8ger.util.VerbalExpressionUtil;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        List<EasterEggCodeTemplateEnum> contentEnumList = judgeContent(currentDocument);
        if (CollectionUtil.isEmpty(contentEnumList)) {
            WriteCommandAction.runWriteCommandAction(currentProject, () -> currentDocument.setText(currentDocument.getText() + "Welcome to my github!"));
            BrowserLauncher.getInstance().browse("https://github.com/caofanCPU", WebBrowserManager.getInstance().getFirstActiveBrowser());
            return;
        }
        WriteCommandAction.runWriteCommandAction(currentProject, () -> {
            if (contentEnumList.contains(EasterEggCodeTemplateEnum.NASA_CONFIG_FILE_KEY)) {
                currentDocument.setText(extractNASAContent(currentDocument.getText()));
            } else {
                currentDocument.setText(CollectionUtil.join(CollectionUtil.transToList(contentEnumList, EasterEggCodeTemplateEnum::getTemplateCode), ConstantUtil.DOUBLE_NEXT_LINE));
            }
        });
    }

    /**
     * Judge which template to be used
     *
     * @param currentDocument
     * @return
     */
    private List<EasterEggCodeTemplateEnum> judgeContent(@NonNull Document currentDocument) {
        List<EasterEggCodeTemplateEnum> contentEnumList = new ArrayList<>(8);
        String text = currentDocument.getText();
        if (StringUtils.containsIgnoreCase(text, EasterEggCodeTemplateEnum.D8GER_CONFIG_FILE_KEY.getCodeKey())) {
            contentEnumList.add(EasterEggCodeTemplateEnum.D8GER_CONFIG_FILE_KEY);
        }
        if (StringUtils.containsIgnoreCase(text, EasterEggCodeTemplateEnum.REGEX_CONFIG_FILE_KEY.getCodeKey())) {
            contentEnumList.add(EasterEggCodeTemplateEnum.REGEX_CONFIG_FILE_KEY);
        }

        // Attention: put this statement in the last
        if (StringUtils.containsIgnoreCase(text, EasterEggCodeTemplateEnum.NASA_CONFIG_FILE_KEY.getCodeKey())) {
            contentEnumList.clear();
            contentEnumList.add(EasterEggCodeTemplateEnum.REGEX_CONFIG_FILE_KEY);
        }
        return contentEnumList;
    }

    /**
     * Handle multi-lines align, like NASA (HaHa...)
     *
     * @param originText
     * @return
     */
    private String extractNASAContent(@NonNull String originText) {
        String[] split = originText.split(NASAEnum.NASA_KEY.getKey());

        if (split.length < 2) {
            return EasterEggCodeTemplateEnum.REGEX_CONFIG_FILE_KEY.getTemplateCode();
        }

        String config = split[0];
        List<String> configStringList = VerbalExpressionUtil.extractMatchContent(config, StringAlignUtil.NASA_CONFIG_PATTERN);
        String prefix = ConstantUtil.EMPTY;
        String suffix = ConstantUtil.EMPTY;
        StringAlignUtil.Alignment alignStyle = StringAlignUtil.Alignment.LEFT;
        boolean formatSQL = false;
        boolean formatAsCamel = false;
        for (String item : configStringList) {
            String[] configDetail = VerbalExpressionUtil.cleanWhiteChar(item)
                    .replaceAll(NASAEnum.CONFIG_PREFIX_KEY.getKey(), ConstantUtil.EMPTY)
                    .replaceAll(NASAEnum.CONFIG_SUFFIX_KEY.getKey(), ConstantUtil.EMPTY)
                    .split(NASAEnum.CONFIG_EQUAL_KEY.getKey());
            if (configDetail.length != 2) {
                return EasterEggCodeTemplateEnum.REGEX_CONFIG_FILE_KEY.getTemplateCode();
            }
            if (configDetail[0].equalsIgnoreCase(NASAEnum.CONFIG_ITEM_PREFIX_KEY.getKey())) {
                prefix = configDetail[1];
                continue;
            }
            if (configDetail[0].equalsIgnoreCase(NASAEnum.CONFIG_ITEM_SUFFIX_KEY.getKey())) {
                suffix = configDetail[1];
                continue;
            }
            if (configDetail[0].equalsIgnoreCase(NASAEnum.CONFIG_ITEM_FORMAT_AS_CAMEL_KEY.getKey())) {
                alignStyle = StringAlignUtil.Alignment.fromName(configDetail[1]);
                if (Objects.isNull(alignStyle)) {
                    alignStyle = StringAlignUtil.Alignment.LEFT;
                }
                continue;
            }
            if (configDetail[0].equalsIgnoreCase(NASAEnum.CONFIG_ITEM_FORMAT_SQL_KEY.getKey()) && Boolean.parseBoolean(configDetail[1])) {
                formatSQL = true;
                continue;
            }
            if (configDetail[0].equalsIgnoreCase(NASAEnum.CONFIG_ITEM_FORMAT_AS_CAMEL_KEY.getKey()) && Boolean.parseBoolean(configDetail[1])) {
                formatAsCamel = true;
            }

            // formatAsCamel depends on formatSQL
            if (formatAsCamel && !formatSQL) {
                formatAsCamel = false;
            }
        }
        String wantedText = split[1];
        String result = StringAlignUtil.formatSQLColumn(wantedText, alignStyle, prefix, suffix, formatSQL, formatAsCamel);
        return config + ConstantUtil.DOUBLE_NEXT_LINE + ConstantUtil.DOUBLE_NEXT_LINE + result;
    }

    private enum NASAEnum {
        NASA_KEY("@D8ger-ALIGN@"),
        CONFIG_PREFIX_KEY("@<"),
        CONFIG_SUFFIX_KEY(">@"),
        CONFIG_EQUAL_KEY("="),
        CONFIG_ITEM_PREFIX_KEY("prefix"),
        CONFIG_ITEM_SUFFIX_KEY("suffix"),
        CONFIG_ITEM_ALIGN_STYLE_KEY("alignStyle"),
        CONFIG_ITEM_FORMAT_SQL_KEY("formatSQL"),
        CONFIG_ITEM_FORMAT_AS_CAMEL_KEY("formatAsCamel"),

        ;
        private String key;

        NASAEnum(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }
}
