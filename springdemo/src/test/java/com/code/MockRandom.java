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
//        int times = 0;
//        for (int i = 0; i < 10000; i++) {
//            if (random(x)) {
//                times++;
//            }
//        }
//        System.out.println("times=" + times);
//        BigDecimal base = new BigDecimal("0.95");
//        for (int i = 0; i < 10; i++) {
//            base = base.multiply(new BigDecimal("0.95"));
//        }
//        System.out.println(base);
        cal();
    }

    public static void cal() {
        BigDecimal base = new BigDecimal("0.9427");//1东
        base = base.multiply(new BigDecimal("0.9975"));//2 和光
        base = base.multiply(new BigDecimal("0.9217"));//3 春和
        base = base.multiply(new BigDecimal("0.867"));//4 联发现房
        base = base.multiply(new BigDecimal("0.8921"));//5 滨江沁语 桃源
        base = base.multiply(new BigDecimal("0.515")); //6 联发北秀
        base = base.multiply(new BigDecimal("0.9531"));//7 荣望轩
        base = base.multiply(new BigDecimal("0.906")); //8  融信展望
        base = base.multiply(new BigDecimal("0.7946"));//9 保利潮起云上府  中签率20.54%
        base = base.multiply(new BigDecimal("0.935")); //10  大家天筑  中签率6.5%
        base = base.multiply(new BigDecimal("0.769"));  //11 滨江沁语 桃源板块   预计中签率23.1%


        System.out.println(base);
    }

    public static boolean random(int x) {
        int r = ThreadLocalRandom.current().nextInt(1000);
        System.out.println("r=" + r);
        System.out.println(r <= x);
        return r <= x;
    }
}
