package com.xyz.caofancpu.d8ger.action;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
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
import com.xyz.caofancpu.d8ger.util.ConstantUtil;
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
        String sectionText = currentDocument.getText();
        boolean showHelp = false;
        if (StringUtils.isBlank(sectionText)) {
            showHelp = true;
        }
        if (sectionText.contains(LinkUrlEnum.QR_CODE.getTopic())) {
            String[] split = sectionText.split(LinkUrlEnum.QR_CODE.getTopic());
            if (split.length < 2) {
                showHelp = true;
            }
        }
        if (showHelp) {
            WriteCommandAction.runWriteCommandAction(currentProject, () -> currentDocument.setText(LinkUrlEnum.loadHelpInfo()));
            return;
        }
        executeLinkQRCodeWindow(currentProject, currentDocument);
    }

    private void executeLinkQRCodeWindow(Project currentProject, Document currentDocument) {
        String keyText = currentDocument.getText();
        Pair<String, String> linkPair = null;
        if (keyText.contains(LinkUrlEnum.QR_CODE.getTopic())) {
            String[] split = keyText.split(LinkUrlEnum.QR_CODE.getTopic());
            linkPair = Pair.of(split[0], split[1]);
        } else {
            LinkUrlEnum match = CollectionUtil.findAnyInArrays(LinkUrlEnum.values(), LinkUrlEnum::getTopic, keyText);
            if (Objects.nonNull(match) && !Objects.equals(match, LinkUrlEnum.QR_CODE)) {
                linkPair = Pair.of(match.getTopic(), match.getUrl());
            }
        }
        if (Objects.isNull(linkPair)) {
            Notifications.Bus.notify(
                    new Notification(ConstantUtil.NOTIFICATION_GROUP_VIEW_ID, "Wow, you need new feature!", "keyword: [" + keyText + "] doesn't support for now", NotificationType.INFORMATION)
            );
            linkPair = Pair.of(LinkUrlEnum.HELP.getTopic(), LinkUrlEnum.HELP.getUrl());
        }
        LinkDialogWrapper linkDialogWrapper = new LinkDialogWrapper(currentProject, linkPair);
        linkDialogWrapper.show();
    }

}
