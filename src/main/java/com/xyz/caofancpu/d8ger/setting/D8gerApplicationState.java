package com.xyz.caofancpu.d8ger.setting;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * D8gerSetting State Service
 *
 * @author D8GER
 */
@State(
        name = "com.xyz.caofancpu.d8ger.setting.D8gerApplicationState",
        storages = {@Storage("D8gerAutoCodePluginGlobalSetting.xml")}
)
public class D8gerApplicationState implements PersistentStateComponent<D8gerApplicationState> {
    public String version = "1.0";

    public static D8gerApplicationState getInstance() {
        return ServiceManager.getService(D8gerApplicationState.class);
    }

    public static String getOldVersion() {
        return getInstance().version;
    }

    public static void setPluginVersion(String version) {
        getInstance().setVersion(version);
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Nullable
    @Override
    public D8gerApplicationState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull D8gerApplicationState d8gerState) {
        XmlSerializerUtil.copyBean(d8gerState, this);
    }
}
