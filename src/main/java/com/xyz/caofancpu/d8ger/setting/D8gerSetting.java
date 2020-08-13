package com.xyz.caofancpu.d8ger.setting;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Setting Form
 *
 * @author D8GER
 */
public class D8gerSetting {
    /**
     * Root panel
     */
    private JPanel rootPanel;

    /**
     * L1
     */
    private JCheckBox moCheck;
    /**
     * L2
     */
    private JCheckBox mapperCheck;
    /**
     * L3
     */
    private JCheckBox mapperExampleCheck;
    /**
     * L4
     */
    private JCheckBox mapperXmlCheck;
    /**
     * L5
     */
    private JCheckBox sqlCheck;
    /**
     * L6
     */
    private JCheckBox voCheck;
    /**
     * L7
     */
    private JCheckBox handlerCheck;
    /**
     * L8
     */
    private JCheckBox controllerCheck;
    /**
     * L9
     */
    private JCheckBox formatStyleCheck;
    /**
     * L10
     */
    private JCheckBox detectSQLTimeColumnCheck;
    /**
     * L11
     */
    private JCheckBox mapperBetterThenRepositoryCheck;
    /**
     * L12
     */
    private JLabel author;
    /**
     * L13
     */
    private JLabel apiUrlPrefix;
    /**
     * L14
     */
    private JLabel locale;

    /**
     * R1
     */
    private TextFieldWithBrowseButton moPath;
    /**
     * R2
     */
    private TextFieldWithBrowseButton mapperPath;
    /**
     * R3
     */
    private TextFieldWithBrowseButton mapperExamplePath;
    /**
     * R4
     */
    private TextFieldWithBrowseButton mapperXmlPath;
    /**
     * R5
     */
    private TextFieldWithBrowseButton sqlPath;
    /**
     * R6
     */
    private TextFieldWithBrowseButton voPath;
    /**
     * R7
     */
    private TextFieldWithBrowseButton handlerPath;
    /**
     * R8
     */
    private TextFieldWithBrowseButton controllerPath;
    /**
     * R12
     */
    private JTextField defaultAuthor;
    /**
     * R13
     */
    private JTextField defaultUrlPrefix;
    /**
     * R14
     */
    private JComboBox<String> defaultLocale;
    /**
     * Donate link button
     */
    private JButton donateButton;

    public D8gerSetting() {
        initDonate();
        initTbbListener();
    }

    private void initTbbListener() {
        moPath.addBrowseFolderListener("Please Select a Directory", null, null, FileChooserDescriptorFactory.createSingleFolderDescriptor());
        mapperPath.addBrowseFolderListener(new TextBrowseFolderListener(new FileChooserDescriptor(false, true, false, false, false, false)));
        mapperExamplePath.addBrowseFolderListener(new TextBrowseFolderListener(new FileChooserDescriptor(false, true, false, false, false, false)));
        mapperXmlPath.addBrowseFolderListener(new TextBrowseFolderListener(new FileChooserDescriptor(false, true, false, false, false, false)));
        sqlPath.addBrowseFolderListener(new TextBrowseFolderListener(new FileChooserDescriptor(false, true, false, false, false, false)));
        voPath.addBrowseFolderListener(new TextBrowseFolderListener(new FileChooserDescriptor(false, true, false, false, false, false)));
        handlerPath.addBrowseFolderListener(new TextBrowseFolderListener(new FileChooserDescriptor(false, true, false, false, false, false)));
        controllerPath.addBrowseFolderListener(new TextBrowseFolderListener(new FileChooserDescriptor(false, true, false, false, false, false)));
    }

    private void initDonate() {
        donateButton.setBorder(null);
        donateButton.setMargin(JBUI.emptyInsets());
        donateButton.setContentAreaFilled(false);
        donateButton.addActionListener(e -> BrowserUtil.browse("http://www.debuggerpowerzcy.top/home/2020/03/14/D8gerAutoCode%E6%8F%92%E4%BB%B6%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97/"));
        donateButton.putClientProperty("JButton.backgroundColor", rootPanel.getBackground());
    }

    public JPanel getPanel() {
        return rootPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return defaultAuthor;
    }

    public boolean getMoCheck() {
        return moCheck.isSelected();
    }

    public void setMoCheck(boolean newStatus) {
        moCheck.setSelected(newStatus);
    }

    public boolean getMapperCheck() {
        return mapperCheck.isSelected();
    }

    public void setMapperCheck(boolean newStatus) {
        mapperCheck.setSelected(newStatus);
    }

    public boolean getMapperExampleCheck() {
        return mapperExampleCheck.isSelected();
    }

    public void setMapperExampleCheck(boolean newStatus) {
        mapperExampleCheck.setSelected(newStatus);
    }

    public boolean getMapperXmlCheck() {
        return mapperXmlCheck.isSelected();
    }

    public void setMapperXmlCheck(boolean newStatus) {
        mapperXmlCheck.setSelected(newStatus);
    }

    public boolean getSqlCheck() {
        return sqlCheck.isSelected();
    }

    public void setSqlCheck(boolean newStatus) {
        sqlCheck.setSelected(newStatus);
    }

    public boolean getVoCheck() {
        return voCheck.isSelected();
    }

    public void setVoCheck(boolean newStatus) {
        voCheck.setSelected(newStatus);
    }

    public boolean getHandlerCheck() {
        return handlerCheck.isSelected();
    }

    public void setHandlerCheck(boolean newStatus) {
        handlerCheck.setSelected(newStatus);
    }

    public boolean getControllerCheck() {
        return controllerCheck.isSelected();
    }

    public void setControllerCheck(boolean newStatus) {
        controllerCheck.setSelected(newStatus);
    }

    public boolean getFormatStyleCheck() {
        return formatStyleCheck.isSelected();
    }

    public void setFormatStyleCheck(boolean newStatus) {
        formatStyleCheck.setSelected(newStatus);
    }

    public boolean getDetectSQLTimeColumnCheck() {
        return detectSQLTimeColumnCheck.isSelected();
    }

    public void setDetectSQLTimeColumnCheck(boolean newStatus) {
        detectSQLTimeColumnCheck.setSelected(newStatus);
    }

    public boolean getMapperBetterThenRepositoryCheck() {
        return mapperBetterThenRepositoryCheck.isSelected();
    }

    public void setMapperBetterThenRepositoryCheck(boolean newStatus) {
        mapperBetterThenRepositoryCheck.setSelected(newStatus);
    }

    @NotNull
    public String getMoPath() {
        return moPath.getText();
    }

    public void setMoPath(@NotNull String newText) {
        moPath.setText(newText);
    }

    @NotNull
    public String getMapperPath() {
        return mapperPath.getText();
    }

    public void setMapperPath(@NotNull String newText) {
        mapperPath.setText(newText);
    }

    @NotNull
    public String getMapperExamplePath() {
        return mapperExamplePath.getText();
    }

    public void setMapperExamplePath(@NotNull String newText) {
        mapperExamplePath.setText(newText);
    }

    @NotNull
    public String getMapperXmlPath() {
        return mapperXmlPath.getText();
    }

    public void setMapperXmlPath(@NotNull String newText) {
        mapperXmlPath.setText(newText);
    }

    @NotNull
    public String getSqlPath() {
        return sqlPath.getText();
    }

    public void setSqlPath(@NotNull String newText) {
        sqlPath.setText(newText);
    }

    @NotNull
    public String getVoPath() {
        return voPath.getText();
    }

    public void setVoPath(@NotNull String newText) {
        voPath.setText(newText);
    }

    @NotNull
    public String getHandlerPath() {
        return handlerPath.getText();
    }

    public void setHandlerPath(@NotNull String newText) {
        handlerPath.setText(newText);
    }

    @NotNull
    public String getControllerPath() {
        return controllerPath.getText();
    }

    public void setControllerPath(@NotNull String newText) {
        controllerPath.setText(newText);
    }

    @NotNull
    public String getDefaultAuthor() {
        return defaultAuthor.getText();
    }

    public void setDefaultAuthor(@NotNull String newText) {
        defaultAuthor.setText(newText);
    }

    @NotNull
    public String getDefaultUrlPrefix() {
        return defaultUrlPrefix.getText();
    }

    public void setDefaultUrlPrefix(@NotNull String newText) {
        defaultUrlPrefix.setText(newText);
    }

    @NotNull
    public String getDefaultLocale() {
        return defaultLocale.getModel().getSelectedItem().toString();
    }

    public void setDefaultLocale(@NotNull String newText) {
        defaultLocale.getModel().setSelectedItem(newText);
    }
}
