package com.xyz.caofancpu.d8ger.setting;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.xyz.caofancpu.d8ger.util.ConstantUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * D8gerSetting State Service
 *
 * @author D8GER
 */
@State(
        name = "com.xyz.caofancpu.d8ger.setting.D8gerProjectState",
        storages = {@Storage("D8gerAutoCodePluginProjectSetting.xml")}
)
public class D8gerProjectState implements PersistentStateComponent<D8gerProjectState> {
    public boolean moCheck = true;
    public boolean mapperCheck = true;
    public boolean mapperExampleCheck = true;
    public boolean mapperXmlCheck = true;
    public boolean sqlCheck = true;
    public boolean voCheck = false;
    public boolean handlerCheck = false;
    public boolean controllerCheck = false;
    public boolean formatStyleCheck = true;
    public boolean detectSQLTimeColumnCheck = true;
    public boolean mapperBetterThenRepositoryCheck = true;

    public String moPath = ConstantUtil.EMPTY;
    public String mapperPath = ConstantUtil.EMPTY;
    public String mapperExamplePath = ConstantUtil.EMPTY;
    public String mapperXmlPath = ConstantUtil.EMPTY;
    public String sqlPath = ConstantUtil.EMPTY;
    public String voPath = ConstantUtil.EMPTY;
    public String handlerPath = ConstantUtil.EMPTY;
    public String controllerPath = ConstantUtil.EMPTY;
    public String defaultAuthor = ConstantUtil.DEFAULT_AUTHOR;
    public String defaultUrlPrefix = ConstantUtil.DEFAULT_API_URL_PREFIX;
    public String defaultLocale = ConstantUtil.OPTIONAL_CONFIG_LANGUAGE;

    public static D8gerProjectState getInstance(Project currentProject) {
        return ServiceManager.getService(currentProject, D8gerProjectState.class);
    }

    @Nullable
    @Override
    public D8gerProjectState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull D8gerProjectState d8GerProjectState) {
        XmlSerializerUtil.copyBean(d8GerProjectState, this);
    }
}
