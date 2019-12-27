package com.xyz.caofancpu.d8ger.core;

import com.xyz.caofancpu.d8ger.util.CollectionUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * 支持Mo字段类型
 *
 * @author caofanCPU
 */
public enum SupportFieldTypeEnum {
    INTEGER("java.lang.Integer", "Integer", "int(11)"),
    BASIC_INT("int", "int", "int(20)"),
    LONG("java.lang.Long", "Long", "bigint(20)"),
    BASIC_LONG("long", "long", "bigint(20)"),
    STRING("java.lang.String", "String", "varchar(32)"),
    BOOLEAN("java.lang.Boolean", "Boolean", "tinyint(1)"),
    DATE("java.util.Date", "Date", "datetime"),
    LOCAL_DATE_TIME("java.time.LocalDateTime", "LocalDateTime", "datetime"),
    BIG_DECIMAL("java.math.BigDecimal", "BigDecimal", "double(8, 2)"),
    DOUBLE("java.lang.Double", "Double", "double(8, 2)"),
    FLOAT("java.lang.Float", "Float", "double(8, 2)"),
    SHORT("java.lang.Short", "Short", "int(4)"),
    BASIC_DOUBLE("double", "double", "double(8, 2)"),
    BASIC_FLOAT("float", "float", "double(8, 2)"),
    BASIC_SHORT("short", "short", "int(4)"),
    BASIC_BOOLEAN("boolean", "boolean", "tinyint(1)"),
    ENUM("java.lang.Enum", null, "int(4)"),
    NONE(null, null, null);

    private String originName;

    private String shortName;

    private String sqlName;

    SupportFieldTypeEnum(String originName, String shortName, String sqlName) {
        this.originName = originName;
        this.shortName = shortName;
        this.sqlName = sqlName;
    }

    public static SupportFieldTypeEnum positionByShortName(String shortName) {
        List<SupportFieldTypeEnum> noneNullEnumList = CollectionUtil.removeAndTransList(Arrays.asList(SupportFieldTypeEnum.values()),
                item -> item == SupportFieldTypeEnum.NONE || item == SupportFieldTypeEnum.ENUM,
                Function.identity()
        );
        SupportFieldTypeEnum result = CollectionUtil.findFirst(noneNullEnumList, item -> item.getShortName().equals(shortName));
        if (Objects.nonNull(result)) {
            return result;
        }
        return SupportFieldTypeEnum.NONE;
    }

    public String getOriginName() {
        return originName;
    }

    public String getShortName() {
        return shortName;
    }

    public SupportFieldTypeEnum setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public String getSqlName() {
        return sqlName;
    }
}
