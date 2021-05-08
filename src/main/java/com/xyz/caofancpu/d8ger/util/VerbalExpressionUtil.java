package com.xyz.caofancpu.d8ger.util;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex common util
 * Tips: https://github.com/VerbalExpressions/JavaVerbalExpressions
 * 1. DO NOT USE or(), take oneOf() | add(Regex string) place of it
 * 2. USE multi segment capt()+endCapt() for easy reading
 * 3. add() is very powerful, some times it's easy to express OR logic
 *
 * @author caofanCPU
 */
public class VerbalExpressionUtil {
    /**
     * Judge current system is WINDOWS, by the way, WINDOWS is real ***...
     */
    public static boolean CURRENT_OS_IS_WINDOWS = Objects.equals(System.getProperty("os.name").toLowerCase(), "windows");

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
     * Next line separator regex
     */
    public static final Pattern NEXT_LINE_REGEX = Pattern.compile("(?:\\n|(?:\\r\\n))+");

    /**
     * English dot as key symbol regex
     */
    public static final Pattern KEY_ENGLISH_DOT_REGEX = Pattern.compile("\\.");

    /**
     * Symbols '[', ']', '<', '>' OR relationship regex
     */
    public static final Pattern KEY_COLLECTION_EDGE_SYMBOL_REGEX = Pattern.compile("[\\[\\]<>]");

    /**
     * String.replaceAll(), matched result can be access by $0
     */
    public static final String REPLACE_MATCH_RESULT_SYMBOL = "$0";

    /**
     * Java file as source code, which prefix path
     */
    public static Pattern PREFIX_JAVA_SOURCE_FILE_PATH = Pattern.compile("^(?:.*)(?:[/\\\\]*)(?:src)(?:[/\\\\]+)(?:main)(?:[/\\\\]+)(?:java)(?:[/\\\\]+)");

    /**
     * File path split symbol
     */
    public static Pattern FILE_PATH_SPLIT_SYMBOL = Pattern.compile("(?:[/\\\\]+)");

    /**
     * File path prefix split in Windows OS
     */
    public static Pattern WINDOWS_PREFIX_JAVA_SOURCE_FILE_PATH = Pattern.compile("(?:[a-zA-Z]*:\\.*)");

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
                .oneOf("/", "*", ConstantUtil.NEXT_LINE, ConstantUtil.TAB).oneOrMore()
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
     * compatible windows file path
     *
     * @param property
     * @return
     */
    public static String correctUrl(String property) {
        String resultPrefix = File.separator;
        try {
            if (CURRENT_OS_IS_WINDOWS) {
                String[] splits = property.split(ConstantUtil.ENGLISH_COLON);
                resultPrefix = splits[0] + ConstantUtil.ENGLISH_COLON;
                if (splits.length == 1 || StringUtils.isBlank(splits[1])) {
                    return resultPrefix + "/";
                }
                property = splits[1];
            } else {
                property = resultPrefix + property;
            }
        } catch (Throwable e) {
            throw new RuntimeException("Illegal file path, please check carefully!");
        }
        VerbalExpression regex = VerbalExpression.regex()
                .capt()
                .oneOf("/", "\\\\").oneOrMore()
                .endCapt()
                .build();
        String tempResult = executePatternRex(regex, property, "/");
        return CURRENT_OS_IS_WINDOWS ? resultPrefix + tempResult : tempResult;
    }

    /**
     * Convert path string to package,
     * for example: /ModuleName//src/main/java/com/xyz/caofancpu/d8ger/test --> com.xyz.caofancpu.d8ger.test
     * Compatible with WINDOWS: D:/ModuleName//src\main\\java/com/xyz/caofancpu/d8ger/test --> com.xyz.caofancpu.d8ger.test
     *
     * @param originPath
     * @return
     */
    public static String convertPathToPackage(String originPath) {
        String first = originPath.replaceAll(PREFIX_JAVA_SOURCE_FILE_PATH.pattern(), ConstantUtil.EMPTY);
        String second = first.replaceAll(FILE_PATH_SPLIT_SYMBOL.pattern(), ConstantUtil.ENGLISH_FULL_STOP);
        if (StringUtils.isBlank(second) || second.length() < 2) {
            throw new RuntimeException("Illegal file path, please check carefully!");
        }
        if (CURRENT_OS_IS_WINDOWS) {
            String winR = second.replaceAll(WINDOWS_PREFIX_JAVA_SOURCE_FILE_PATH.pattern(), ConstantUtil.EMPTY);
            if (StringUtils.isBlank(winR)) {
                throw new RuntimeException("Illegal file path, please check carefully!");
            }
            return winR;
        }
        return Objects.equals(second.charAt(0), '.') ? second.substring(1) : second;
    }

    public static String convertPathToPackage2(String originPath) {
        VerbalExpression regex1 = VerbalExpression.regex()
                .startOfLine().anything()
                .capt().oneOf("/", "\\\\").zeroOrMore().endCapt()
                .capt().find("src").oneOf("/", "\\\\").oneOrMore().endCapt()
                .capt().find("main").oneOf("/", "\\\\").oneOrMore().endCapt()
                .capt().find("java").oneOf("/", "\\\\").oneOrMore().endCapt()
                .build();
        String first = executePatternRex(regex1, originPath, ConstantUtil.EMPTY);
        VerbalExpression regex2 = VerbalExpression.regex()
                .capt()
                .oneOf("/", "\\\\").oneOrMore()
                .endCapt()
                .build();
        String second = executePatternRex(regex2, first, ConstantUtil.ENGLISH_FULL_STOP);
        if (StringUtils.isBlank(second) || second.length() < 2) {
            throw new RuntimeException("Illegal file path, please check carefully!");
        }
        if (CURRENT_OS_IS_WINDOWS) {
            VerbalExpression regex3 = VerbalExpression.regex()
                    .capt()
                    .add("[a-zA-Z]").zeroOrMore().then(":").then(".").zeroOrMore()
                    .endCapt()
                    .build();
            String winR = executePatternRex(regex3, second, ConstantUtil.EMPTY);
            if (StringUtils.isBlank(winR)) {
                throw new RuntimeException("Illegal file path, please check carefully!");
            }
            return winR;
        }
        return Objects.equals(second.charAt(0), '.') ? second.substring(1) : second;
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
