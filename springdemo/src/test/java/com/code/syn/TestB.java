package com.code.syn;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class TestB {
    public static void main(String[] args) {
        ArrayList<String> objects = new ArrayList<>();
        objects.add("a");
        objects.add("b");
        objects.add("c");
        objects.add("d");
//        Iterator<Integer> iterator = objects.iterator();
//        while(iterator.hasNext()){
//            Integer next = iterator.next();
//            log.info(next.toString());
//            if(2==next){
//                iterator.remove();
//            }
//        }

//        for (Integer object : objects) {
//            if(2==object){
//                objects.remove(object);
//            }
//            log.info(object.toString());
//        }

        for (int i = 0, lasti = -1; i < objects.size(); i++, lasti++) {
            String v = objects.get(i);
            log.info(v);
            if ("a".equals(v)) {
                objects.remove(i);
                if (lasti < 0) {
                    i = 0;
                } else {
                    i = lasti;
                }
            }

        }
        log.info(JSONUtil.toJsonStr(objects));

    }
}
