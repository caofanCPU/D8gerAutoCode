package com.xyz.caofancpu.d8ger.setting;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

/**
 * D8ger Setting Configurable
 *
 * @author D8GER
 */
public class D8gerSettingsConfigurable implements Configurable {
    private static final Logger LOG = Logger.getInstance(D8gerSettingsConfigurable.class);
    /**
     * User custom config result
     */
    private D8gerSetting d8gerSetting;

    // A default constructor with no arguments is required because this implementation
    // is registered as an applicationConfigurable EP

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "D8gerAutoCode Setting";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return d8gerSetting.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        LOG.error("创建D8ger设置");
        d8gerSetting = new D8gerSetting();
        return d8gerSetting.getPanel();
    }

    @Override
    public boolean isModified() {
        D8gerState settings = D8gerState.getInstance();
        return !Objects.equals(d8gerSetting.getMoPath(), settings.moPath)
                || !Objects.equals(d8gerSetting.getMapperPath(), settings.mapperPath)
                || !Objects.equals(d8gerSetting.getMapperExamplePath(), settings.mapperExamplePath)
                || !Objects.equals(d8gerSetting.getMapperXmlPath(), settings.mapperXmlPath)
                || !Objects.equals(d8gerSetting.getSqlPath(), settings.sqlPath)
                || !Objects.equals(d8gerSetting.getVoPath(), settings.voPath)
                || !Objects.equals(d8gerSetting.getHandlerPath(), settings.handlerPath)
                || !Objects.equals(d8gerSetting.getControllerPath(), settings.controllerPath)
                || !Objects.equals(d8gerSetting.getDefaultAuthor(), settings.defaultAuthor)
                || !Objects.equals(d8gerSetting.getDefaultUrlPrefix(), settings.defaultUrlPrefix)
                || !Objects.equals(d8gerSetting.getDefaultLocale(), settings.defaultLocale)

                || !Objects.equals(d8gerSetting.getMoCheck(), settings.moCheck)
                || !Objects.equals(d8gerSetting.getMapperCheck(), settings.mapperCheck)
                || !Objects.equals(d8gerSetting.getMapperExampleCheck(), settings.mapperExampleCheck)
                || !Objects.equals(d8gerSetting.getMapperXmlCheck(), settings.mapperXmlCheck)
                || !Objects.equals(d8gerSetting.getSqlCheck(), settings.sqlCheck)
                || !Objects.equals(d8gerSetting.getVoCheck(), settings.voCheck)
                || !Objects.equals(d8gerSetting.getHandlerCheck(), settings.handlerCheck)
                || !Objects.equals(d8gerSetting.getControllerCheck(), settings.controllerCheck)
                || !Objects.equals(d8gerSetting.getFormatStyleCheck(), settings.formatStyleCheck)
                || !Objects.equals(d8gerSetting.getDetectSQLTimeColumnCheck(), settings.detectSQLTimeColumnCheck)
                || !Objects.equals(d8gerSetting.getMapperBetterThenRepositoryCheck(), settings.mapperBetterThenRepositoryCheck)
                ;
    }

    @Override
    public void apply() {
        D8gerState settings = D8gerState.getInstance();
        settings.moCheck = d8gerSetting.getMoCheck();
        settings.mapperCheck = d8gerSetting.getMapperCheck();
        settings.mapperExampleCheck = d8gerSetting.getMapperExampleCheck();
        settings.mapperXmlCheck = d8gerSetting.getMapperXmlCheck();
        settings.sqlCheck = d8gerSetting.getSqlCheck();
        settings.voCheck = d8gerSetting.getVoCheck();
        settings.handlerCheck = d8gerSetting.getHandlerCheck();
        settings.controllerCheck = d8gerSetting.getControllerCheck();
        settings.formatStyleCheck = d8gerSetting.getFormatStyleCheck();
        settings.detectSQLTimeColumnCheck = d8gerSetting.getDetectSQLTimeColumnCheck();
        settings.mapperBetterThenRepositoryCheck = d8gerSetting.getMapperBetterThenRepositoryCheck();

        settings.moPath = d8gerSetting.getMoPath();
        settings.mapperPath = d8gerSetting.getMapperPath();
        settings.mapperExamplePath = d8gerSetting.getMapperExamplePath();
        settings.mapperXmlPath = d8gerSetting.getMapperXmlPath();
        settings.sqlPath = d8gerSetting.getSqlPath();
        settings.voPath = d8gerSetting.getVoPath();
        settings.handlerPath = d8gerSetting.getHandlerPath();
        settings.controllerPath = d8gerSetting.getControllerPath();
        settings.defaultAuthor = d8gerSetting.getDefaultAuthor();
        settings.defaultUrlPrefix = d8gerSetting.getDefaultUrlPrefix();
        settings.defaultLocale = d8gerSetting.getDefaultLocale();
    }

    @Override
    public void reset() {
        D8gerState settings = D8gerState.getInstance();
        d8gerSetting.setMoCheck(settings.moCheck);
        d8gerSetting.setMapperCheck(settings.mapperCheck);
        d8gerSetting.setMapperExampleCheck(settings.mapperExampleCheck);
        d8gerSetting.setMapperXmlCheck(settings.mapperXmlCheck);
        d8gerSetting.setSqlCheck(settings.sqlCheck);
        d8gerSetting.setVoCheck(settings.voCheck);
        d8gerSetting.setHandlerCheck(settings.handlerCheck);
        d8gerSetting.setControllerCheck(settings.controllerCheck);
        d8gerSetting.setFormatStyleCheck(settings.formatStyleCheck);
        d8gerSetting.setDetectSQLTimeColumnCheck(settings.detectSQLTimeColumnCheck);
        d8gerSetting.setMapperBetterThenRepositoryCheck(settings.mapperBetterThenRepositoryCheck);

        d8gerSetting.setMoPath(settings.moPath);
        d8gerSetting.setMapperPath(settings.mapperPath);
        d8gerSetting.setMapperExamplePath(settings.mapperExamplePath);
        d8gerSetting.setMapperXmlPath(settings.mapperXmlPath);
        d8gerSetting.setSqlPath(settings.sqlPath);
        d8gerSetting.setVoPath(settings.voPath);
        d8gerSetting.setHandlerPath(settings.handlerPath);
        d8gerSetting.setControllerPath(settings.controllerPath);
        d8gerSetting.setDefaultAuthor(settings.defaultAuthor);
        d8gerSetting.setDefaultUrlPrefix(settings.defaultUrlPrefix);
        d8gerSetting.setDefaultLocale(settings.defaultLocale);
    }

    @Override
    public void disposeUIResources() {
        LOG.error("销毁D8GER-UI设置");
        d8gerSetting = null;
    }

}
