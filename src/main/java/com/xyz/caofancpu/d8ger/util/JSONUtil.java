package com.xyz.caofancpu.d8ger.util;

import lombok.NonNull;

/**
 * JSON tool
 *
 * @author caofanCPU
 */
public class JSONUtil {

    public static String formatStandardJSON(@NonNull String source) {
        String nonWhiteCharStr = VerbalExpressionUtil.cleanJSONWhiteChar(source);
        int level = 0;
        StringBuilder resultBuilder = new StringBuilder();
        // Loop through each character
        int doubleQuoteCount = 0;
        for (int i = 0; i < nonWhiteCharStr.length(); i++) {
            // Get the current character
            char piece = nonWhiteCharStr.charAt(i);
            // If the previous character is a line break,
            // add a marker according to the level value at the beginning of the line,
            // excluding the first line
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
                    // If the character is '{' or '[', then line break, level plus 1
                    resultBuilder.append(piece);
                    if (doubleQuoteCount % 2 == 0) {
                        resultBuilder.append(ConstantUtil.NEXT_LINE);
                        level++;
                    }
                    break;
                case ',':
                    resultBuilder.append(piece);
                    // If the character is ',' then break line
                    if (doubleQuoteCount % 2 == 0) {
                        resultBuilder.append(ConstantUtil.NEXT_LINE);
                    }
                    break;
                case '}':
                case ']':
                    // If the character is '}' or '}', then break line and level minus 1
                    if (doubleQuoteCount % 2 == 0) {
                        resultBuilder.append(ConstantUtil.NEXT_LINE);
                        level--;
                        for (int k = 0; k < level; k++) {
                            resultBuilder.append(ConstantUtil.TAB);
                        }
                    }
                    resultBuilder.append(piece);
                    break;
                case ':':
                    // add a space to beauty view
                    resultBuilder.append(piece).append(ConstantUtil.SPACE);
                    break;
                default:
                    resultBuilder.append(piece);
                    break;
            }
        }
        return VerbalExpressionUtil.beautyNextLine(resultBuilder.toString());
    }
}
