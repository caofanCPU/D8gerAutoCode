package com.xyz.caofancpu.d8ger.util;

import lombok.NonNull;

/**
 * JSON工具类
 *
 * @author caofanCPU
 */
public class JSONUtil {

    public static String formatStandardJSON(@NonNull String source) {
        String nonWhiteCharStr = VerbalExpressionUtil.cleanWhiteChar(source);
        int level = 0;
        StringBuilder resultBuilder = new StringBuilder();
        // 循环遍历每一个字符
        int doubleQuoteCount = 0;
        for (int i = 0; i < nonWhiteCharStr.length(); i++) {
            // 获取当前字符
            char piece = nonWhiteCharStr.charAt(i);
            // 如果上一个字符是断行, 则在本行开始按照level数值添加标记符, 排除第一行
            if (i != 0 && '\n' == resultBuilder.charAt(resultBuilder.length() - 1)) {
                for (int k = 0; k < level; k++) {
                    resultBuilder.append(ConstantUtil.TAB);
                }
            }

            if (piece == '"') {
                doubleQuoteCount++;
            }

            switch (piece) {
                case '{':
                case '[':
                    // 如果字符是{或者[, 则断行, level加1
                    resultBuilder.append(piece);
                    if (doubleQuoteCount % 2 == 0) {
                        resultBuilder.append(ConstantUtil.NEXT_LINE);
                        level++;
                    }
                    break;
                case ',':
                    // 如果是",", 则断行
                    resultBuilder.append(piece).append(ConstantUtil.NEXT_LINE);
                    break;
                case '}':
                case ']':
                    // 如果是"}"或者"]", 则断行, level减1
                    if (doubleQuoteCount % 2 == 0) {
                        resultBuilder.append(ConstantUtil.NEXT_LINE);
                        level--;
                        for (int k = 0; k < level; k++) {
                            resultBuilder.append(ConstantUtil.TAB);
                        }
                    }
                    resultBuilder.append(piece);
                    break;
                default:
                    resultBuilder.append(piece);
                    break;
            }
        }
        return VerbalExpressionUtil.beautyNextLine(resultBuilder.toString());
    }
}
