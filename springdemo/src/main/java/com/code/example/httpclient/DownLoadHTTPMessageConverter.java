package com.code.example.httpclient;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.util.StreamUtils;

import java.io.IOException;

/**
 * canRead ：判断该转换器是否能将请求内容转换成Java对象
 * canWrite ：判断该转换器是否可以将Java对象转换成返回内容
 * getSupportedMediaTypes ：获得该转换器支持的MediaType类型
 * read ：读取请求内容并转换成Java对象
 * write ：将Java对象转换后写入返回内容
 * <p>
 * 暂时是无意义的类
 *
 * @author ccy
 */
public class DownLoadHTTPMessageConverter extends AbstractHttpMessageConverter<InMemoryMultipartFile> {


    public DownLoadHTTPMessageConverter() {
        super(new MediaType("application", "octet-stream"), MediaType.ALL);
    }

    /**
     * java 对像->http
     * 只读
     */
    @Override
    public boolean canWrite(Class<?> clazz, @Nullable MediaType mediaType) {
        return false;
    }

    /**
     * public boolean canRead(Class<?> clazz, @Nullable MediaType mediaType) {
     * return supports(clazz) && canRead(mediaType);
     * }
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        return InMemoryMultipartFile.class == clazz;
    }

    /**
     * http->java 对像
     */
    @Override
    protected InMemoryMultipartFile readInternal(Class<? extends InMemoryMultipartFile> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        byte[] bytes = StreamUtils.copyToByteArray(inputMessage.getBody());
        return new InMemoryMultipartFile("file", bytes);
    }

    /**
     * java 对像->http
     */
    @Override
    protected void writeInternal(InMemoryMultipartFile multipartFile, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
