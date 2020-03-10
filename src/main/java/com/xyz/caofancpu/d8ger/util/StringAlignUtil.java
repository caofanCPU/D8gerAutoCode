package com.xyz.caofancpu.d8ger.util;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * Handle multi-lines align, batch add prefix | suffix
 *
 * @author caofanCPU
 */
@Slf4j
public class StringAlignUtil {
    /**
     * White char regex
     */
    public static final Pattern WHITE_CHAR_PATTERN = Pattern.compile("(?:\\s)+");

    /**
     * Compatibility separator pattern, support one or more lineBreak | English comma as ',' | Chinese comma as '，'
     */
    public static final Pattern ORIGIN_COMPATIBILITY_SEPARATOR = Pattern.compile("((?:\\n|(?:\\r\\n))|(?:,)|(?:，))+");

    /**
     * Line begins with English comma
     */
    public static final Pattern START_WITH_ENGLISH_COMMA_PATTERN = Pattern.compile("^(?:,)+");

    /**
     * Config key parser regex
     */
    public static final Pattern CONFIG_PARSER_PATTERN = Pattern.compile("(?:@<)(?:.*)(?:>@)");

    /**
     * Handling multi lines by conventional separator
     *
     * @param originText
     * @return
     */
    public static List<String> handleSplitMultiLines(@NonNull String originText) {
        String legalText = originText.replaceAll(ORIGIN_COMPATIBILITY_SEPARATOR.pattern(), ConstantUtil.ENGLISH_COMMA)
                .replaceAll(WHITE_CHAR_PATTERN.pattern(), ConstantUtil.EMPTY)
                .replaceAll(START_WITH_ENGLISH_COMMA_PATTERN.pattern(), ConstantUtil.EMPTY);
        String splitSymbol = ConstantUtil.ENGLISH_COMMA;
        return CollectionUtil.splitDelimitedStringToList(legalText, splitSymbol, String::toString);
    }

    /**
     * Format SQL columns, for example, add function or rename by using 'AS'
     *
     * @param originText
     * @param formatAlignment
     * @param prefix
     * @param suffix
     * @param formatSQL
     * @param formatAsCamel
     * @return
     */
    public static String formatSQLColumn(@NonNull String originText, Alignment formatAlignment, @NonNull String prefix, @NonNull String suffix, boolean formatSQL, boolean formatAsCamel) {
        List<String> stringList = handleSplitMultiLines(originText);
        List<String> completeFixList = CollectionUtil.transToList(stringList, item -> prefix + item + suffix);
        int singleLineMaxChars = CollectionUtil.max(completeFixList, String::length).intValue();
        if (Objects.isNull(formatAlignment)) {
            formatAlignment = Alignment.LEFT;
        }
        List<String> formattedLineList = formatSQLColumn(singleLineMaxChars, formatAlignment, completeFixList);
        if (formatSQL && formatAsCamel) {
            stringList = CollectionUtil.transToList(stringList, StringAlignUtil::cleanUnderLineForSQLAliasName);
        }
        Map<Integer, String> indexMap = CollectionUtil.transToMap(stringList, stringList::indexOf, Function.identity());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < formattedLineList.size(); i++) {
            String column = indexMap.get(i);
            result.append(formattedLineList.get(i));
            if (formatSQL) {
                result.append(ConstantUtil.SPACE).append(ConstantUtil.SPACE).append("AS").append(ConstantUtil.SPACE).append(ConstantUtil.SPACE).append(column);
            }
            result.append(ConstantUtil.ENGLISH_COMMA).append(ConstantUtil.NEXT_LINE);
        }
        return StringUtils.isEmpty(result) ? result.toString() : result.deleteCharAt(result.lastIndexOf(ConstantUtil.ENGLISH_COMMA)).toString();
    }

    /**
     * format multi-lines and handling encryption | decryption
     *
     * @param originText
     * @param alignStyle
     * @param algorithm
     * @param endOperate
     * @return
     */
    public static String formatEND(@NonNull String originText, Alignment alignStyle, Algorithm algorithm, ENDOperate endOperate) {
        List<String> originStringList = handleSplitMultiLines(originText);
        // init param
        if (Objects.isNull(alignStyle)) {
            alignStyle = Alignment.LEFT;
        }
        if (Objects.isNull(algorithm)) {
            algorithm = Algorithm.AES;
        }
        if (Objects.isNull(endOperate)) {
            endOperate = ENDOperate.ENCRYPTION_AND_DECRYPTION;
        }

        // calculate operate
        List<ENDOperate> operateList = new ArrayList<>(4);
        if (ENDOperate.ENCRYPTION_AND_DECRYPTION == endOperate) {
            operateList.add(ENDOperate.ENCRYPTION);
            operateList.add(ENDOperate.DECRYPTION);
            if (Algorithm.PINYIN == algorithm) {
                operateList.add(ENDOperate.PINYIN);
            }
        } else {
            operateList.add(endOperate);
            if (ENDOperate.ENCRYPTION == endOperate && Algorithm.PINYIN == algorithm) {
                operateList.add(ENDOperate.PINYIN);
            }
        }

        Map<ENDOperate, List<String>> handleResultMap = new HashMap<>(8, 0.75f);
        handleResultMap.put(ENDOperate.ORIGIN, originStringList);
        if (operateList.contains(ENDOperate.PINYIN)) {
            // calculate pinyin
            handleResultMap.put(ENDOperate.PINYIN, CollectionUtil.transToList(originStringList, String::toString));
        }
        if (operateList.contains(ENDOperate.ENCRYPTION)) {
            List<String> targetList;

            if (operateList.contains(ENDOperate.PINYIN)) {
                targetList = handleResultMap.get(ENDOperate.PINYIN);
            } else {
                targetList = originStringList;
            }
            if (Algorithm.PINYIN == algorithm) {
                // PINYIN encryption TODO
                handleResultMap.put(ENDOperate.ENCRYPTION, CollectionUtil.transToList(targetList, String::toUpperCase));
            } else {
                // AES encryption TODO
                handleResultMap.put(ENDOperate.ENCRYPTION, CollectionUtil.transToList(targetList, String::toLowerCase));
            }
        }
        if (operateList.contains(ENDOperate.DECRYPTION)) {
            List<String> targetList;
            if (operateList.contains(ENDOperate.ENCRYPTION)) {
                targetList = handleResultMap.get(ENDOperate.ENCRYPTION);
            } else {
                targetList = originStringList;
            }
            if (Algorithm.PINYIN == algorithm) {
                // PINYIN decryption TODO
                handleResultMap.put(ENDOperate.DECRYPTION, CollectionUtil.transToList(targetList, String::toLowerCase));
            } else {
                // AES decryption TODO
                handleResultMap.put(ENDOperate.DECRYPTION, CollectionUtil.transToList(targetList, String::toUpperCase));
            }
        }

        // align string
        for (ENDOperate key : handleResultMap.keySet()) {
            List<String> itemList = handleResultMap.get(key);
            int maxCharLength = CollectionUtil.max(itemList, String::length).intValue();
            handleResultMap.put(key, formatSQLColumn(maxCharLength, alignStyle, itemList));
        }

        StringBuilder result = new StringBuilder();
        List<String> formattedOriginList = handleResultMap.get(ENDOperate.ORIGIN);
        List<String> pinyinList = handleResultMap.get(ENDOperate.PINYIN);
        List<String> encryptionList = handleResultMap.get(ENDOperate.ENCRYPTION);
        List<String> decryptionList = handleResultMap.get(ENDOperate.DECRYPTION);
        for (int i = 0; i < formattedOriginList.size(); i++) {
            result.append(formattedOriginList.get(i));
            if (i != formattedOriginList.size() - 1) {
                result.append(ConstantUtil.ENGLISH_COMMA);
            } else {
                result.append(ConstantUtil.SPACE);
            }
            if (CollectionUtil.isNotEmpty(pinyinList)) {
                result.append(ConstantUtil.SPACE).append(ConstantUtil.SPACE).append("-->(PinYin Result)").append(ConstantUtil.SPACE).append(ConstantUtil.SPACE).append(pinyinList.get(i));
                if (i != formattedOriginList.size() - 1) {
                    result.append(ConstantUtil.ENGLISH_COMMA);
                } else {
                    result.append(ConstantUtil.SPACE);
                }
            }
            if (CollectionUtil.isNotEmpty(encryptionList)) {
                result.append(ConstantUtil.SPACE).append(ConstantUtil.SPACE).append("-->(Encryption Result)").append(ConstantUtil.SPACE).append(ConstantUtil.SPACE).append(encryptionList.get(i));
                if (i != formattedOriginList.size() - 1) {
                    result.append(ConstantUtil.ENGLISH_COMMA);
                } else {
                    result.append(ConstantUtil.SPACE);
                }
            }
            if (CollectionUtil.isNotEmpty(decryptionList)) {
                result.append(ConstantUtil.SPACE).append(ConstantUtil.SPACE).append("-->(Decryption Result)").append(ConstantUtil.SPACE).append(ConstantUtil.SPACE).append(decryptionList.get(i));
                if (i != formattedOriginList.size() - 1) {
                    result.append(ConstantUtil.ENGLISH_COMMA);
                } else {
                    result.append(ConstantUtil.SPACE);
                }
            }
            result.append(ConstantUtil.NEXT_LINE);
        }

        return result.toString();
    }

    public static String cleanUnderLineForSQLAliasName(@NonNull String columnName) {
        String result = columnName;
        for (int i = 0; i < 4; i++) {
            if (VerbalExpressionUtil.CAMEL_UNDERLINE_2_NO_UNDERLINE_UNCAPITALIZE.matcher(result).matches()) {
                break;
            }
            result = VerbalExpressionUtil.camelUnderLineNameConverter(result);
        }
        return result;
    }

    /**
     * Format by splitSymbol, such as ',' or NEXT_LINE
     *
     * @param originText
     * @param splitSymbol
     * @param currentAlignment
     * @return
     */
    public static String formatBySplitSymbol(String originText, String splitSymbol, Alignment currentAlignment) {
        List<String> stringList = CollectionUtil.splitDelimitedStringToList(originText, splitSymbol, String::toString);
        return formatMultiLine(stringList, currentAlignment);
    }

    /**
     * MultiLine format
     *
     * @param stringList
     * @param currentAlignment
     * @return
     */
    public static String formatMultiLine(List<String> stringList, Alignment currentAlignment) {
        int singleLineMaxChars = CollectionUtil.max(stringList, String::length).intValue();
        return format(singleLineMaxChars, currentAlignment, stringList);
    }

    /**
     * Format multi-string
     *
     * @param singleLineMaxChars
     * @param currentAlignment
     * @param stringList
     * @return
     */
    public static String format(int singleLineMaxChars, Alignment currentAlignment, List<String> stringList) {
        checkAlignmentParam(singleLineMaxChars, currentAlignment);
        StringBuilder result = new StringBuilder();
        for (String wanted : stringList) {
            switch (currentAlignment) {
                case RIGHT:
                    pad(result, singleLineMaxChars - wanted.length());
                    result.append(wanted);
                    break;
                case CENTER:
                    int toAdd = singleLineMaxChars - wanted.length();
                    pad(result, toAdd / 2);
                    result.append(wanted);
                    pad(result, toAdd - toAdd / 2);
                    break;
                case LEFT:
                    result.append(wanted);
                    pad(result, singleLineMaxChars - wanted.length());
                    break;
            }
            result.append(ConstantUtil.NEXT_LINE);
        }
        return result.toString();
    }

    /**
     * Format sql columns, return multi-lines
     *
     * @param stringList
     * @param singleLineMaxChars
     * @param currentAlignment
     * @return
     */
    public static List<String> formatSQLColumn(int singleLineMaxChars, Alignment currentAlignment, List<String> stringList) {
        String result = format(singleLineMaxChars, currentAlignment, stringList);
        return CollectionUtil.splitDelimitedStringToList(result, ConstantUtil.NEXT_LINE, String::toString);
    }

    /**
     * Supplementary space
     *
     * @param to
     * @param howMany
     */
    public static void pad(StringBuilder to, int howMany) {
        for (int i = 0; i < howMany; i++) {
            to.append(ConstantUtil.SPACE);
        }
    }

    /**
     * Split text, especially is paragraph
     *
     * @param text
     * @param singleLineMaxChars
     * @return
     */
    public static List<String> splitInputText(String text, int singleLineMaxChars) {
        List<String> list = new ArrayList<>();
        if (StringUtils.isBlank(text)) {
            return list;
        }
        if (singleLineMaxChars < 0) {
            throw new IllegalArgumentException("singleLineMaxChars must be positive.");
        }
        for (int i = 0; i < text.length(); i = i + singleLineMaxChars) {
            list.add(text.substring(i, Math.min(i + singleLineMaxChars, text.length())));
        }
        return list;
    }

    /**
     * Basic check
     *
     * @param singleLineMaxChars
     * @param align
     */
    public static void checkAlignmentParam(int singleLineMaxChars, Alignment align) {
        if (singleLineMaxChars < 0) {
            throw new IllegalArgumentException("singleLineMaxChars must be positive.");
        }
        if (align != Alignment.LEFT && align != Alignment.CENTER && align != Alignment.RIGHT) {
            throw new IllegalArgumentException("invalid justification arg.");
        }
    }

    /**
     * Format paragraph
     *
     * @param text
     * @param singleLineMaxChars
     * @param currentAlignment
     * @return
     */
    public String formatText(String text, int singleLineMaxChars, Alignment currentAlignment) {
        List<String> stringList = splitInputText(text, singleLineMaxChars);
        return format(singleLineMaxChars, currentAlignment, stringList);
    }

    public enum Alignment {
        LEFT,
        CENTER,
        RIGHT,
        ;
    }

    public enum Algorithm {
        AES("1"),
        PINYIN("2");
        @Getter
        private String value;

        Algorithm(String value) {
            this.value = value;
        }
    }

    public enum ENDOperate {
        /**
         * Fast sqrt magic number in 64 bit.
         */
        ORIGIN("0x5fe6ec85e7de30da"),
        /**
         * Fast sqrt magic number, haha, can you guess it?
         */
        PINYIN("0x5f3759df"),
        ENCRYPTION_AND_DECRYPTION("0"),
        ENCRYPTION("1"),
        DECRYPTION("2"),

        ;
        @Getter
        private String value;

        ENDOperate(String value) {
            this.value = value;
        }
    }
}
