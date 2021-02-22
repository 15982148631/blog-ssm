package com.wyf.blog.ssm.service.api;

import java.util.List;

/**
 * @Author wyf
 * @ClassName LoginService
 * @Description 用户登录模块
 * @Date 2021/2/22 11:17
 * @Version 1.0.0
 */
public interface LoginService {

    /***
     * @Author wyf
     * @Description 获取指定用户权限列表
     * @Date  2021/2/22 11:48
     * @Param [username]
     * @return java.util.List<java.lang.String>
     **/
    List<String> getUserAuth(String username);
}
