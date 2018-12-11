package code;

import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ccy
 * @description
 * @time 2018/11/21 下午2:13
 */
@RestController
public class ProduceController {

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam String name){
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
        Thread.sleep(5000L);
        System.out.println("slow");
        return "slow";
    }
}
