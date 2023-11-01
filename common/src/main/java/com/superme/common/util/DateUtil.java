package com.superme.common.util;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.ObjectUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

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

    public static void main(String[] args) throws ParseException {
//        LocalDateTime parse = LocalDateTime.parse("Thu Jan 04 16:36:56 CST 2018");
        DateTimeFormatter sdf =  DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.SIMPLIFIED_CHINESE);
        System.out.println(LocalDateTime.parse("Thu Jan 04 16:36:56 CST 2018",sdf));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy",Locale.US);
        LocalDateTime ldt = LocalDateTime.parse("Mon Jul 13 11:48:10 CST 2020",df);
        System.out.println(ldt);

//        String date = "Thu Aug 27 18:05:49 CST 2015";
//        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
//        Date d = sdf.parse(date);
//        String formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
//
//
//        System.out.println(formatDate);

    }
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

    public static LocalDateTime getLocalDateTimeByCST(String CSTStr) {
        if (CSTStr == null) {
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy",Locale.US);
        return LocalDateTime.parse(CSTStr, df);
    }

}
