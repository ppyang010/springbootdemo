package com.code;

import com.code.util.Sequence;

/**
 * @author ccy
 * @description
 * @time 2019/3/15 下午3:12
 */
public class GIDTest {
    public static void main(String[] args) {
        Sequence sequence = new Sequence();
        for (int i = 0; i < 10; i++) {
            System.out.println(sequence.nextId());
        }
    }
}
