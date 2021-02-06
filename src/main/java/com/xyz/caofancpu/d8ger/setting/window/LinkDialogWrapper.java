package com.xyz.caofancpu.d8ger.setting.window;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;
import com.xyz.caofancpu.d8ger.util.QRCodeUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Link Window
 *
 * @author D8GER
 */
public class LinkDialogWrapper extends DialogWrapper {
    private static final Logger LOG = Logger.getInstance(LinkDialogWrapper.class);
    private final DialogPanel dialogPanel;

    public LinkDialogWrapper(@Nullable Project project, Pair<String, String> linkPair) {
        super(project, false);
        dialogPanel = new DialogPanel(linkPair.getLeft(), linkPair.getRight());
        setResizable(false);
        setTitle("D8ger•帝八哥");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return dialogPanel;
    }

    public static class DialogPanel extends JPanel {
        public DialogPanel(String url, String title) {
            BufferedImage image;
            try {
                image = QRCodeUtil.createImage(url, null, true);
                JLabel label = new JLabel();
                // config image CENTER
                ImageIcon imageIcon = new ImageIcon(image);
                label.setIcon(imageIcon);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                label.setAlignmentY(JLabel.CENTER_ALIGNMENT);
                // config image text CENTER & BOTTOM
                label.setText("<html><u>" + title + "</u></html>");
                label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
                label.setForeground(JBColor.BLUE);
                label.setVerticalTextPosition(JLabel.BOTTOM);
                label.setHorizontalTextPosition(JLabel.CENTER);
                label.setSize(310, 310);
                // config mouse click action
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        BrowserUtil.browse(url);
                    }
                });
                label.setBorder(JBUI.Borders.empty(30, 50));
                add(label);
            } catch (Exception e) {
                LOG.error("Init url QR code failed, try again!");
            }
        }
    }
}
