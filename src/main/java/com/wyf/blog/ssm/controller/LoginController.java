package com.wyf.blog.ssm.controller;

import com.wyf.blog.ssm.exception.ErrorEnum;
import com.wyf.blog.ssm.pojo.vo.ResultData;
import com.wyf.blog.ssm.utils.JsonUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import static com.wyf.blog.ssm.utils.ShiroUtils.MD5Pwd;


/**
 * @Author wyf
 * @ClassName LoginController
 * @Description 登录类
 * @Date 2021/2/22 9:37
 * @Version 1.0.0
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String defaultLogin() {
        return "首页";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    @ResponseBody
    public String defaultError() {

        return "错误页";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("remember") String remember) {

        if (StringUtils.isEmpty(username)) {
            ResultData result = new ResultData(-1, "用户名未填写", null);
            return JsonUtils.objectToJson(result);
        }


        if (StringUtils.isEmpty(password)) {
            ResultData result = new ResultData(-1, "密码未填写", null);
            return JsonUtils.objectToJson(result);
        }


        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();

        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        if (remember != null && "1".equals(remember)) {
            token.setRememberMe(true);
        }

        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            ResultData result = new ResultData(-1, "未知账户", null);
            return JsonUtils.objectToJson(result);
//        } catch (IncorrectCredentialsException ice) {
//            ResultData result = new  ResultData(-1, "密码不正确",null);
//            return JsonUtils.objectToJson(result);
        } catch (LockedAccountException lae) {
            ResultData result = new ResultData(-1, "账户已锁定", null);
            return JsonUtils.objectToJson(result);
        } catch (ExcessiveAttemptsException eae) {
            ResultData result = new ResultData(-1, "用户名或密码错误次数过多", null);
            return JsonUtils.objectToJson(result);
        }
//        catch (AuthenticationException ae) {
//            ResultData result = new  ResultData(-1, "用户名或密码不正确！",null);
//            return JsonUtils.objectToJson(result);
//        }

        if (subject.isAuthenticated()) {
            ResultData result = new ResultData(200, "登录成功", null);
            return JsonUtils.objectToJson(result);
        } else {
            token.clear();
            ResultData result = new ResultData(-1, "登录失败", null);
            return JsonUtils.objectToJson(result);
        }

    }
}
