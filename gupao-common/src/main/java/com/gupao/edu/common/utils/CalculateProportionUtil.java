package com.gupao.edu.common.utils;

import java.text.NumberFormat;

/**
 * <h3>app-backend</h3>
 * <p>百分比 计算 工具类</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-05-05 20:54
 **/
public final class CalculateProportionUtil {

    public final static char DONT = '.';
    /**
     * 计算百分比 整数
     *
     * @param divisor
     * @param dividend
     * @return
     */
    public static String proportionLong(Long divisor, Long dividend) {
        if (dividend == null || divisor == null) {
            return null;
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float) divisor / (float) dividend * 100);
        if (result.indexOf(DONT) != -1) {
            result = Math.round(Double.parseDouble(result)) + "%";
        } else {
            result += "%";
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(proportionLong(150L, 2500L));
        System.out.println(proportionLong(15L, 2500L));
        System.out.println(proportionLong(100L, 1000L));
    }
}
