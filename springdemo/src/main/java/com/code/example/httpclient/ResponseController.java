package com.code.example.httpclient;

import cn.hutool.core.date.DateUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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


    /**
     * @RequestPart 支持文件上传的文件参数接受 也支持常规参数接受 并且使用这个注解后所有参数都要添加这个注解才能获取到值
     * https://blog.csdn.net/wd2014610/article/details/79727061
     */
    @PostMapping("/response/upload")
    public String uploadResponse(@RequestPart(value = "sqlFile", required = false) MultipartFile file, @RequestPart Date date) {
        byte[] bytes = {};
        try {
            bytes = file.getBytes();
            System.out.println("file bytes size = " + bytes.length + "byte");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fileName=" + file.getName() + "file bytes size = " + bytes.length + "byte" + "|date= " + DateUtil.formatDateTime(date);
    }


    @GetMapping("/response/download")
    public ResponseEntity<Resource> downloadResponse() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:db.sql");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return DownloadResponseUtil.downloadResponse(file, "db.sql");
    }
}
