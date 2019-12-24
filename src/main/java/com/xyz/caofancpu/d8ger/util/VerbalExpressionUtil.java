package com.xyz.caofancpu.d8ger.util;

import ru.lanwen.verbalregex.VerbalExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FileName: VerbalExpressionUtil
 *
 * @author caofanCPU
 */
public class VerbalExpressionUtil {

    public static String removeMoSuffixForTable(String moClassName) {
        return executePatternRex(VerbalExpression.regex().capt().find(ConstantUtil.MO_NAME_SUFFIX).endCapt().endOfLine().build(),
                moClassName,
                ConstantUtil.EMPTY
        );
    }

    public static String executePatternRex(VerbalExpression regexExpression, String originText, String replacer) {
        Pattern pattern = Pattern.compile(regexExpression.toString());
        Matcher matcher = pattern.matcher(originText);
        return matcher.replaceAll(replacer);
    }

}
