package com.code;

import java.math.BigDecimal;

/**
 * @author ccy
 * @description
 * @time 2018/11/14 下午12:08
 */
public class bigtest {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("1.32").setScale(1, BigDecimal.ROUND_UP);
        System.out.println(bigDecimal.toString());
    }
}
