package com.code;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * 临时用测试类
 * @author ccy
 */
public class TempTest {
    public static void main(String[] args) {
        Date now = new Date();
        DateTime day = DateUtil.offsetDay(now, -2711);
        System.out.println(day);
    }
}
