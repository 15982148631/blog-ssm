package com.wyf.blog.ssm.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author wyf
 * @ClassName Request
 * @Description //TODO
 * @Date 2021/3/3 16:49
 * @Version 1.0.0
 */
@Data
public class Request implements Serializable {

    private String username;

    private String password;

    private String remember;
}
