package com.wyf.blog.ssm.exception;

import com.wyf.blog.ssm.pojo.vo.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalExceptionHandler
 * @Description  全局异常处理器-拦截器
 * @Date 2020/10/28 11:53
 * @Version 1.0.0
 */

//@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);



    /**
     * 处理自定义异常
     *
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResultData bizExceptionHandler(BusinessException e) {
        log.error(e.getMessage(), e);
        return ResultData.defineError(e);
    }

    /**
     *            处理其他异常
     *
     */
    @ExceptionHandler(value = Exception.class)
    public ResultData exceptionHandler( Exception e) {
        log.error(e.getMessage(), e);
        return ResultData.otherError(ErrorEnum.INTERNAL_SERVER_ERROR);

    }

}