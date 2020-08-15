package com.xyz.caofancpu.d8ger.activity;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.util.text.VersionComparatorUtil;
import com.xyz.caofancpu.d8ger.setting.D8gerApplicationState;
import com.xyz.caofancpu.d8ger.util.ConstantUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Start up and update activity, show some message!
 *
 * @author D8GER
 */
public class D8gerActivity implements StartupActivity, DumbAware {

    @Override
    public void runActivity(@NotNull Project project) {
        if (ApplicationManager.getApplication().isUnitTestMode()) {
            return;
        }
        IdeaPluginDescriptor plugin = PluginManagerCore.getPlugin(PluginId.getId(ConstantUtil.PLUGIN_ID));
        if (Objects.nonNull(plugin)) {
            String installedVersion = D8gerApplicationState.getOldVersion();
            int compare = VersionComparatorUtil.compare(installedVersion, plugin.getVersion());
            if (compare < 0) {
                ApplicationManager.getApplication()
                        .invokeLater(() -> Notifications.Bus.notify(
                                new Notification(ConstantUtil.NOTIFICATION_GROUP_VIEW_ID,
                                        ConstantUtil.RENDER_TITLE,
                                        "If you like D8gerAutoCode, please star the project <a href='https://github.com/caofanCPU/D8gerAutoCode'>D8gerAutoCode</a>",
                                        NotificationType.INFORMATION
                                ))
                        );
                D8gerApplicationState.setPluginVersion(plugin.getVersion());
            }
        }
    }
}
