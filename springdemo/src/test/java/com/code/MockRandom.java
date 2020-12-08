package com.code;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ccy
 * @description
 * @time 2020/12/3 10:22 上午
 */
public class MockRandom {
    public static void main(String[] args) {
//        int x = 90;
//        for (int i = 0; i < 10; i++) {
//            random(x);
//        }
//        BigDecimal base = new BigDecimal("0.95");
//        for (int i = 0; i < 10; i++) {
//           base = base.multiply(new BigDecimal("0.95"));
//        }
//        System.out.println(base);
        cal();
    }

    public static void cal() {
        BigDecimal base = new BigDecimal("0.9427");//东
        base = base.multiply(new BigDecimal("0.9975"));//和光
        base = base.multiply(new BigDecimal("0.9217"));//春和
        base = base.multiply(new BigDecimal("0.867"));//联发现房
        base = base.multiply(new BigDecimal("0.8921"));//桃源滨江
        base = base.multiply(new BigDecimal("0.515"));//联发北秀
        base = base.multiply(new BigDecimal("0.9531"));//荣望
        base = base.multiply(new BigDecimal("0.906"));//展望预估

        System.out.println(base);
    }

    public static void random(int x) {
        int r = ThreadLocalRandom.current().nextInt(1000);
        System.out.println("r=" + r);
        System.out.println(r <= x);
    }
}
