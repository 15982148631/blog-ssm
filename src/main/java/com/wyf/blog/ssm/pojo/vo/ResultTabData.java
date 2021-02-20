package com.wyf.blog.ssm.pojo.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 类名称：  ResultTabData
 * 功能描述: 将后台数据转换成指定表格数据格式
 * 创建者：  xiaowang
 * 创建时间：2019/3/20
 */

public class ResultTabData implements Serializable{
    //状态
    private  Integer code;

    //错误消息
    private String msg;

    //页数
    private long page;

    //单页数量
    private Integer rows;

    //总条数
    private Integer total;

    //返回数据
    private List<?> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }
    public void setData(List<?> data) {
        this.data = data;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
