package com.xyz.caofancpu.d8ger.core;

/**
 * 模板关键字枚举
 *
 * @author caofanCPU
 */
public enum EasterEggCodeTemplateEnum {
    D8GER_CONFIG_FILE_KEY("D8GER", AutoCodeTemplate.TEMPLATE_D8GER),

    ;


    private String codeKey;

    private String templateCode;

    EasterEggCodeTemplateEnum(String codeKey, String templateCode) {
        this.codeKey = codeKey;
        this.templateCode = templateCode;
    }

    public String getCodeKey() {
        return codeKey;
    }

    public String getTemplateCode() {
        return templateCode;
    }
}
