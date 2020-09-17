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

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;


/**
 * LocalDateTime-Data converter and handler
 *
 * @author D8GER
 */
@Slf4j
public class DateUtil {
    public static final Pattern DATE_TIME_REGEX = Pattern.compile("(?:[Tt])+");
    public final static String DATETIME_FORMAT_SIMPLE = "yyyy-MM-dd HH:mm:ss";
    public final static String DATETIME_FORMAT_DETAIL = "yyyy-MM-dd HH:mm:ss:SSS";
    public final static String DATETIME_FORMAT_DETAIL_DOT = "yyyy-MM-dd HH:mm:ss.SSS";
    public final static String DATETIME_FORMAT_CN = "yyyy年MM月dd日HH时mm分ss秒";

    public final static ZoneOffset DEFAULT_ZONE_OFFSET = ZoneOffset.of("+8");

    /**
     * Long to LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(@NonNull Long milliSeconds) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), DEFAULT_ZONE_OFFSET);
    }

    /**
     * String of date and time, for example, 2020-09-14 15:45:57 convert to Long: milliseconds
     *
     * @param dateTimeStr
     * @return
     */
    public static Long parseStandardMilliSeconds(String dateTimeStr) {
        return parseStandardDateTime(dateTimeStr).toInstant(DEFAULT_ZONE_OFFSET).toEpochMilli();
    }

    /**
     * String of date and time, for example, 2020-09-14 15:45:57 convert to LocalDateTime
     *
     * @param dateTimeStr
     * @return
     */
    public static LocalDateTime parseStandardDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(DATETIME_FORMAT_SIMPLE));
    }

    /**
     * Long to LocalDateTimeString
     */
    public static String enhanceToLocalDateTime(@NonNull Long milliSeconds) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), DEFAULT_ZONE_OFFSET);
        return localDateTime.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT_DETAIL_DOT));
    }

    /**
     * String of date and time, convert to String: milliseconds
     * for example:
     * 2020-09-14 15:45:57
     * 2020-09-14T15:45:57
     * 2020-09-14t15:45:57
     * 2020-09-14 15:45:57.007
     * 2020-09-14 15:45:57:007
     * 2020年09月14日15时45分57秒
     *
     * @param dateTimeStr
     * @return
     */
    public static String enhanceParseMilliSeconds(String dateTimeStr) {
        if (StringUtils.isBlank(dateTimeStr)) {
            return dateTimeStr;
        }
        String originWord = dateTimeStr.replaceAll(DATE_TIME_REGEX.pattern(), ConstantUtil.SPACE);
        LocalDateTime parse = null;
        try {
            parse = LocalDateTime.parse(originWord, DateTimeFormatter.ofPattern(DATETIME_FORMAT_SIMPLE));
        } catch (Exception e) {
            try {
                parse = LocalDateTime.parse(originWord, DateTimeFormatter.ofPattern(DATETIME_FORMAT_DETAIL));
            } catch (Exception exception) {
                try {
                    parse = LocalDateTime.parse(originWord, DateTimeFormatter.ofPattern(DATETIME_FORMAT_DETAIL_DOT));
                } catch (Exception ex) {
                    parse = LocalDateTime.parse(originWord, DateTimeFormatter.ofPattern(DATETIME_FORMAT_CN));
                }
            }
        }
        return Objects.nonNull(parse) ? String.valueOf(parse.toInstant(DEFAULT_ZONE_OFFSET).toEpochMilli()) : dateTimeStr;
    }

}
