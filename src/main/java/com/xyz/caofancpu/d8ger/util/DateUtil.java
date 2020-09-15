/*
 * Copyright 2016-2020 the original author
 *
 * @D8GER(https://github.com/caofanCPU).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xyz.caofancpu.d8ger.util;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;


/**
 * Java8时间方法LocalDate和LocalDateTime
 * getYear()                            int         获取当前日期的年份
 * getMonth()                           Month       获取当前日期的月份对象
 * getMonthValue()                      int         获取当前日期是第几月
 * getDayOfWeek()                       DayOfWeek   表示该对象表示的日期是星期几
 * getDayOfMonth()                      int         表示该对象表示的日期是这个月第几天
 * getDayOfYear()                       int         表示该对象表示的日期是今年第几天
 * withYear(int year)                   LocalDate   修改当前对象的年份
 * withMonth(int month)                 LocalDate   修改当前对象的月份
 * withDayOfMonth(int dayOfMonth)       LocalDate   修改当前对象在当月的日期
 * isLeapYear()                         boolean     是否是闰年
 * lengthOfMonth()                      int         这个月有多少天
 * lengthOfYear()                       int         该对象表示的年份有多少天（365或者366）
 * plusYears(long yearsToAdd)           LocalDate   当前对象增加指定的年份数
 * plusMonths(long monthsToAdd)         LocalDate   当前对象增加指定的月份数
 * plusWeeks(long weeksToAdd)           LocalDate   当前对象增加指定的周数
 * plusDays(long daysToAdd)             LocalDate   当前对象增加指定的天数
 * minusYears(long yearsToSubtract)     LocalDate   当前对象减去指定的年数
 * minusMonths(long monthsToSubtract)   LocalDate   当前对象减去注定的月数
 * minusWeeks(long weeksToSubtract)     LocalDate   当前对象减去指定的周数
 * minusDays(long daysToSubtract)       LocalDate   当前对象减去指定的天数
 * compareTo(ChronoLocalDate other)     int         比较当前对象和other对象在时间上的大小，返回值如果为正，则当前对象时间较晚，
 * isBefore(ChronoLocalDate other)      boolean     比较当前对象日期是否在other对象日期之前
 * isAfter(ChronoLocalDate other)       boolean     比较当前对象日期是否在other对象日期之后
 * isEqual(ChronoLocalDate other)       boolean     比较两个日期对象是否相等
 *
 * @author D8GER
 */
@Slf4j
public class DateUtil {
    public final static String DATETIME_FORMAT_SIMPLE = "yyyy-MM-dd HH:mm:ss";
    public final static String DATETIME_FORMAT_DETAIL = "yyyy-MM-dd HH:mm:ss:SSS";
    public final static String DATETIME_FORMAT_CN = "yyyy年MM月dd日HH时mm分ss秒";

    public final static String DATE_FORMAT_SIMPLE = "yyyy-MM-dd";
    public final static String DATE_FORMAT_FOR_BANK = "yyyyMMdd";
    public final static String DATE_FORMAT_CN = "yyyy年MM月dd日";

    public final static String TIME_FORMAT_SIMPLE = "HH:mm:ss";
    public final static String TIME_FORMAT_DETAIL = "HH:mm:ss:SSS";
    public final static String TIME_FORMAT_CN = "HH点mm分ss秒";

    public final static ZoneOffset DEFAULT_ZONE_OFFSET = ZoneOffset.of("+8");

    private final static int MAX_HOUR = 23;
    private final static int MAX_MINUTE_SECOND = 59;
    private final static int MIN_HOUR_MINUTE_SECOND = 0;

    /**
     * 获取当天剩余秒数
     * 翌日00:00:00减去当前时间
     *
     * @return
     */
    public static int getRemainingSecondOfCurrentDay() {
        return getRemainingMilliSecondOfCurrentDay() / 1000;
    }

    /**
     * 获取当天剩余毫秒数
     * 翌日00:00:00减去当前时间
     *
     * @return
     */
    public static int getRemainingMilliSecondOfCurrentDay() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrowZero = now.withDayOfYear(now.getDayOfYear() + 1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
        return (int) (tomorrowZero.toInstant(DEFAULT_ZONE_OFFSET).toEpochMilli() - now.toInstant(DEFAULT_ZONE_OFFSET).toEpochMilli());
    }

    /**
     * java.util.date转LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertFromJavaUtilDate(@NonNull Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), DEFAULT_ZONE_OFFSET);
    }

    /**
     * Long 转换 localDateTime
     */
    public static LocalDateTime toLocalDateTime(@NonNull Long milliSeconds) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), DEFAULT_ZONE_OFFSET);
    }

    /**
     * Long 转换 localDate
     */
    public static LocalDate toLocalDate(@NonNull Long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(DEFAULT_ZONE_OFFSET).toLocalDate();
    }

    /**
     * 转换为java.util.date
     *
     * @param localDateTime
     * @return
     */
    public static Date convertToJavaUtilDate(@NonNull LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(DEFAULT_ZONE_OFFSET).toInstant());
    }

    /**
     * 获取当日查询最早起始日期+时间, yyyy-MM-dd 00:00:00
     *
     * @return
     */
    @Deprecated
    public static Date getTodayQueryStartDate() {
        return parseStandardDateTimeStr(getTodayStartDateTime());
    }

    /**
     * 获取当日查询最晚结束日期+时间, yyyy-MM-dd 23:59:59
     *
     * @return
     */
    @Deprecated
    public static Date getTodayQueryEndDate() {
        return parseStandardDateTimeStr(getTodayEndDateTime());
    }

    /**
     * 获取当日查询最早起始日期+时间, yyyy-MM-dd 00:00:00
     *
     * @return
     */
    public static LocalDateTime getTodayQueryStartLocalDateTime() {
        return LocalDateTime.now()
                .withHour(MIN_HOUR_MINUTE_SECOND)
                .withMinute(MIN_HOUR_MINUTE_SECOND)
                .withSecond(MIN_HOUR_MINUTE_SECOND);
    }

    /**
     * 获取当日查询最晚结束日期+时间, yyyy-MM-dd 23:59:59
     *
     * @return
     */
    public static LocalDateTime getTodayQueryEndLocalDateTime() {
        return LocalDateTime.now()
                .withHour(MAX_HOUR)
                .withMinute(MAX_MINUTE_SECOND)
                .withSecond(MAX_MINUTE_SECOND);
    }

    /**
     * 获取当日最早起始日期+时间, yyyy-MM-dd 00:00:00
     *
     * @return
     */
    public static String getTodayStartDateTime() {
        return getTodayQueryStartLocalDateTime().format(DateTimeFormatter.ofPattern(DATETIME_FORMAT_SIMPLE));
    }

    /**
     * 获取当日最晚结束日期+时间, yyyy-MM-dd 23:59:59
     *
     * @return
     */
    public static String getTodayEndDateTime() {
        return getTodayQueryEndLocalDateTime().format(DateTimeFormatter.ofPattern(DATETIME_FORMAT_SIMPLE));
    }

    /**
     * 检测目标时间段与参考时间段是否重叠
     *
     * @param targetPairDateTimePair
     * @param referPairDateTimePair
     * @return
     */
    public static boolean hasRepeatByLocaleDateTime(MutablePair<LocalDateTime, LocalDateTime> targetPairDateTimePair, MutablePair<LocalDateTime, LocalDateTime> referPairDateTimePair) {
        LocalDateTime targetLeft = targetPairDateTimePair.getLeft();
        LocalDateTime targetRight = targetPairDateTimePair.getRight();
        if (targetLeft.compareTo(targetRight) < 0) {
            targetPairDateTimePair.setLeft(targetRight);
            targetPairDateTimePair.setRight(targetLeft);
            targetLeft = targetPairDateTimePair.getLeft();
            targetRight = targetPairDateTimePair.getRight();
        }
        LocalDateTime referLeft = referPairDateTimePair.getLeft();
        LocalDateTime referRight = referPairDateTimePair.getRight();
        if (referLeft.compareTo(referRight) < 0) {
            referPairDateTimePair.setLeft(referRight);
            referPairDateTimePair.setRight(referLeft);
            referLeft = referPairDateTimePair.getLeft();
            referRight = referPairDateTimePair.getRight();
        }
        return !(targetLeft.compareTo(referRight) >= 0 || targetRight.compareTo(referLeft) <= 0);
    }

    /**
     * 检测目标时间段与参考时间段是否重叠
     *
     * @param targetPairDateTimePair
     * @param referPairDateTimePair
     * @return
     */
    @Deprecated
    public static boolean hasRepeatByJavaUtilDate(MutablePair<Date, Date> targetPairDateTimePair, MutablePair<Date, Date> referPairDateTimePair) {
        Date targetLeft = targetPairDateTimePair.getLeft();
        Date targetRight = targetPairDateTimePair.getRight();
        if (targetLeft.after(targetRight)) {
            targetPairDateTimePair.setLeft(targetRight);
            targetPairDateTimePair.setRight(targetLeft);
            targetLeft = targetPairDateTimePair.getLeft();
            targetRight = targetPairDateTimePair.getRight();
        }
        Date referLeft = referPairDateTimePair.getLeft();
        Date referRight = referPairDateTimePair.getRight();
        if (referLeft.after(referRight)) {
            referPairDateTimePair.setLeft(referRight);
            referPairDateTimePair.setRight(referLeft);
            referLeft = referPairDateTimePair.getLeft();
            referRight = referPairDateTimePair.getRight();
        }
        return !(targetLeft.compareTo(referRight) >= 0 || targetRight.compareTo(referLeft) <= 0);
    }

    /**
     * 检测目标时间段与参考时间段是否重叠
     *
     * @param targetPairDateTimeStrPair
     * @param referPairDateTimeStrPair
     * @return
     */
    public static boolean hasRepeat(MutablePair<String, String> targetPairDateTimeStrPair, MutablePair<String, String> referPairDateTimeStrPair) {
        String targetLeft = targetPairDateTimeStrPair.getLeft();
        String targetRight = targetPairDateTimeStrPair.getRight();
        if (dateTimeAfter(targetLeft, targetRight)) {
            targetPairDateTimeStrPair.setLeft(targetRight);
            targetPairDateTimeStrPair.setRight(targetLeft);
            targetLeft = targetPairDateTimeStrPair.getLeft();
            targetRight = targetPairDateTimeStrPair.getRight();
        }
        String referLeft = referPairDateTimeStrPair.getLeft();
        String referRight = referPairDateTimeStrPair.getRight();
        if (dateTimeAfter(referLeft, referRight)) {
            referPairDateTimeStrPair.setLeft(referRight);
            referPairDateTimeStrPair.setRight(referLeft);
            referLeft = referPairDateTimeStrPair.getLeft();
            referRight = referPairDateTimeStrPair.getRight();
        }
        return !(targetLeft.compareTo(referRight) >= 0 || targetRight.compareTo(referLeft) <= 0);
    }

    /**
     * 获取系统当前日期+时间
     *
     * @param format
     * @return
     */
    public static String getCurrentDateTime(String format) {
        if (StringUtils.isBlank(format)) {
            format = DATETIME_FORMAT_SIMPLE;
        }
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 获取系统当前日期
     *
     * @param format
     * @return
     */
    public static String getCurrentDate(String format) {
        if (StringUtils.isBlank(format)) {
            format = DATE_FORMAT_SIMPLE;
        }
        return LocalDate.now().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 获取系统当前时间
     *
     * @param format
     * @return
     */
    public static String getCurrentTime(String format) {
        if (StringUtils.isBlank(format)) {
            format = TIME_FORMAT_SIMPLE;
        }
        return LocalTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * java.util.date转字符串
     *
     * @param date
     * @param format
     * @return String
     */
    @Deprecated
    public static String parseJavaUtilDate(@NonNull Date date, String format) {
        if (StringUtils.isBlank(format)) {
            format = DATETIME_FORMAT_SIMPLE;
        }
        return LocalDateTime.ofInstant(date.toInstant(), DEFAULT_ZONE_OFFSET).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 将日期+时间转换为java.util.date
     *
     * @param dateTimeStr
     * @return
     */
    @Deprecated
    public static Date parseStandardDateTimeStr(String dateTimeStr) {
        return new Date(parseStandardMilliSeconds(dateTimeStr));
    }

    /**
     * 将日期+时间转换为毫秒
     *
     * @param dateTimeStr
     * @return
     */
    public static Long parseStandardMilliSeconds(String dateTimeStr) {
        return parseStandardDateTime(dateTimeStr).toInstant(DEFAULT_ZONE_OFFSET).toEpochMilli();
    }

    /**
     * 将日期+时间转换为秒
     *
     * @param dateTimeStr
     * @return
     */
    public static long parseStandardSeconds(String dateTimeStr) {
        return parseStandardDateTime(dateTimeStr).toEpochSecond(DEFAULT_ZONE_OFFSET);
    }

    /**
     * 将日期+时间转换为标准格式字符串
     *
     * @param dateTimeStr
     * @return
     */
    public static LocalDateTime parseStandardDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(DATETIME_FORMAT_SIMPLE));
    }

    /**
     * 将日期转换为标准格式字符串
     *
     * @param dateStr
     * @return
     */
    public static LocalDate parseStandardDate(String dateStr) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DATE_FORMAT_SIMPLE));
    }

    /**
     * 将时间转换为标准格式字符串
     *
     * @param timeStr
     * @return
     */
    public static LocalTime parseStandardTime(String timeStr) {
        return LocalTime.parse(timeStr, DateTimeFormatter.ofPattern(TIME_FORMAT_SIMPLE));
    }

    /**
     * 日期+时间字符串, 由标准格式转换为其他格式
     *
     * @param dateTimeStr
     * @param format
     * @return
     */
    public static String convertDateTimeFormat(String dateTimeStr, String format) {
        if (StringUtils.isBlank(dateTimeStr) || StringUtils.isBlank(format) || DATETIME_FORMAT_SIMPLE.equals(format)) {
            return dateTimeStr;
        }
        return parseStandardDateTime(dateTimeStr).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 日期字符串, 由标准格式转换为其他格式
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static String convertDateFormat(String dateStr, String format) {
        if (StringUtils.isBlank(dateStr) || StringUtils.isBlank(format) || DATE_FORMAT_SIMPLE.equals(format)) {
            return dateStr;
        }
        return parseStandardDate(dateStr).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 时间字符串, 由标准格式转换为其他格式
     *
     * @param timeStr
     * @param format
     * @return
     */
    public static String convertTimeFormat(String timeStr, String format) {
        if (StringUtils.isBlank(timeStr) || StringUtils.isBlank(format) || TIME_FORMAT_SIMPLE.equals(format)) {
            return timeStr;
        }
        return parseStandardTime(timeStr).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 判断当年
     *
     * @param dateTimeStr
     * @return
     */
    public static boolean isCurrentYear(String dateTimeStr) {
        if (StringUtils.isBlank(dateTimeStr)) {
            return Boolean.FALSE;
        }
        return LocalDate.now().getYear() == parseStandardDateTime(dateTimeStr).getYear();
    }

    /**
     * 判断当月
     *
     * @param dateTimeStr
     * @return
     */
    public static boolean isCurrentMonth(String dateTimeStr) {
        if (StringUtils.isBlank(dateTimeStr)) {
            return Boolean.FALSE;
        }
        LocalDateTime strLocalDateTime = parseStandardDateTime(dateTimeStr);
        return LocalDate.now().getYear() == strLocalDateTime.getYear() && LocalDate.now().getMonth() == strLocalDateTime.getMonth();
    }

    /**
     * 判断当天
     *
     * @param dateTimeStr
     * @return
     */
    public static boolean isCurrentDay(String dateTimeStr) {
        if (StringUtils.isBlank(dateTimeStr)) {
            return Boolean.FALSE;
        }
        LocalDateTime strLocalDateTime = parseStandardDateTime(dateTimeStr);
        return LocalDate.now().getYear() == strLocalDateTime.getYear() && MonthDay.from(LocalDate.now()).equals(MonthDay.from(strLocalDateTime));
    }

    /**
     * 对日期+时间, 演算年份
     *
     * @param dateTimeStr
     * @param format      对演算结果格式化
     * @param years
     */
    public static String getAndAddYear(String dateTimeStr, String format, int years) {
        if (StringUtils.isBlank(dateTimeStr)) {
            return dateTimeStr;
        }
        if (StringUtils.isBlank(format)) {
            format = DATETIME_FORMAT_SIMPLE;
        }
        if (years == 0) {
            return convertDateTimeFormat(dateTimeStr, format);
        }
        return parseStandardDateTime(dateTimeStr).plusYears(years).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 对日期+时间, 演算月份
     *
     * @param dateTimeStr
     * @param format      对演算结果格式化
     * @param months
     */
    public static String getAndAddMonth(String dateTimeStr, String format, int months) {
        if (StringUtils.isBlank(dateTimeStr)) {
            return dateTimeStr;
        }
        if (StringUtils.isBlank(format)) {
            format = DATETIME_FORMAT_SIMPLE;
        }
        if (months == 0) {
            return convertDateTimeFormat(dateTimeStr, format);
        }
        return parseStandardDateTime(dateTimeStr).plusMonths(months).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 对日期+时间, 演算周
     *
     * @param dateTimeStr
     * @param format      对演算结果格式化
     * @param weeks
     */
    public static String getAndAddWeek(String dateTimeStr, String format, int weeks) {
        if (StringUtils.isBlank(dateTimeStr)) {
            return dateTimeStr;
        }
        if (StringUtils.isBlank(format)) {
            format = DATETIME_FORMAT_SIMPLE;
        }
        if (weeks == 0) {
            return convertDateTimeFormat(dateTimeStr, format);
        }
        return parseStandardDateTime(dateTimeStr).plusWeeks(weeks).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 对日期+时间, 演算天
     *
     * @param dateTimeStr
     * @param format      对演算结果格式化
     * @param day
     */
    public static String getAndAddDay(String dateTimeStr, String format, int day) {
        if (StringUtils.isBlank(dateTimeStr)) {
            return dateTimeStr;
        }
        if (StringUtils.isBlank(format)) {
            format = DATETIME_FORMAT_SIMPLE;
        }
        if (day == 0) {
            return convertDateTimeFormat(dateTimeStr, format);
        }
        return parseStandardDateTime(dateTimeStr).plusDays(day).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 对日期+时间, 演算小时
     *
     * @param dateTimeStr
     * @param format      对演算结果格式化
     * @param hour
     * @return
     */
    public static String getAndAddHour(String dateTimeStr, String format, int hour) {
        if (StringUtils.isBlank(dateTimeStr)) {
            return dateTimeStr;
        }
        if (StringUtils.isBlank(format)) {
            format = DATETIME_FORMAT_SIMPLE;
        }
        if (hour == 0) {
            return convertDateTimeFormat(dateTimeStr, format);
        }
        return parseStandardDateTime(dateTimeStr).plusHours(hour).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 对日期+时间, 演算分钟
     *
     * @param dateTimeStr
     * @param format      对演算结果格式化
     * @param minutes
     * @return
     */
    public static String getAndAddMinute(String dateTimeStr, String format, int minutes) {
        if (StringUtils.isBlank(dateTimeStr)) {
            return dateTimeStr;
        }
        if (StringUtils.isBlank(format)) {
            format = DATETIME_FORMAT_SIMPLE;
        }
        if (minutes == 0) {
            return convertDateTimeFormat(dateTimeStr, format);
        }
        return parseStandardDateTime(dateTimeStr).plusMinutes(minutes).format(DateTimeFormatter.ofPattern(format));
    }


    /**
     * 对日期+时间, 演算秒钟
     *
     * @param dateTimeStr
     * @param format      对演算结果格式化
     * @param seconds
     * @return
     */
    public static String getAndAddSecond(String dateTimeStr, String format, int seconds) {
        if (StringUtils.isBlank(dateTimeStr)) {
            return dateTimeStr;
        }
        if (StringUtils.isBlank(format)) {
            format = DATETIME_FORMAT_SIMPLE;
        }
        if (seconds == 0) {
            return convertDateTimeFormat(dateTimeStr, format);
        }
        return parseStandardDateTime(dateTimeStr).plusSeconds(seconds).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 比较目标日期+时间是否早于参考日期+时间
     *
     * @param targetDateTimeStr
     * @param referDateTimeStr
     * @return
     */
    public static boolean dateTimeBefore(String targetDateTimeStr, String referDateTimeStr) {
        return parseStandardDateTime(targetDateTimeStr).compareTo(parseStandardDateTime(referDateTimeStr)) < 0;
    }

    /**
     * 比较目标日期+时间是否晚于参考日期+时间
     *
     * @param targetDateTimeStr
     * @param referDateTimeStr
     * @return
     */
    public static boolean dateTimeAfter(String targetDateTimeStr, String referDateTimeStr) {
        return !dateTimeBefore(targetDateTimeStr, referDateTimeStr);
    }

    /**
     * 比较目标日期是否早于参考日期
     *
     * @param targetDateStr
     * @param referDateStr
     * @return
     */
    public static boolean dateBefore(String targetDateStr, String referDateStr) {
        return parseStandardDate(targetDateStr).compareTo(parseStandardDate(referDateStr)) < 0;
    }

    /**
     * 比较目标日期是否晚于参考日期
     *
     * @param targetDateStr
     * @param referDateStr
     * @return
     */
    public static boolean dateAfter(String targetDateStr, String referDateStr) {
        return !dateTimeBefore(targetDateStr, referDateStr);
    }

    /**
     * 比较目标时间是否早于参考时间
     *
     * @param targetTimeStr
     * @param referTimeStr
     * @return
     */
    public static boolean timeBefore(String targetTimeStr, String referTimeStr) {
        return parseStandardTime(targetTimeStr).compareTo(parseStandardTime(referTimeStr)) < 0;
    }

    /**
     * 比较目标日期是否晚于参考日期
     *
     * @param targetTimeStr
     * @param referTimeStr
     * @return
     */
    public static boolean timeAfter(String targetTimeStr, String referTimeStr) {
        return !dateTimeBefore(targetTimeStr, referTimeStr);
    }

    /**
     * 计算日期时间间隔, 以DAY为单位计算
     *
     * @param dateTimeStart
     * @param dateTimeEnd
     */
    public static int dateTimeDiffDays(String dateTimeStart, String dateTimeEnd) {
        long between = ChronoUnit.DAYS.between(parseStandardDateTime(dateTimeStart), parseStandardDateTime(dateTimeEnd));
        return (int) between;
    }

    /**
     * 计算日期间隔, 以DAY为单位计算
     *
     * @param dateStart
     * @param dateEnd
     */
    public static int dateDiffDays(String dateStart, String dateEnd) {
        long between = ChronoUnit.DAYS.between(parseStandardDate(dateStart), parseStandardDate(dateEnd));
        return (int) between;
    }

    /**
     * 计算时间间隔, 以HOUR为单位计算
     *
     * @param timeStart
     * @param timeEnd
     */
    public static int timeDiffHours(String timeStart, String timeEnd) {
        long between = ChronoUnit.HOURS.between(parseStandardTime(timeStart), parseStandardTime(timeEnd));
        return (int) between;
    }

}
