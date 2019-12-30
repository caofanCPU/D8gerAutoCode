package com.xyz.caofancpu.d8ger.core;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.PsiTypeElement;
import com.intellij.psi.impl.source.tree.PsiCommentImpl;
import com.intellij.psi.javadoc.PsiDocComment;
import com.xyz.caofancpu.d8ger.util.ConstantUtil;
import com.xyz.caofancpu.d8ger.util.VerbalExpressionUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * Mo封装对象
 *
 * @author caofanCPU
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MoField {
    /**
     * 字段注释(名称)
     */
    private String comment;

    /**
     * 访问修饰符, 默认private
     */
    private String accessModifier;

    /**
     * 字段原始类型名称
     */
    private String fieldOriginTypeName;

    /**
     * 字段类型简称
     */
    private String fieldTypeShortName;

    /**
     * 字段SQL类型名称
     */
    private String fieldSqlTypeName;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段顺序
     */
    private int index;

    public MoField(@NonNull PsiField field) {
        PsiElement[] children = field.getChildren();
        for (PsiElement child : children) {
            if (child instanceof PsiCommentImpl || child instanceof PsiDocComment) {
                this.comment = VerbalExpressionUtil.extractComment(child.getText()).trim();
                continue;
            }
            if (child instanceof PsiModifierList) {
                this.accessModifier = child.getText();
                continue;
            }
            if (child instanceof PsiTypeElement) {
                if (Objects.nonNull(child.getText()) && StringUtils.containsIgnoreCase(child.getText(), ConstantUtil.ENUM_SUFFIX)) {
                    // 对于名称中包含Enum或者enum的类型, 判定为枚举
                    this.fieldOriginTypeName = SupportFieldTypeEnum.ENUM.getOriginName();
                    this.fieldTypeShortName = child.getText();
                    this.fieldSqlTypeName = SupportFieldTypeEnum.ENUM.getSqlName();
                } else {
                    SupportFieldTypeEnum fieldTypeEnum = SupportFieldTypeEnum.positionByShortName(child.getText());
                    this.fieldOriginTypeName = fieldTypeEnum.getOriginName();
                    this.fieldTypeShortName = fieldTypeEnum.getShortName();
                    this.fieldSqlTypeName = fieldTypeEnum.getSqlName();
                }
                continue;
            }
            if (child instanceof PsiIdentifier) {
                this.name = child.getText();
            }
        }
        if (Objects.isNull(this.comment)) {
            this.comment = ConstantUtil.EMPTY;
        }
        if (Objects.isNull(this.accessModifier)) {
            this.accessModifier = ConstantUtil.DEFAULT_ACCESS_MODIFIER;
        }
    }

    /**
     * SQL列字段类型展示
     *
     * @return
     */
    public String wrapSqlDefaultValueView() {
        if (Objects.isNull(fieldSqlTypeName)) {
            return ConstantUtil.EMPTY;
        }
        if (fieldSqlTypeName.contains("int")) {
            return "default 0 null";
        }
        if (fieldSqlTypeName.contains("double")) {
            return "default 0.00 null";
        }
        if (fieldSqlTypeName.contains("datetime")) {
            return name.equals(ConstantUtil.SQL_UPDATE_TIME) ? "default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP" : "default CURRENT_TIMESTAMP null";
        }
        return "default '' null";
    }

    @Override
    public String toString() {
        return ConstantUtil.TAB + "/**\n"
                + ConstantUtil.TAB + ConstantUtil.SPACE + "*" + ConstantUtil.SPACE + comment + "\n"
                + ConstantUtil.TAB + ConstantUtil.SPACE + "*/" + "\n"
                + ConstantUtil.TAB + accessModifier + ConstantUtil.SPACE + fieldTypeShortName + ConstantUtil.SPACE + name + ConstantUtil.ENGLISH_SEMICOLON;
    }

    /**
     * Swagger字段
     *
     * @return
     */
    public String toSwaggerString() {
        return ConstantUtil.TAB + "@ApiModelProperty(value = \"" + comment + "\", required = false, example = \"\", position = " + index + ")" + ConstantUtil.NEXT_LINE
                + ConstantUtil.TAB + accessModifier + ConstantUtil.SPACE + fieldTypeShortName + ConstantUtil.SPACE + name + ConstantUtil.ENGLISH_SEMICOLON;
    }

    /**
     * SQL列定义字段
     *
     * @return
     */
    public String toSqlColumnDefinitionString() {
        if (name.equals(ConstantUtil.SQL_ID)) {
            return ConstantUtil.TAB + ConstantUtil.SQL_ID + ConstantUtil.SPACE + fieldSqlTypeName + ConstantUtil.SPACE + "unsigned auto_increment" + ConstantUtil.SPACE + "comment" + ConstantUtil.SPACE + "'" + comment + "'" + ConstantUtil.SPACE + "primary key" + ConstantUtil.ENGLISH_COMMA;
        }

        return ConstantUtil.TAB + VerbalExpressionUtil.sqlUnderLineName(name) + ConstantUtil.SPACE + fieldSqlTypeName + ConstantUtil.SPACE + wrapSqlDefaultValueView() + ConstantUtil.SPACE + "comment" + ConstantUtil.SPACE + "'" + comment + "'" + ConstantUtil.ENGLISH_COMMA;
    }

    /**
     * SQL操作Example对象方法
     *
     * @return
     */
    public String toMoExampleDefinitionMethodString() {
        String capitalizeName = StringUtils.capitalize(name);
        String sqlColumnName = VerbalExpressionUtil.sqlUnderLineName(name);
        String javaTypeShortName = fieldTypeShortName;
        String fieldTypeInListShortName = SupportFieldTypeEnum.BASIC_INT.getShortName().equals(fieldOriginTypeName) ? SupportFieldTypeEnum.INTEGER.getShortName() : StringUtils.capitalize(javaTypeShortName);
        StringBuilder builder = new StringBuilder();
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append("为null").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("IsNull() {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("is null\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append("不为null").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("IsNotNull() {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("is not null\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append("等于").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("EqualTo(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("=\", value, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append("不等于").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("NotEqualTo(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("<>\", value, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append("大于").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("GreaterThan(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append(">\", value, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append("大于等于").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("GreaterThanOrEqualTo(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append(">=\", value, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append("小于").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("LessThan(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("<\", value, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append("小于等于").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("LessThanOrEqualTo(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("<=\", value, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append("在列表内").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("In(List<").append(fieldTypeInListShortName).append(">").append(ConstantUtil.SPACE).append("values) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("in\", values, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append("不在列表内").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("NotIn(List<").append(fieldTypeInListShortName).append(">").append(ConstantUtil.SPACE).append("values) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("not in\", values, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append("在起始值范围内").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("Between(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value1,").append(ConstantUtil.SPACE).append(javaTypeShortName).append(ConstantUtil.SPACE).append("value2) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("between\", value1, value2, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append("不在起始值范围内").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("NotBetween(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value1,").append(ConstantUtil.SPACE).append(javaTypeShortName).append(ConstantUtil.SPACE).append("value2) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("not between\", value1, value2, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        if (SupportFieldTypeEnum.STRING.getShortName().equals(fieldTypeShortName)) {
            // 字符串字段, 添加前缀模糊查询
            builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append("模糊查询以前缀开头").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("Like(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value) {").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("like\", value + \"%\", \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
            builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append("模糊查询不以前缀匹开头").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("NotLike(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value) {").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("not like\", value + \"%\", \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        }
        return builder.toString();
    }
}
