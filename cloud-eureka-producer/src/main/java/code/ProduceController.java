package code;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
}
