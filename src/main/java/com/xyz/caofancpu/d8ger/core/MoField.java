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
     * 字段类型简称
     */
    private SupportFieldTypeEnum fieldTypeEnum;
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
                this.fieldTypeEnum = SupportFieldTypeEnum.positionByShortName(child.getText());
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
        if (Objects.isNull(fieldTypeEnum) || fieldTypeEnum == SupportFieldTypeEnum.NONE) {
            return ConstantUtil.EMPTY;
        }
        if (fieldTypeEnum.getSqlName().contains("int")) {
            return "default 0 null";
        }
        if (fieldTypeEnum.getSqlName().contains("double")) {
            return "default 0.00 null";
        }
        if (fieldTypeEnum.getSqlName().contains("datetime")) {
            return name.equals(ConstantUtil.SQL_UPDATE_TIME) ? "default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP" : "default CURRENT_TIMESTAMP null";
        }
        return "default '' null";
    }

    @Override
    public String toString() {
        return ConstantUtil.TAB + "/**\n"
                + ConstantUtil.TAB + ConstantUtil.SPACE + "*" + ConstantUtil.SPACE + comment + "\n"
                + ConstantUtil.TAB + ConstantUtil.SPACE + "*/" + "\n"
                + ConstantUtil.TAB + accessModifier + ConstantUtil.SPACE + fieldTypeEnum.getShortName() + ConstantUtil.SPACE + name + ConstantUtil.ENGLISH_SEMICOLON;
    }

    /**
     * Swagger字段
     *
     * @return
     */
    public String toSwaggerString() {
        return ConstantUtil.TAB + "@ApiModelProperty(value = \"" + comment + "\", required = false, example = \"\", position = " + index + ")" + ConstantUtil.NEXT_LINE
                + ConstantUtil.TAB + accessModifier + ConstantUtil.SPACE + fieldTypeEnum.getShortName() + ConstantUtil.SPACE + name + ConstantUtil.ENGLISH_SEMICOLON;
    }

    /**
     * SQL列定义字段
     *
     * @return
     */
    public String toSqlColumnDefinitionString() {
        if (name.equals(ConstantUtil.SQL_ID)) {
            return ConstantUtil.TAB + ConstantUtil.SQL_ID + ConstantUtil.SPACE + fieldTypeEnum.getSqlName() + ConstantUtil.SPACE + "unsigned auto_increment" + ConstantUtil.SPACE + wrapSqlDefaultValueView() + ConstantUtil.SPACE + "comment" + ConstantUtil.SPACE + "'" + comment + "'" + ConstantUtil.SPACE + "primary key" + ConstantUtil.ENGLISH_COMMA;
        }

        return ConstantUtil.TAB + VerbalExpressionUtil.sqlUnderLineName(name) + ConstantUtil.SPACE + fieldTypeEnum.getSqlName() + ConstantUtil.SPACE + wrapSqlDefaultValueView() + ConstantUtil.SPACE + "comment" + ConstantUtil.SPACE + "'" + comment + "'" + ConstantUtil.ENGLISH_COMMA;
    }
}
