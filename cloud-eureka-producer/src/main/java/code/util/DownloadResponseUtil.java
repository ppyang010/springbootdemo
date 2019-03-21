package code.util;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author ccy
 * @description
 * @time 2019/3/21 下午7:12
 */
public class DownloadResponseUtil {


    /**
     * https://my.oschina.net/songxinqiang/blog/898901
     */
    public static ResponseEntity<Resource> downloadResponse(File file, String fileName) {
        Resource body = new FileSystemResource(file);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String header = request.getHeader("User-Agent").toUpperCase();
        HttpStatus status = HttpStatus.CREATED;

        try {
            if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
                //方案1
//                fileName = URLEncoder.encode(fileName, "UTF-8");
//                // IE下载文件名空格变+号问题
//                fileName = fileName.replace("+", "%20");

                //方案2
                fileName = UriUtils.encode(fileName, "UTF-8");
                status = HttpStatus.OK;
            } else {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            }
        } catch (UnsupportedEncodingException e) {
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentLength(file.length());

        return new ResponseEntity<>(body, headers, status);
    }

    public static ResponseEntity<byte[]> downloadResponseWithByte(File file, String fileName) {
//        Resource body = new FileSystemResource(file);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String header = request.getHeader("User-Agent").toUpperCase();
        HttpStatus status = HttpStatus.CREATED;

        InputStream in = null;


        byte[] body = null;
        try {
            in = new FileInputStream(file);

            body = new byte[in.available()];

            if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
                //方案1
//                fileName = URLEncoder.encode(fileName, "UTF-8");
//                // IE下载文件名空格变+号问题
//                fileName = fileName.replace("+", "%20");

                //方案2
                fileName = UriUtils.encode(fileName, "UTF-8");
                status = HttpStatus.OK;
            } else {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            }
        } catch (Exception e) {
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentLength(file.length());

        return new ResponseEntity<>(body, headers, status);
    }
}
