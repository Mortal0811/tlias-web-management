package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * ClassName: LoginCheckFilter
 * Package: com.itheima.filter
 * Description:
 *
 * @Author dhf
 * @Create 2023/7/22 2:05
 * @Version 17.0.6
 */
@Slf4j
// @WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp =(HttpServletResponse) servletResponse;

        //1获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求的url:{}",url);
        //2判断请求url是否包含login,如果包含，放行
        if(url.contains("login")){
            log.info("登录操作，放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //3获取请求头中的令牌
        String jwt = req.getHeader("token");
        //4判断令牌是否存在，登录失败返回错误结果
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换对象 对象->json  @RestController可以自动转换
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        //5解析令牌，解析失败返回错误结果
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            Result error = Result.error("NOT_LOGIN");
            //手动转换对象 对象->json  @RestController可以自动转换
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        //6放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
