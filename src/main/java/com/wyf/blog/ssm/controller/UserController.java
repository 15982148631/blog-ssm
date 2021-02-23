package com.wyf.blog.ssm.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author wyf
 * @ClassName UserController
 * @Description //TODO
 * @Date 2021/2/22 9:56
 * @Version 1.0.0
 */

@RequestMapping("/user")
@Controller
public class UserController {

    @RequiresPermissions("113")
    @ResponseBody
    @RequestMapping("/show")
    public String showUser() {
        return "这是学生信息";
    }
}