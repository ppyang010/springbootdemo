package com.code.My;

import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Optional;

/**
 * @author ccy
 */
public class PriceUtil {

    public static String priceFormat(BigDecimal price) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(price);
    }

    public static String priceFormat(String price) {
        return priceFormat(new BigDecimal(price));
    }

    public static String cent2Yuan(Integer cent) {
        return Optional.ofNullable(cent).map(c -> cent.doubleValue() / 100).map(String::valueOf).map(PriceUtil::priceFormat).orElse("0.00");
    }


    public static String cent2Yuan(Long cent) {
        return Optional.ofNullable(cent).map(c -> cent.doubleValue() / 100).map(String::valueOf).map(PriceUtil::priceFormat).orElse("0.00");
    }


    public static Integer yuan2Cent(String yuan) {
        if (StrUtil.isEmpty(yuan)) {
            return 0;
        }
        return new BigDecimal(yuan).multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    public static Long yuan2CentLong(String yuan) {
        if (StrUtil.isEmpty(yuan)) {
            return 0L;
        }
        return new BigDecimal(yuan).multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
    }

    public static String minus(String minuend, String subtrahend) {
        int amount = yuan2Cent(minuend) - yuan2Cent(subtrahend);
        if (amount < 0) {
            return "0";
        }
        return cent2Yuan(amount);
    }

    public static Integer minusCent(Integer minuend, Integer subtrahend) {
        int amount = minuend - subtrahend;
        if (amount < 0) {
            return 0;
        }
        return amount;
    }

    public static Integer minusCentOrDefault(Integer minuend, Integer subtrahend, Integer defaultCent) {
        int amount = minuend - subtrahend;
        if (amount < 0) {
            return defaultCent;
        }
        return amount;
    }

    public static String add(String... nums) {
        int init = 0;
        for (String num : nums) {
            init += yuan2Cent(num);
        }
        return cent2Yuan(init);
    }

    public static Long centMultiplyPer(Long cent, BigDecimal per) {
        BigDecimal income = new BigDecimal(cent.toString());
        return income.multiply(per).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
    }

    /**
     * 两个价格区间是否有交集
     *
     * @return true 有交集
     */
    public static boolean isOverlap(Integer leftMinPrice, Integer leftMaxPrice, Integer rightMinPrice, Integer rightMaxPrice) {
        return !(leftMaxPrice.compareTo(rightMinPrice) < 0 || leftMinPrice.compareTo(rightMaxPrice) > 0);
    }
}
