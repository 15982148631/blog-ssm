package com.wyf.blog.ssm.pojo.vo;

import com.wyf.blog.ssm.exception.BusinessException;
import com.wyf.blog.ssm.exception.ErrorEnum;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 自定义返回页面响应结构
 */
@Data
public class ResultData implements Serializable{

    /**
     * @Fields field:表单提交结构
     * @date 2018年7月10日
     */
    private static final long serialVersionUID = -2496672594279324524L;

    /**
     * 默认失败响应码
     */
    private static final Integer DEAFAULT_FAILURE_CODE = HttpStatus.INTERNAL_SERVER_ERROR.value();

    /**是否成功*/
    private Boolean success;

    /**响应业务状态*/
    private Integer status;

    /**响应消息*/
    private String msg;

    /**响应中的数据 */
    private Object data;


    public ResultData() {

    }


    public ResultData(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResultData(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /***
     * @Author wyf
     * @Description 成功返回
     * @Date  2021/2/25 10:15
     * @Param [data]
     * @return
     **/
    public ResultData(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public static ResultData failure(String msg) {
        ResultData result = new ResultData();
        result.setMsg(msg);
        result.status = DEAFAULT_FAILURE_CODE;
        return result;
    }

    public static ResultData failure(int code,String msg ,Object data) {
        ResultData result = new ResultData();
        result.setMsg(msg);
        result.setStatus(code);
        result.setData(data);
        return result;
    }

    public static ResultData ok(Object data) {
        return new ResultData(data);
    }

    public static ResultData ok() {
        return new ResultData(null);
    }


    public static ResultData build(Integer status, String msg) {
        return new ResultData(status, msg, null);
    }


    public Boolean isOK() {
        return this.status == 200;
    }


    //自定义异常返回的结果
    public static ResultData defineError(BusinessException de){
        ResultData result = new ResultData();
        result.setSuccess(false);
        result.setStatus(de.getErrorCode());
        result.setMsg(de.getErrorMsg());
        result.setData(null);
        return result;
    }


    //其他异常处理方法返回的结果
    public static ResultData otherError(ErrorEnum errorEnum){
        ResultData result = new ResultData();
        result.setMsg(errorEnum.getErrorMsg());
        result.setStatus(errorEnum.getErrorCode());
        result.setSuccess(false);
        result.setData(null);
        return result;
    }

}
