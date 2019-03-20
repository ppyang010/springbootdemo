package code;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import code.dto.DateParamDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
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
     * 测试接受日期参数和返回日期参数
     */
    @PostMapping("/dateParam")
    public DateParamDTO dateParam(@RequestBody DateParamDTO dto) {

        System.out.println(DateUtil.format(dto.getDateParam(), "yyyy-M-dd"));
        dto.setDateReturn(DateUtil.offsetDay(dto.getDateParam(),10));
        return dto;
    }
}
