package com.wyf.blog.ssm.service.api;

import com.wyf.blog.ssm.pojo.vo.CacheUser;

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


    /***
     * @Author wyf
     * @Description 退出
     * @Date  2021/2/23 14:12
     * @Param []
     * @return void
     **/
    void logout();

    /**
     * @Author wyf
     * @Description 获取已登录用户信息
     * @Date  2021/3/4 15:18
     * @Param []
     * @return com.wyf.blog.ssm.pojo.vo.CacheUser
     **/
    CacheUser getUserSession();
}
