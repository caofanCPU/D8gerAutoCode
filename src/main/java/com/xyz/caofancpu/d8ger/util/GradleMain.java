package com.xyz.caofancpu.d8ger.util;

import com.xyz.caofancpu.d8ger.core.AutoCodeTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Gradle项目的main函数
 *
 * @author caofanCPU
 */
public class GradleMain {

    public static void main(String[] args)
            throws IOException {
        Map<String, String> bindingMap = new HashMap<>();
        bindingMap.put("@MoName@", "Debugger");
        bindingMap.put("@uncapitallizeMoName@", "debugger");
        bindingMap.put("@d8Author@", "Power+");
        String render = AutoCodeTemplate.render(AutoCodeTemplate.TEMPLATE_MAPPER, bindingMap);

    }

}
