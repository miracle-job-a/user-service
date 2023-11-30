package com.miracle.userservice.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class DateFormatUtil {

    public static String dateToString(LocalDateTime localDateTime, String format) {
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static String dateToString(LocalDate localDate, String format) {
        return localDate.format(DateTimeFormatter.ofPattern(format));
    }
}
