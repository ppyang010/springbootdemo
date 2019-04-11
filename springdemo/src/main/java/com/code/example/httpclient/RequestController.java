package com.code.example.httpclient;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author ccy
 */
@RestController
public class RequestController {

    public static final String HOST = "http://127.0.0.1:8011";


    @Autowired
    @Qualifier("httpclientRestTemplate")
    private RestTemplate httpclientRest;

    @GetMapping("/request/get")
    public String getRequest(String name) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        return httpclientRest.getForObject(buildGetRequestUrl(HOST, "/response/get", map), String.class);
    }

    @GetMapping("/request/post")
    public String postRequest(String name) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        String formatDateTime = DateUtil.formatDateTime(new Date());
        map.put("date", new Date());
        return httpclientRest.postForObject(buildGetRequestUrl(HOST, "/response/post", null), createMultiValueMap(map), String.class);
    }


    private static URI buildGetRequestUrl(String host, String uri, Map<String, Object> jsonObject) {
        try {
            URIBuilder builder = new URIBuilder(host + uri);
            builder.setCharset(Charset.forName("UTF-8"));
            if (Objects.nonNull(jsonObject)) {
                for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                    builder.setParameter(entry.getKey(), ObjectUtil.defaultIfNull(entry.getValue(), "").toString());
                }
            }
            return builder.build();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }


    private static MultiValueMap<String, Object> createMultiValueMap(Map<String, Object> params) {
        final MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        if (params != null) {
            params.forEach((k, v) -> multiValueMap.add(k, (v instanceof File) ? new FileSystemResource((File) v) : v));
        }
        return multiValueMap;
    }
}
