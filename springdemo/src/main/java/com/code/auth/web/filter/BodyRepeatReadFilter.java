package com.code.auth.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * http request类 input流重复读 处理
 * 流重复读的情况 比如对入参,出参进行打印
 * @author ccy
 */
@Slf4j
@Component
@WebFilter(urlPatterns = "/*")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BodyRepeatReadFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("BodyRepeatReadFilter.doFilter()");
        // 排除文件上传请求
        if (servletRequest.getContentType() != null && servletRequest.getContentType().contains("multipart/form-data")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (servletRequest instanceof HttpServletRequest) {
                ServletRequest requestWrapper = new BodyRepeatReadHttpServletRequestWrapper((HttpServletRequest) servletRequest);
                filterChain.doFilter(requestWrapper, servletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
