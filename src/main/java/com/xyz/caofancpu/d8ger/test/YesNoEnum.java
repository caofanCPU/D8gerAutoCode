package com.xyz.caofancpu.d8ger.test;

import lombok.Getter;

/**
 * YesNo Enum class
 */
public enum YesNoEnum {

    YES(1, "Yes"),
    NO(1, "No");

    @Getter
    private Integer value;
    @Getter
    private String name;

    YesNoEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
