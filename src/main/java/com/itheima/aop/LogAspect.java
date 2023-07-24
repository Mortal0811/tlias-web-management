package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * ClassName: LogAspect
 * Package: com.itheima.aop
 * Description:
 *
 * @Author dhf
 * @Create 2023/7/22 22:10
 * @Version 17.0.6
 */
@Slf4j
@Component
@Aspect//切面类
public class LogAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Around("@annotation(com.itheima.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //操作人ID-当前登录员工ID
        //获取请求头的jwt令牌并解析
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");

        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名
        String className = joinPoint.getTarget().getClass().getName();
        //操作方法名
        String methodName = joinPoint.getSignature().getName();


        long begin = System.currentTimeMillis();
        //调用原始目标方法运行
        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        //方法返回值
        String returnValue = JSONObject.toJSONString(result);
        //操作耗时
        Long costTime = end - begin;
        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        //记录操作日志
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);
        log.info("AOP操作日志：{}",operateLog);
        return result;
    }
}
