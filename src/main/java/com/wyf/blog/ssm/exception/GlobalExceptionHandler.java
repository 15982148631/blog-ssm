package com.wyf.blog.ssm.exception;

import com.wyf.blog.ssm.pojo.vo.Response;
import com.wyf.blog.ssm.pojo.vo.ResultData;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.management.ServiceNotFoundException;
import javax.security.auth.login.LoginException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Set;

/**
 * @ClassName GlobalExceptionHandler
 * @Description  全局异常处理器-拦截器
 * @Date 2020/10/28 11:53
 * @Version 1.0.0
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
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
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultData handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        String msg = "缺少请求参数！";
        log.error(msg, e);
        return new ResultData().failure(msg);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultData handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String msg = e.getMessage();
        log.error("参数解析失败：", e);
        return new ResultData().failure(msg);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultData handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = handleBindingResult(e.getBindingResult());
        log.error("方法参数无效: ", e);
        return new ResultData().failure(msg);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResultData handleBindException(BindException e) {
        String msg = handleBindingResult(e.getBindingResult());
        log.error("参数绑定失败:", e);
        return new ResultData().failure(msg);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultData handleServiceException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String msg = violations.iterator().next().getMessage();
        log.error("参数验证失败:", e);
        return new ResultData().failure(msg);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResultData handleValidationException(ValidationException e) {
        String msg = e.getMessage();
        log.error("参数验证失败：", e);
        return new ResultData().failure(msg);
    }

    /**
     * 401 - Unauthorized
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(LoginException.class)
    public ResultData handleLoginException(LoginException e) {
        String msg = e.getMessage();
        log.error("登录异常：", e);
        return new ResultData().failure(msg);
    }

    /**
     * 403 - Unauthorized
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnauthorizedException.class)
    public ResultData handleLoginException(UnauthorizedException e) {
        String msg = e.getMessage();
        log.error("用户无权限：", e);
        return new ResultData().failure(HttpStatus.FORBIDDEN.value(), "用户无权限!", null);
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultData handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String msg = "不支持当前请求方法！";
        log.error(msg, e);
        return new ResultData().failure(msg);
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResultData handleHttpMediaTypeNotSupportedException(Exception e) {
        String msg = "不支持当前媒体类型！";
        log.error(msg, e);
        return new ResultData().failure(msg);
    }

    /**
     * 422 - UNPROCESSABLE_ENTITY
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResultData handleMaxUploadSizeExceededException(Exception e) {
        String msg = "所上传文件大小超过最大限制，上传失败！";
        log.error(msg, e);
        return new ResultData().failure(msg);
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceNotFoundException.class)
    public ResultData handleServiceException(ServiceNotFoundException e) {
        String msg = "服务内部异常：" + e.getMessage();
        log.error(msg, e);
        return new ResultData().failure(msg);
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResultData handleException(Exception e) {
        String msg = "服务内部异常！" + e.getMessage();
        log.error(msg, e);
        return new ResultData().failure(msg);
    }


    /**
     * 处理参数绑定异常，并拼接出错的参数异常信息。
     * <p>
     * 创建人：leigq <br>
     * 创建时间：2017年10月16日 下午9:09:22 <br>
     * <p>
     * 修改人： <br>
     * 修改时间： <br>
     * 修改备注： <br>
     * </p>
     *
     * @param result
     */
    private String handleBindingResult(BindingResult result) {
        if (result.hasErrors()) {
            final List<FieldError> fieldErrors = result.getFieldErrors();
            return fieldErrors.iterator().next().getDefaultMessage();
        }
        return null;
    }

}