package com.code.auth.web.filter;

import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

/**
 * http request类 流重复读包装类
 * <p>
 * <p>
 * 原理参考
 * https://www.jianshu.com/p/f7f8237861e7
 * https://blog.csdn.net/wangjun5159/article/details/48996109
 * https://www.cnblogs.com/yueli/p/7986009.html
 *
 * @author ccy
 */
public class BodyRepeatReadHttpServletRequestWrapper extends HttpServletRequestWrapper {
    /**
     * HTTP请求GET/POST与参数小结
     * https://alanli7991.github.io/2016/10/26/HTTP%E8%AF%B7%E6%B1%82GETPOST%E4%B8%8E%E5%8F%82%E6%95%B0%E5%B0%8F%E7%BB%93/
     * <p>
     * url上的参数和content-type=application/x-www-form-urlencoded的参数会解析到ParameterMap中
     * requestParam 参数缓存
     */
    private Map<String, String[]> params;
    /**
     * body 数据字节流 缓存
     */
    private byte[] requestBody;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public BodyRepeatReadHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        params = request.getParameterMap();
        requestBody = StreamUtils.copyToByteArray(request.getInputStream());

    }

    @Override
    public ServletInputStream getInputStream() {

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody);

        return new ServletInputStream() {

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }


    @Override
    public String getParameter(String name) {
        if (params.get(name) != null && params.get(name).length >= 1) {
            return params.get(name)[0];
        }
        return super.getParameter(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }

    /**
     * 重写获取参数名称方法，可以在保证参数转换的同时获取流信息
     */
    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(params.keySet());
    }

    /**
     * 重写获取参数方法，可以在保证参数转换的同时获取流信息
     */
    @Override
    public String[] getParameterValues(final String name) {
        return params.get(name);

    }
}
