package com.code.example.httpclient;

import cn.hutool.core.date.DateUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author ccy
 */
@RestController
public class ResponseController {

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        dateFormat.setLenient(false);
//        //true:允许输入空值，false:不能为空值
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//
//    }

    @GetMapping("/response/get")
    public String getResponse(String name) {
        System.out.println("getResponse method!!" + name);
        return "xxx=" + name;
    }


    @PostMapping("/response/post")
    public String postResponse(String name, Date date) {
        System.out.println("postResponse method!!" + name + "====" + DateUtil.formatDate(date));
//        System.out.println("postResponse method!!" + name);
        return "xxx=" + name;
    }
}
