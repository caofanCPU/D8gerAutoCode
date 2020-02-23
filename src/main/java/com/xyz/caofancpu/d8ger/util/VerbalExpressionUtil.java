package com.xyz.caofancpu.d8ger.util;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FileName: VerbalExpressionUtil
 *
 * @author caofanCPU
 */
public class VerbalExpressionUtil {

    /**
     * Uppercase regular expression
     */
    public static final Pattern HUMP_TO_UNDERLINE = Pattern.compile("[A-Z]");

    /**
     * Swagger field position order regular match expression
     */
    public static final Pattern SWAGGER_POSITION_PATTERN = Pattern.compile("((?:position)(?:\\s)*(?:\\=)(?:\\s)*(?:\\d)*)");

    /**
     * Swagger field position order regular replacement
     *
     * @param originString
     * @param replaceString
     * @return
     */
    public static String regexHandlePositionProperty(String originString, final String replaceString) {
        Matcher matcher = SWAGGER_POSITION_PATTERN.matcher(originString);
        return matcher.replaceAll(replaceString);
    }

    /**
     * Create a regular expression object
     *
     * @param matchKeyWord
     * @return
     */
    public static VerbalExpression buildRegex(String matchKeyWord) {
        return VerbalExpression.regex().capt().find(matchKeyWord).endCapt().build();
    }

    /**
     * Disjunction regular expression
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
     * Regular replacement
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

    /**
     * Regular replacement of relatively large strings, using StringBuilder to store strings
     *
     * @param regexExpression
     * @param originText
     * @param replacer
     * @return
     */
    public static StringBuilder executePatternRex(VerbalExpression regexExpression, StringBuilder originText, StringBuilder replacer) {
        Pattern pattern = Pattern.compile(regexExpression.toString());
        Matcher matcher = pattern.matcher(originText);
        return new StringBuilder(matcher.replaceAll(replacer.toString()));
    }

    /**
     * Camel convert to underline
     *
     * @param originName
     * @return
     */
    public static String camelToUnderLineName(String originName) {
        return StringUtils.lowerCase(StringUtils.uncapitalize(originName).replaceAll(HUMP_TO_UNDERLINE.pattern(), "_$0"));
    }

    /**
     * Remove the trailing one or more 'Mo's from the original Model object's name
     *
     * @param originMoName
     * @return
     */
    public static String cropMoSuffix(@NonNull String originMoName) {
        VerbalExpression regex = VerbalExpression.regex().capt().find(ConstantUtil.MO_SUFFIX).oneOrMore().endCapt().endOfLine().build();
        return executePatternRex(regex, originMoName, ConstantUtil.EMPTY);
    }

    /**
     * url path correction, remove rare '/' to keep just one '/' and begin with it
     *
     * @param property
     * @return
     */
    public static String correctUrl(String property) {
        VerbalExpression regex = VerbalExpression.regex().capt().find(File.separator).oneOrMore().endCapt().build();
        return executePatternRex(regex, File.separator + property, File.separator);
    }

    /**
     * Beautify multiple newlines
     *
     * @param source
     * @return
     */
    public static String beautyNextLine(@NonNull String source) {
        VerbalExpression regex = VerbalExpression.regex()
                .lineBreak().oneOrMore()
                .build();
        return executePatternRex(regex, source, ConstantUtil.NEXT_LINE);
    }

    /**
     * Clear whitespace
     *
     * @param source
     * @return
     */
    public static String cleanWhiteChar(@NonNull String source) {
        VerbalExpression regex = VerbalExpression.regex()
                .capt()
                .space().oneOrMore()
                .or("\\n").oneOrMore()
                .or("\\r\\n").oneOrMore()
                .or("\\t").oneOrMore()
                .endCapt()
                .build();
        return executePatternRex(regex, source, ConstantUtil.EMPTY);
    }

}
