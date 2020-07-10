package com.code;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.code.data.hello.People;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * 临时用测试类
 * @author ccy
 */
@Slf4j
public class TempTest {
    public static void main(String[] args) {
//        Date now = new Date();
//        DateTime day = DateUtil.offsetDay(now, -2711);
//        System.out.println(day);

        ArrayList<Integer> objects = Lists.newArrayList();
        objects.add(11);
        objects.add(12);
        objects.add(13);
        objects.add(14);
        objects.add(15);
        objects.stream().map(x->{
            log.info("map1");
            People people = new People();
            people.setAge(x);
            return people;
        }).map(x->{
            log.info("map2");
            x.setName("ccc"+x.getAge());
            return x;
        }).filter(x->{
            log.info("filter1");
            return x.getAge()>12;
        }).collect(Collectors.toList());

    }
}
