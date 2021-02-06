package com.xyz.caofancpu.d8ger.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.xyz.caofancpu.d8ger.setting.window.LinkDialogWrapper;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Link wonderful articles
 *
 * @author D8GER
 */
public class LinkAction extends AnAction {

    @Override
    public synchronized void actionPerformed(@NonNull final AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            return;
        }
        Pair<String, String> linkPair = Pair.of("https://mp.weixin.qq.com/s/YXH47C4P2Sc1OQblyZlZzg", "Kafka");
        LinkDialogWrapper linkDialogWrapper = new LinkDialogWrapper(project, linkPair);
        linkDialogWrapper.show();
    }

}
