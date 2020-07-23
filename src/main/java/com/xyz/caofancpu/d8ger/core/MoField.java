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
 * Model encapsulated object
 *
 * @author caofanCPU
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MoField {
    /**
     * Field comment(name)
     */
    private String comment;

    /**
     * Access modifier, private is used by default
     */
    private String accessModifier;

    /**
     * Field primitive type name
     */
    private String fieldOriginTypeName;

    /**
     * Field type abbreviation
     */
    private String fieldTypeShortName;

    /**
     * Field SQL Type Name
     */
    private String fieldSqlTypeName;

    /**
     * Field Name
     */
    private String name;

    /**
     * Field order
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
                    // Enums or enums with names that are enumerated
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
     * SQL columns field type display
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
     * Swagger fields
     *
     * @return
     */
    public String toSwaggerString() {
        return ConstantUtil.TAB + "@ApiModelProperty(value = \"" + comment + "\", required = false, example = \"\", position = " + (index + 1) + ")" + ConstantUtil.NEXT_LINE
                + ConstantUtil.TAB + accessModifier + ConstantUtil.SPACE + fieldTypeShortName + ConstantUtil.SPACE + name + ConstantUtil.ENGLISH_SEMICOLON;
    }

    /**
     * SQL columns definition field
     *
     * @return
     */
    public String toSqlColumnDefinitionString() {
        if (name.equals(ConstantUtil.SQL_ID)) {
            return ConstantUtil.TAB + ConstantUtil.SQL_ID + ConstantUtil.SPACE + fieldSqlTypeName + ConstantUtil.SPACE + "unsigned auto_increment" + ConstantUtil.SPACE + "comment" + ConstantUtil.SPACE + "'" + comment + "'" + ConstantUtil.SPACE + "primary key";
        }

        return ConstantUtil.TAB + VerbalExpressionUtil.sqlUnderLineName(name) + ConstantUtil.SPACE + fieldSqlTypeName + ConstantUtil.SPACE + wrapSqlDefaultValueView() + ConstantUtil.SPACE + "comment" + ConstantUtil.SPACE + "'" + comment + "'";
    }

    /**
     * SQL operating methods by using example object
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
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append(ConstantUtil.SPACE).append("Is Null").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("IsNull() {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("is null\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append(ConstantUtil.SPACE).append("IS Not Null").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("IsNotNull() {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("is not null\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append(ConstantUtil.SPACE).append("Equal").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("EqualTo(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("=\", value, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append(ConstantUtil.SPACE).append("Not Equal").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("NotEqualTo(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("<>\", value, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append(ConstantUtil.SPACE).append("Greater Than").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("GreaterThan(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append(">\", value, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append(ConstantUtil.SPACE).append("Greater Than Or Equal To").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("GreaterThanOrEqualTo(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append(">=\", value, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append(ConstantUtil.SPACE).append("Less Than").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("LessThan(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("<\", value, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append(ConstantUtil.SPACE).append("Less Than Or Equal To").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("LessThanOrEqualTo(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("<=\", value, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append(ConstantUtil.SPACE).append("In").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("In(List<").append(fieldTypeInListShortName).append(">").append(ConstantUtil.SPACE).append("values) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("in\", values, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append(ConstantUtil.SPACE).append("Not In").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("NotIn(List<").append(fieldTypeInListShortName).append(">").append(ConstantUtil.SPACE).append("values) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("not in\", values, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append(ConstantUtil.SPACE).append("Between").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("Between(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value1,").append(ConstantUtil.SPACE).append(javaTypeShortName).append(ConstantUtil.SPACE).append("value2) {").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("between\", value1, value2, \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
        builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append(ConstantUtil.SPACE).append("Not Between").append(ConstantUtil.NEXT_LINE)
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
                    .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append(ConstantUtil.SPACE).append("Like").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*/").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append("public Criteria and").append(capitalizeName).append("Like(").append(javaTypeShortName).append(ConstantUtil.SPACE).append("value) {").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.TRIPLE_TAB).append("addCriterion(\"").append(sqlColumnName).append(ConstantUtil.SPACE).append("like\", value + \"%\", \"").append(name).append("\");").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.TRIPLE_TAB).append("return (Criteria) this;").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append("}").append(ConstantUtil.NEXT_LINE).append(ConstantUtil.NEXT_LINE);
            builder.append(ConstantUtil.DOUBLE_TAB).append("/**").append(ConstantUtil.NEXT_LINE)
                    .append(ConstantUtil.DOUBLE_TAB).append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(name).append(ConstantUtil.SPACE).append("Not Like").append(ConstantUtil.NEXT_LINE)
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
