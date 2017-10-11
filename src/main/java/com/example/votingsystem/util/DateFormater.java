package com.example.votingsystem.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Brad on 15.09.2017.
 */
public class DateFormater {

    public static final String DATEPATTERN = "yyyy-MM-dd";
    private static final DateFormat FORMATTER = new SimpleDateFormat(DATEPATTERN);

    public static Date of(String str) {

        Date retVal = null;
        try {
            retVal = FORMATTER.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return retVal;
    }

    public static String of(Date date){
        return FORMATTER.format(date);
    }
}
