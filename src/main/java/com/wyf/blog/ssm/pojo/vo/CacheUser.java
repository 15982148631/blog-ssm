package com.wyf.blog.ssm.pojo.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author wyf
 * @ClassName CacheUser
 * @Description 缓存用户信息
 * @Date 2021/2/23 9:27
 * @Version 1.0.0
 */
@Data
@Builder
public class CacheUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    private String name;

    private Integer state;

    private String username;

    private String nickname;

    private String token;
}
