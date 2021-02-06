package com.xyz.caofancpu.d8ger.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.xyz.caofancpu.d8ger.core.LinkUrlEnum;
import com.xyz.caofancpu.d8ger.setting.window.LinkDialogWrapper;
import com.xyz.caofancpu.d8ger.util.CollectionUtil;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Objects;

/**
 * Link wonderful articles
 *
 * @author D8GER
 */
public class LinkAction extends AnAction {

    @Override
    public synchronized void actionPerformed(@NonNull final AnActionEvent e) {
        final Editor currentEditor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project currentProject = e.getRequiredData(CommonDataKeys.PROJECT);
        final Document currentDocument = currentEditor.getDocument();

        // Perform a refresh of the current file
        WriteCommandAction.runWriteCommandAction(currentProject, () -> handleLink(currentProject, currentDocument));

    }

    private void handleLink(Project currentProject, Document currentDocument) {
        String sectionText = currentDocument.getText();
        if (StringUtils.isBlank(sectionText)) {
            currentDocument.setText(LinkUrlEnum.loadHelpInfo());
            return;
        }
        Pair<String, String> linkPair = null;
        if (sectionText.contains(LinkUrlEnum.QR_CODE.getTopic())) {
            String[] split = sectionText.split(LinkUrlEnum.QR_CODE.getTopic());
            if (split.length < 2) {
                currentDocument.setText(LinkUrlEnum.loadHelpInfo());
                return;
            }
            linkPair = Pair.of(split[0], split[1]);
        } else {
            LinkUrlEnum match = CollectionUtil.findAnyInArrays(LinkUrlEnum.values(), LinkUrlEnum::getTopic, sectionText);
            if (Objects.nonNull(match) && !Objects.equals(match, LinkUrlEnum.QR_CODE)) {
                linkPair = Pair.of(match.getTopic(), match.getUrl());
            }
        }
        if (Objects.nonNull(linkPair)) {
            LinkDialogWrapper linkDialogWrapper = new LinkDialogWrapper(currentProject, linkPair);
            linkDialogWrapper.show();
        }

    }

}
