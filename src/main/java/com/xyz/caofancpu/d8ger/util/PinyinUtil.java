package com.xyz.caofancpu.d8ger.util;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * pinyin4j汉字转拼音工具类
 *
 * @author zhiheng
 */
@Slf4j
public class PinyinUtil {

    /**
     * pinyin4j格式类
     */
    private static HanyuPinyinOutputFormat format = null;

    /**
     * 通过构造方法进行初始化
     * 设置需要转换的拼音格式
     * 以天为例
     * HanyuPinyinToneType.WITHOUT_TONE 转换为tian
     * HanyuPinyinToneType.WITH_TONE_MARK 转换为tian1
     * HanyuPinyinVCharType.WITH_U_UNICODE 转换为tiān
     */
    static {
        format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    /**
     * 对单个字进行转换
     *
     * @param pinYinStr 需转换的汉字字符串
     * @return 拼音字符串数组
     */
    public static String getCharPinYin(char pinYinStr) {
        String[] pinyin = null;
        try {
            // 执行转换
            pinyin = PinyinHelper.toHanyuPinyinStringArray(pinYinStr, format);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            log.error("拼音转化失败", e);
            return null;
        }

        // pinyin4j规则，当转换的符串不是汉字，就返回null
        if (pinyin == null) {
            return null;
        }

        // 多音字会返回一个多音字拼音的数组，pinyiin4j并不能有效判断该字的读音
        return pinyin[0];
    }

    /**
     * 对单个字进行转换
     *
     * @param pinYinStr
     * @return
     */
    public static String getStringPinYin(String pinYinStr) {
        StringBuilder sb = new StringBuilder();
        String tempStr = null;
        // 循环字符串
        for (int i = 0; i < pinYinStr.length(); i++) {

            tempStr = getCharPinYin(pinYinStr.charAt(i));
            if (tempStr == null) {
                // 非汉字直接拼接
                sb.append(pinYinStr.charAt(i));
            } else {
                sb.append(tempStr);
            }
        }
        return sb.toString();
    }
}