package com.xyz.caofancpu.d8ger.util;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex common util
 *
 * @author caofanCPU
 */
public class VerbalExpressionUtil {

    /**
     * Uppercase regular expression
     */
    public static final Pattern HUMP_TO_UNDERLINE = Pattern.compile("[A-Z]");

    /**
     * No '_' or '-' and begin with [A-Z] regex, it will trigger to execute CamelToUnderline when regex detect is true
     */
    public static final Pattern CAMEL_UNDERLINE_1_NO_UNDERLINE_CAPITALIZE = Pattern.compile("^(?![_-])(?:[A-Z])[a-zA-Z0-9\\W]+$");

    /**
     * No '_' or '-' and begin with [a-z] regex, it will trigger to execute CamelToUnderline when regex detect is true
     */
    public static final Pattern CAMEL_UNDERLINE_2_NO_UNDERLINE_UNCAPITALIZE = Pattern.compile("^(?![_-])(?:[a-z])[a-zA-Z0-9\\W]+$");

    /**
     * No upper case regex, it will trigger to execute LowerCaseToUpperCase when regex detect is true
     */
    public static final Pattern CAMEL_UNDERLINE_3_NO_UPPER_CASE = Pattern.compile("^(?![A-Z])[a-z0-9\\W_-]+$");

    /**
     * No lower case, it will trigger to execute UpperCaseToCamel when regex detect is true
     */
    public static final Pattern CAMEL_UNDERLINE_4_NO_LOWER_CASE = Pattern.compile("^(?![a-z])[A-Z0-9\\W_-]+$");

    /**
     * Swagger field | interface position order regular match expression
     */
    public static final Pattern SWAGGER_MODEL_PATTERN = Pattern.compile("(((?:position)|(?:order))(?:\\s)*(?:=)(?:\\s)*(?:\\d)*)");

    /**
     * When we clear white chars, considering spaces can be part of data we should except spaces in JSON string
     */
    public static final Pattern WHITE_CHAR_IN_JSON_REGEX_0 = Pattern.compile("(?:[\\t\\n\\x0B\\f\\r])+");

    /**
     * Beauty JSON view regex
     */
    public static final Pattern WHITE_CHAR_IN_JSON_REGEX_1 = Pattern.compile("(?:\")+[ ]*[:：]+[ ]*");

    /**
     * JSON string definition regex
     */
    public static final Pattern JSON_STRING_JUDGE_REGEX = Pattern.compile("^(?:\\{).*(?:})$");

    /**
     * CaoFAn -->(Uncapitalize) caoFAn -->(CamelToUnderline) cao_f_an -->(LowerCaseToUpperCase) CAO_F_AN -->(UpperCaseToCamel) CaoFAn
     *
     * @param originName
     * @return
     */
    public static String camelUnderLineNameConverter(@NonNull String originName) {
        int matchNo = 0;
        if (CAMEL_UNDERLINE_1_NO_UNDERLINE_CAPITALIZE.matcher(originName).matches()) {
            matchNo = 1;
        }
        if (CAMEL_UNDERLINE_2_NO_UNDERLINE_UNCAPITALIZE.matcher(originName).matches()) {
            matchNo = 2;
        }
        if (CAMEL_UNDERLINE_3_NO_UPPER_CASE.matcher(originName).matches()) {
            matchNo = 3;
        }
        if (CAMEL_UNDERLINE_4_NO_LOWER_CASE.matcher(originName).matches()) {
            matchNo = 4;
        }
        if (matchNo == 0) {
            return originName;
        }
        String result = originName;
        switch (matchNo) {
            case 1:
                // Uncapitalize
                result = StringUtils.uncapitalize(originName);
                break;
            case 2:
                // CamelToUnderline
                result = StringUtils.lowerCase(originName.replaceAll(HUMP_TO_UNDERLINE.pattern(), "_$0"));
                break;
            case 3:
                // LowerCaseToUpperCase
                result = StringUtils.upperCase(originName);
                break;
            case 4:
                // UpperCaseToCamel
                String[] words = originName.split("[_-]");
                List<String> resultItemWordList = new ArrayList<>(words.length);
                for (String word : words) {
                    resultItemWordList.add(StringUtils.capitalize(StringUtils.lowerCase(word)));
                }
                result = CollectionUtil.join(resultItemWordList, ConstantUtil.EMPTY);
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * Swagger field | interface position order regular replacement
     *
     * @param originString
     * @param replaceString
     * @return
     */
    public static String regexHandleSwaggerModelProperty(String originString, final String replaceString) {
        Matcher matcher = SWAGGER_MODEL_PATTERN.matcher(originString);
        return matcher.replaceAll(replaceString);
    }

    /**
     * Extract matched content list by pattern
     *
     * @param originContext
     * @param pattern
     * @return
     */
    public static List<String> extractMatchContent(@NonNull String originContext, Pattern pattern) {
        List<String> resultList = new ArrayList<>();
        Matcher matcher = pattern.matcher(originContext);
        while (matcher.find()) {
            // add current matched group value
            resultList.add(matcher.group());
        }
        return resultList;
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
    public static String sqlUnderLineName(String originName) {
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
     * Convert path string to package,
     * for example: /ModuleName/src/main/java/com/xyz/caofancpu/d8ger/test --> com.xyz.caofancpu.d8ger.test
     *
     * @param originPath
     * @return
     */
    public static String convertPathToPackage(String originPath) {
        VerbalExpression regex1 = VerbalExpression.regex()
                .capt()
                .startOfLine()
                .anything()
                .find(File.separator).zeroOrMore()
                .then("src").then(File.separator).zeroOrMore()
                .then("main").then(File.separator).zeroOrMore()
                .then("java")
                .endCapt()
                .build();
        String first = executePatternRex(regex1, originPath, ConstantUtil.EMPTY);
        VerbalExpression regex2 = VerbalExpression.regex()
                .startOfLine()
                .then(File.separator).oneOrMore()
                .build();
        String second = executePatternRex(regex2, first, ConstantUtil.EMPTY);
        return second.replaceAll(File.separator, ConstantUtil.ENGLISH_STOP);
    }

    /**
     * Beautify multiple newlines
     * (?:\\n|(?:\\r\\n))+
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
     * (?:\\s)+
     *
     * @param source
     * @return
     */
    public static String cleanWhiteChar(@NonNull String source) {
        VerbalExpression regex = VerbalExpression.regex()
                .space().oneOrMore()
                .build();
        return executePatternRex(regex, source, ConstantUtil.EMPTY);
    }


    /**
     * Clear whitespace in JSON string
     *
     * @param source
     * @return
     */
    public static String cleanJSONWhiteChar(@NonNull String source) {
        return source.replaceAll(WHITE_CHAR_IN_JSON_REGEX_0.pattern(), ConstantUtil.EMPTY)
                .replaceAll(WHITE_CHAR_IN_JSON_REGEX_1.pattern(), ConstantUtil.ENGLISH_DOUBLE_QUOTES + ConstantUtil.ENGLISH_COLON);
    }
}
