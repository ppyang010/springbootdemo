package com.code.example.httpclient;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ccy
 */
@Slf4j
public class StringToDateConverter implements Converter<String, Date> {
    @Nullable
    @Override
    public Date convert(String source) {
        try{
            log.info("StringToDateConverter Convert");
            return DateUtil.parseDateTime(source);
        }catch (Exception e){
            return null;
        }

    }
}
