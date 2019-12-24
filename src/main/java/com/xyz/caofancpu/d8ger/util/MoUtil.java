package com.xyz.caofancpu.d8ger.util;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

/**
 * @author caofanCPU
 */
public class MoUtil {

    /**
     * 自动生成方法注释
     *
     * @param comment
     * @param paramName
     * @param needReturn
     * @return
     */
    public static String wrapMethodHeader(@NonNull String comment, @NonNull String paramName, boolean needReturn) {
        StringBuilder builder = new StringBuilder()
                .append("\t").append("/**").append("\n")
                .append("\t").append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append(comment).append("\n")
                .append("\t").append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("\n");
        if (StringUtils.isNotBlank(paramName)) {
            builder.append("\t").append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@param").append(ConstantUtil.SPACE).append(paramName).append("\n");
        }
        if (needReturn) {
            builder.append("\t").append(ConstantUtil.SPACE).append("*").append(ConstantUtil.SPACE).append("@return").append("\n");
        }
        return builder.toString();
    }

}
