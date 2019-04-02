package com.code;

import org.springframework.util.LinkedCaseInsensitiveMap;

/**
 * @author ccy
 * @description
 * @time 2019/4/2 上午11:16
 */
public class LinkedCaseInsensitiveMapTest {

    /**
     * https://blog.csdn.net/z69183787/article/details/38382645
     * @param args
     */
    public static void main(String[] args) {
        LinkedCaseInsensitiveMap map = new LinkedCaseInsensitiveMap();

        map.put("DaDa","dada");

        System.out.println(map.get("dada"));
    }
}
