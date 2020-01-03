package com.xyz.caofancpu.d8ger.test;

import lombok.Getter;

/**
 * YesNo枚举
 */
public enum YesNoEnum {

    YES(1, "是"),
    NO(1, "否");

    @Getter
    private Integer value;
    @Getter
    private String name;

    YesNoEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
