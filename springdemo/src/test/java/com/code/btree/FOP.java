package com.code.btree;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ccy
 * @description
 * @time 2021/6/13 4:38 下午
 */
public class FOP {

    public static final FopObject FOP_OBJECT = new FopObject();
    public static final String FOP_STR = "fopStr";
    static {
        System.out.println("static {}");
        System.out.println(FOP_OBJECT);
    }

    public static void main(String[] args) {
        System.out.println(FOP_OBJECT);
//        new ConcurrentHashMap();
    }

    public static class FopObject {
        public FopObject() {
            System.out.println("FopObject()");
        }
    }
}
