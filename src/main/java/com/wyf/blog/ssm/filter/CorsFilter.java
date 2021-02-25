package com.wyf.blog.ssm.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author wyf
 * @ClassName CorsFilter
 * @Description 通过filter方式处理集成shiro后跨域处理失效的问题
 * @Date 2021/2/22 9:59
 * @Version 1.0.0
 */
@Component
@Slf4j
public class CorsFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig){
        log.info("CorsFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, If-Modified-Since, x-token");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Credentials", "true");

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("CorsFilter destroy");
    }
}
