package com.wyf.blog.ssm.pojo.vo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wyf.blog.ssm.exception.BusinessException;
import com.wyf.blog.ssm.exception.ErrorEnum;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义返回页面响应结构
 */
public class ResultData implements Serializable{


    /**
     * @Fields field:表单提交结构
     * @date 2018年7月10日
     */
    private static final long serialVersionUID = -2496672594279324524L;

    /* 定义jackson对象*/
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /*是否成功*/
    private Boolean success;

    /**响应业务状态*/
    private Integer status;

    /*响应消息*/
    private String msg;

    /**响应中的数据 */
    private Object data;

    public static ResultData build(Integer status, String msg, Object data) {
        return new ResultData(status, msg, data);
    }

    public static ResultData ok(Object data) {
        return new ResultData(data);
    }

    public static ResultData ok() {
        return new ResultData(null);
    }

    public ResultData() {

    }

    public static ResultData build(Integer status, String msg) {
        return new ResultData(status, msg, null);
    }

    public ResultData(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultData(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
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


    /**
     * 将json结果集转化为PacsResultUtil对象
     * @param jsonData json数据
     * @param clazz PacsResultUtil中的object类型
     * @return
     */
    public static ResultData formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ResultData.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     * @param json
     * @return
     */
    public static ResultData format(String json) {
        try {
            return MAPPER.readValue(json, ResultData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static ResultData formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
}
