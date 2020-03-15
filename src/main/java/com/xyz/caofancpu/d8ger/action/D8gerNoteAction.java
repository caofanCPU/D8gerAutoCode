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
public class D8gerNoteAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Editor currentEditor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project currentProject = e.getRequiredData(CommonDataKeys.PROJECT);
        final Document currentDocument = currentEditor.getDocument();
        List<EasterEggCodeTemplateEnum> contentEnumList = judgeContent(currentDocument);
        if (CollectionUtil.isEmpty(contentEnumList)) {
            if (!currentDocument.getText().contains("Welcome to my github!")) {
                WriteCommandAction.runWriteCommandAction(currentProject, () -> currentDocument.setText(currentDocument.getText() + ConstantUtil.DOUBLE_NEXT_LINE + "Welcome to my github!"));
            }
            BrowserLauncher.getInstance().browse("https://github.com/caofanCPU", WebBrowserManager.getInstance().getFirstActiveBrowser());
            return;
        }
        WriteCommandAction.runWriteCommandAction(currentProject, () -> {
            if (contentEnumList.contains(EasterEggCodeTemplateEnum.NASA_CONFIG_FILE_KEY)) {
                currentDocument.setText(extractNASAContent(currentDocument.getText()));
                return;
            }
            // TODO: do this for a common version later
//            if (contentEnumList.contains(EasterEggCodeTemplateEnum.END_CONFIG_FILE_KEY)) {
//                currentDocument.setText(extractENDContent(currentDocument.getText()));
//                return;
//            }
            currentDocument.setText(CollectionUtil.join(CollectionUtil.transToList(contentEnumList, EasterEggCodeTemplateEnum::getTemplateCode), ConstantUtil.DOUBLE_NEXT_LINE));
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
        if (StringUtils.containsIgnoreCase(text, EasterEggCodeTemplateEnum.END_CONFIG_FILE_KEY.getCodeKey())) {
            contentEnumList.add(EasterEggCodeTemplateEnum.END_CONFIG_FILE_KEY);
        }

        // Attention: put this statement in the last
        if (StringUtils.containsIgnoreCase(text, EasterEggCodeTemplateEnum.NASA_CONFIG_FILE_KEY.getCodeKey())) {
            contentEnumList.clear();
            contentEnumList.add(EasterEggCodeTemplateEnum.NASA_CONFIG_FILE_KEY);
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
            return EasterEggCodeTemplateEnum.NASA_CONFIG_FILE_KEY.getTemplateCode();
        }

        String config = split[0];
        List<String> configStringList = VerbalExpressionUtil.extractMatchContent(config, StringAlignUtil.CONFIG_PARSER_PATTERN);
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
                return EasterEggCodeTemplateEnum.NASA_CONFIG_FILE_KEY.getTemplateCode();
            }
            if (configDetail[0].equalsIgnoreCase(NASAEnum.CONFIG_ITEM_PREFIX_KEY.getKey())) {
                prefix = configDetail[1];
                continue;
            }
            if (configDetail[0].equalsIgnoreCase(NASAEnum.CONFIG_ITEM_SUFFIX_KEY.getKey())) {
                suffix = configDetail[1];
                continue;
            }
            if (configDetail[0].equalsIgnoreCase(NASAEnum.CONFIG_ITEM_ALIGN_STYLE_KEY.getKey())) {
                StringAlignUtil.Alignment tmpAlignStyle = CollectionUtil.findAnyInArrays(StringAlignUtil.Alignment.values(), StringAlignUtil.Alignment::name, configDetail[1]);
                if (Objects.nonNull(tmpAlignStyle)) {
                    alignStyle = tmpAlignStyle;
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
        return config + NASAEnum.NASA_KEY.getKey() + ConstantUtil.NEXT_LINE + ConstantUtil.DOUBLE_NEXT_LINE + result;
    }

    /**
     * Handle multi-lines align and encryption | decryption, like END (HaHa...)
     *
     * @param originText
     * @return
     */
    private String extractENDContent(String originText) {
        String[] split = originText.split(ENDEnum.END_KEY.getKey());

        if (split.length < 2) {
            return EasterEggCodeTemplateEnum.END_CONFIG_FILE_KEY.getTemplateCode();
        }

        String config = split[0];
        List<String> configStringList = VerbalExpressionUtil.extractMatchContent(config, StringAlignUtil.CONFIG_PARSER_PATTERN);
        StringAlignUtil.Alignment alignStyle = StringAlignUtil.Alignment.LEFT;
        StringAlignUtil.Algorithm algorithm = StringAlignUtil.Algorithm.AES;
        StringAlignUtil.ENDOperate endOperate = StringAlignUtil.ENDOperate.ENCRYPTION_AND_DECRYPTION;
        for (String item : configStringList) {
            String[] configDetail = VerbalExpressionUtil.cleanWhiteChar(item)
                    .replaceAll(ENDEnum.CONFIG_PREFIX_KEY.getKey(), ConstantUtil.EMPTY)
                    .replaceAll(ENDEnum.CONFIG_SUFFIX_KEY.getKey(), ConstantUtil.EMPTY)
                    .split(ENDEnum.CONFIG_EQUAL_KEY.getKey());
            if (configDetail.length != 2) {
                return EasterEggCodeTemplateEnum.END_CONFIG_FILE_KEY.getTemplateCode();
            }

            if (configDetail[0].equalsIgnoreCase(ENDEnum.CONFIG_ITEM_ALIGN_STYLE_KEY.getKey())) {
                StringAlignUtil.Alignment tmpAlignStyle = CollectionUtil.findAnyInArrays(StringAlignUtil.Alignment.values(), StringAlignUtil.Alignment::name, configDetail[1]);
                if (Objects.nonNull(tmpAlignStyle)) {
                    alignStyle = tmpAlignStyle;
                }
                continue;
            }
            if (configDetail[0].equalsIgnoreCase(ENDEnum.CONFIG_ITEM_ALGORITHM_TYPE_KEY.getKey())) {
                StringAlignUtil.Algorithm tmpAlgorithm = CollectionUtil.findAnyInArrays(StringAlignUtil.Algorithm.values(), StringAlignUtil.Algorithm::getValue, configDetail[1]);
                if (Objects.nonNull(tmpAlgorithm)) {
                    algorithm = tmpAlgorithm;
                }
                continue;
            }
            if (configDetail[0].equalsIgnoreCase(ENDEnum.CONFIG_ITEM_OPERATE_TYPE_KEY.getKey())) {
                StringAlignUtil.ENDOperate tmpEndOperate = CollectionUtil.findAnyInArrays(StringAlignUtil.ENDOperate.values(), StringAlignUtil.ENDOperate::getValue, configDetail[1]);
                if (Objects.nonNull(tmpEndOperate) && StringAlignUtil.ENDOperate.PINYIN != tmpEndOperate && StringAlignUtil.ENDOperate.ORIGIN != tmpEndOperate) {
                    endOperate = tmpEndOperate;
                }
            }
        }
        String wantedText = split[1];
        String result = StringAlignUtil.formatEND(wantedText, alignStyle, algorithm, endOperate);
        return config + ENDEnum.END_KEY.getKey() + ConstantUtil.NEXT_LINE + ConstantUtil.DOUBLE_NEXT_LINE + result;
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

    private enum ENDEnum {
        END_KEY("@D8ger-END@"),
        CONFIG_PREFIX_KEY("@<"),
        CONFIG_SUFFIX_KEY(">@"),
        CONFIG_EQUAL_KEY("="),
        CONFIG_ITEM_ALIGN_STYLE_KEY("alignStyle"),
        CONFIG_ITEM_ALGORITHM_TYPE_KEY("algorithmType"),
        CONFIG_ITEM_OPERATE_TYPE_KEY("operateType"),

        ;
        private String key;

        ENDEnum(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }
}
