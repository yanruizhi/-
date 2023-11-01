package com.superme.common.util;

import org.springframework.util.ObjectUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public static final DateTimeFormatter formatter6 = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");

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
        if (CSTStr.startsWith("星期")) {
//            星期日 七月 26 15:32:39 +08:00 2020
//            Fri Jan 01 08:00:00 CST 1904
            CSTStr = changeType(CSTStr);

        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy",Locale.US);
        return LocalDateTime.parse(CSTStr, df);
    }

    private static String changeType(String timeStr) {
        //            星期日 七月 26 15:32:39 +08:00 2020
        //            Fri Jan 01 08:00:00 CST 1904
        return timeStr.replace("星期一", "Mon")
                .replace("星期二", "Tue")
                .replace("星期三", "Wed")
                .replace("星期四", "Thu")
                .replace("星期五", "Fri")
                .replace("星期六", "Sat")
                .replace("星期日", "Sun")
                .replace("星期天", "Sun")
                .replace("十一月", "Nov")
                .replace("十二月", "Dec")
                .replace("一月", "Jan")
                .replace("二月", "Feb")
                .replace("三月", "Mar")
                .replace("四月", "Apr")
                .replace("五月", "May")
                .replace("六月", "Jun")
                .replace("七月", "Jul")
                .replace("八月", "Aug")
                .replace("九月", "Sep")
                .replace("十月", "Oct")
                .replace("+08:00", "CST");
    }
    public static void main(String[] args) throws ParseException {
        LocalDateTime time = getLocalDateTimeByCST("星期五 十二月 01 15:32:39 +08:00 2023");
        System.out.println(time);

    }
}
