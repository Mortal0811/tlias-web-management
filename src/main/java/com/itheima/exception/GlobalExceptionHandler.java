package com.itheima.exception;

import com.itheima.pojo.Result;
import org.apache.ibatis.executor.ResultExtractor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName: GlobalExceptionHandler
 * Package: com.itheima.exception
 * Description:全局异常处理器
 *
 * @Author dhf
 * @Create 2023/7/22 11:09
 * @Version 17.0.6
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error("对不起操作失败，请联系管理员");
    }
}
