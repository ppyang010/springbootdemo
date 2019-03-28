package code.config;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;

import java.io.IOException;

/**
 * canRead ：判断该转换器是否能将请求内容转换成Java对象
 * canWrite ：判断该转换器是否可以将Java对象转换成返回内容
 * getSupportedMediaTypes ：获得该转换器支持的MediaType类型
 * read ：读取请求内容并转换成Java对象
 * write ：将Java对象转换后写入返回内容
 *
 * 暂时是无意义的类
 *
 * @author ccy
 */
public class DownLoadHTTPMessageConverter extends AbstractHttpMessageConverter<ResponseEntity> {


    public DownLoadHTTPMessageConverter() {
        super(new MediaType("application", "octet-stream"), MediaType.ALL);
    }

    /**
     * java 对像->http
     */
    @Override
    public boolean canWrite(Class<?> clazz, @Nullable MediaType mediaType) {
        return false;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return ResponseEntity.class == clazz;
    }

    /**
     * http->java 对像
     */
    @Override
    protected ResponseEntity readInternal(Class<? extends ResponseEntity> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    /**
     * java 对像->http
     */
    @Override
    protected void writeInternal(ResponseEntity responseEntity, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
