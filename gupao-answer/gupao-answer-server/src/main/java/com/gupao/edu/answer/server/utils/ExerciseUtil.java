package com.gupao.edu.answer.server.utils;

import java.util.Calendar;
import java.util.Date;

public class ExerciseUtil {

    public static int getYear(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int res = year;
        return res;
    }

    public static int getMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH)+1;
        int res = month;
        return res;
    }

    public static int getDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DATE);
        int res = day;
        return res;
    }

    public static long daysBetween(Date bigDate, Date smallDate) {
        long difference =  (bigDate.getTime()-smallDate.getTime())/86400000;
        return Math.abs(difference);
    }

}
