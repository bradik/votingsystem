package com.example.votingsystem.util;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Brad on 15.09.2017.
 */
public class DateTimeUtil {

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    public static LocalDate of(String str) {

        return StringUtils.isEmpty(str) ? null : LocalDate.parse(str);
    }

    public static String of(LocalDate date) {
        return date == null ? "" : date.format(DATE_TIME_FORMATTER);
    }
}
