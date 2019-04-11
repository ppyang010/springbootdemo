package com.code.example.httpclient;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author ccy
 * @description
 * @time 2019/4/11 下午12:02
 */
@Slf4j
public class TimeStmpToDateConverter implements Converter<String, Date> {
    @Nullable
    @Override
    public Date convert(String source) {
        try {
            log.info("Timestamp Convert");
            return new Date(Long.valueOf(source));
        } catch (Exception e) {
            return null;
        }

    }
}