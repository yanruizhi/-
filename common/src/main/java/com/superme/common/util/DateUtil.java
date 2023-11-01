package com.superme.common.util;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.ObjectUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

/**
 * 时间工具类
 * 作者: yanruizhi
 * 时间: 2023/9/14 17:38
 */
public class DateUtil {

    public static final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public static final DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final DateFormat formatter3 = new SimpleDateFormat("yyyyMMdd");
    public static final DateFormat formatter4 = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final DateFormat formatter5 = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
    public static final DateTimeFormatter formatter6 = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
    public static final String yyyyMMddHHmmss1 = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddHHmmss2 = "yyyyMMddHHmmss";

    public static String parseDate(Date date) {
        if (ObjectUtils.isEmpty(date)) {
            return null;
        }
        return formatter2.format(date);
    }

    public static String parseDate(LocalDateTime date) {
        if (ObjectUtils.isEmpty(date)) {
            return null;
        }
        return formatter2.format(date);
    }

    public static LocalDateTime getLocalDateTime(String timeStr) {
        return LocalDateTime.parse(timeStr,formatter6);
    }

}
