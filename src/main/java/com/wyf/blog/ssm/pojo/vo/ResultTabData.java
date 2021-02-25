package com.wyf.blog.ssm.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 类名称：  ResultTabData
 * 功能描述: 将后台数据转换成指定表格数据格式
 * 创建者：  xiaowang
 * 创建时间：2019/3/20
 */
@Data
public class ResultTabData implements Serializable{


    private static final long serialVersionUID = 1558728248887638889L;

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


}
