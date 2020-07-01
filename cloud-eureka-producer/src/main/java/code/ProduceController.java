package code;

import cn.hutool.core.date.DateUtil;
import code.dto.DateParamDTO;
import code.util.DownloadResponseUtil;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Random;

/**
 * @author ccy
 * @description
 * @time 2018/11/21 下午2:13
 */
@RestController
public class ProduceController {

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam String name) {
        String s = "hello " + name;
        System.out.println(s);
        return s;
    }

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleFileUpload(@RequestPart(value = "file") MultipartFile file) {
        return file.getName();
    }

    /**
     * 触发降级
     */
    @RequestMapping(value = "/slow")
    public String dc() throws InterruptedException {
        //处理线程等待
        int sleepTime = new Random().nextInt(3000);
        System.out.println("sleepTime = " + sleepTime);
        Thread.sleep(sleepTime);
        System.out.println("slow");
        return "slow";
    }


    /**
     * 触发降级
     */
    @RequestMapping(value = "/slow/{sleepTime}")
    public String slow2(@PathVariable("sleepTime") Integer sleepTime) throws InterruptedException {
        //处理线程等待
        System.out.println("sleepTime = " + sleepTime);
        Thread.sleep(sleepTime);
        System.out.println("slow2");
        return "slow2";
    }

    /**
     * 测试接受日期参数和返回日期参数
     */
    @PostMapping("/dateParam")
    public DateParamDTO dateParam(@RequestBody DateParamDTO dto) {

        System.out.println(DateUtil.format(dto.getDateParam(), "yyyy-M-dd"));
        dto.setDateReturn(DateUtil.offsetDay(dto.getDateParam(), 10));
        return dto;
    }

    /**
     *  提供文件下载接口
     */
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:download.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return DownloadResponseUtil.downloadResponse(file, "download.txt");
    }

    /**
     *  提供文件下载接口
     */
    @GetMapping("/download/byte")
    public ResponseEntity<byte[]> downloadFileWithByte() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:download.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return DownloadResponseUtil.downloadResponseWithByte(file, "download.txt");
    }



}
