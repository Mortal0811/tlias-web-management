package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * ClassName: DemoFilter
 * Package: com.itheima.filter
 * Description:
 *
 * @Author dhf
 * @Create 2023/7/22 1:27
 * @Version 17.0.6
 */
//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override//初始化方法只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("init 初始化方法执行了");
    }

    @Override//拦截请求 调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到了请求...放行前的逻辑");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("拦截到了请求...放行后的逻辑");
    }

    @Override//销毁方法 只调用一次
    public void destroy() {
        Filter.super.destroy();
        System.out.println("destroy 销毁方法执行了");
    }
}
