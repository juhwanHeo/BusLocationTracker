package com.bustracker.utils;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String currentTImeToString(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }

    public static LocalTime convertLocalTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:ss");
        return LocalTime.parse(time, formatter);
    }
}

