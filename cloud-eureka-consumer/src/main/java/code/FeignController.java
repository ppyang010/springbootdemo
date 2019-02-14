package code;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;

/**
 * @author ccy
 * @description
 * @time 2018/11/21 下午2:13
 */

@RestController
public class FeignController {
    @Autowired
    private RemoteService remoteService;
    @Autowired
    private RemoteUploadService remoteUploadService;

    /**
     * Feign 方式
     * http://blog.didispace.com/spring-cloud-starter-dalston-2-3/
     */
    @GetMapping("/hello1")
    public String hello1(@RequestParam String name) {
        return remoteService.hello(name);
    }

    /**
     * upload文件
     * http://blog.didispace.com/spring-cloud-starter-dalston-2-4/
     */
    @GetMapping("/upload")
    public String upload() {

        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:upload.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //fileName 要与方法中的变量名相同  下面远程调用的方法中的@RequestPart(value = "file") 这个value值
        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                MediaType.TEXT_PLAIN_VALUE, true, file.getName());

        try (InputStream input = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()) {
            IOUtils.copy(input, os);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }

        MultipartFile multi = new CommonsMultipartFile(fileItem);

        return remoteUploadService.handleFileUpload(multi);
    }
}
