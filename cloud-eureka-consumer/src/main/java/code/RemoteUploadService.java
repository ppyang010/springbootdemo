package code;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ccy
 * @description
 * @time 2018/11/21 下午3:00
 *
 * name:远程服务名，即spring.application.name配置的名称
 */
@FeignClient(name= "could-eureka-producer",configuration = MultipartSupportConfig.class)
public interface RemoteUploadService {

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String handleFileUpload(@RequestPart(value = "file") MultipartFile file);
}
