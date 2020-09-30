package com.xyz.caofancpu.d8ger.core;

/**
 * EasterEgg code template keyword enumeration
 *
 * @author caofanCPU
 */
public enum EasterEggCodeTemplateEnum {
    D8GER_CONFIG_FILE_KEY("D8GER", AutoCodeTemplate.TEMPLATE_D8GER),
    REGEX_CONFIG_FILE_KEY("REGEX", AutoCodeTemplate.TEMPLATE_REGEX),
    OH_MY_ZSH_CONFIG_FILE_KEY("OHMYZSH", AutoCodeTemplate.TEMPLATE_OH_MY_ZSH),
    END_CONFIG_FILE_KEY("END", AutoCodeTemplate.TEMPLATE_END),


    NASA_CONFIG_FILE_KEY("NASA", AutoCodeTemplate.TEMPLATE_NASA),
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
