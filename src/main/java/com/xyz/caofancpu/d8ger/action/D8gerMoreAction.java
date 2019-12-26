package com.xyz.caofancpu.d8ger.action;

import com.intellij.ide.browsers.BrowserLauncher;
import com.intellij.ide.browsers.WebBrowserManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 *
 * @author caofanCPU
 */
public class D8gerMoreAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        BrowserLauncher.getInstance().browse("https://github.com/caofanCPU", WebBrowserManager.getInstance().getFirstActiveBrowser());
    }
}
