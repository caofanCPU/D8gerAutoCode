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

    /**
     * 创建正则表达式对象
     *
     * @param matchKeyWord
     * @return
     */
    public static VerbalExpression buildRegex(String matchKeyWord) {
        return VerbalExpression.regex().capt().find(matchKeyWord).endCapt().build();
    }

    /**
     * 析取正则表达式
     *
     * @param originComment
     * @return
     */
    public static String extractComment(String originComment) {
        VerbalExpression regex = VerbalExpression.regex()
                .capt()
                .find("/").oneOrMore()
                .or("*").oneOrMore()
                .or(ConstantUtil.NEXT_LINE).oneOrMore()
                .or(ConstantUtil.TAB).oneOrMore()
                .endCapt()
                .build();
        return executePatternRex(regex, originComment, ConstantUtil.EMPTY);
    }

    /**
     * 正则替换
     *
     * @param regexExpression
     * @param originText
     * @param replacer
     * @return
     */
    public static String executePatternRex(VerbalExpression regexExpression, String originText, String replacer) {
        Pattern pattern = Pattern.compile(regexExpression.toString());
        Matcher matcher = pattern.matcher(originText);
        return matcher.replaceAll(replacer);
    }

}
