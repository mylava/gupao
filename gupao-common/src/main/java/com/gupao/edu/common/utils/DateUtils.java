package com.gupao.edu.common.utils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @description
 * @author: chenlong
 * @data: 2020-04-30 14:13
 **/
public class DateUtils {

    /**
     * 根据传入的时间和现在的时间计算天数
     * @param end
     * @return
     */
    public static long countDaysByNowAndParam(LocalDate end){
        LocalDate now = LocalDate.now();
        return now.until(end, ChronoUnit.DAYS);
    }

    public static void main(String[] args) {
        System.out.println(countDaysByNowAndParam(LocalDate.parse("2020-05-01")));
    }
}
