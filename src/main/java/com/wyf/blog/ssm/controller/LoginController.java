package com.wyf.blog.ssm.controller;

import com.wyf.blog.ssm.exception.ErrorEnum;
import com.wyf.blog.ssm.pojo.domain.CoreAdmin;
import com.wyf.blog.ssm.pojo.vo.CacheUser;
import com.wyf.blog.ssm.pojo.vo.ResultData;
import com.wyf.blog.ssm.service.api.LoginService;
import com.wyf.blog.ssm.utils.JsonUtils;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


/**
 * @Author wyf
 * @ClassName LoginController
 * @Description 登录类
 * @Date 2021/2/22 9:37
 * @Version 1.0.0
 */
@Api(tags = "登录管理")
@Controller
@CrossOrigin//处理跨域
public class LoginController {

    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String defaultLogin() {
        return "首页";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
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
        CacheUser cacheUser;

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
        } catch (IncorrectCredentialsException ice) {
            ResultData result = new  ResultData(-1, "密码不正确",null);
            return JsonUtils.objectToJson(result);
        } catch (LockedAccountException lae) {
            ResultData result = new ResultData(-1, "账户已锁定", null);
            return JsonUtils.objectToJson(result);
        } catch (ExcessiveAttemptsException eae) {
            ResultData result = new ResultData(-1, "用户名或密码错误次数过多", null);
            return JsonUtils.objectToJson(result);
        }
        catch (AuthenticationException ae) {
            ResultData result = new  ResultData(-1, "用户名或密码不正确！",null);
            return JsonUtils.objectToJson(result);
        }

        if (subject.isAuthenticated()) {
            CoreAdmin user = (CoreAdmin) subject.getPrincipals().getPrimaryPrincipal();
            cacheUser = CacheUser.builder().token(subject.getSession().getId().toString()).build();
            BeanUtils.copyProperties(user, cacheUser);
            ResultData result = new ResultData(200, "登录成功", cacheUser);
            return JsonUtils.objectToJson(result);
        } else {
            token.clear();
            ResultData result = new ResultData(-1, "登录失败", null);
            return JsonUtils.objectToJson(result);
        }

    }


    /**
     * description: 登出
     * create time: 2019/6/28 17:37
     */
    @GetMapping("/logout")
    public String logOut() {
        loginService.logout();
        ResultData data = new ResultData(ErrorEnum.SUCCESS.getErrorCode(), ErrorEnum.SUCCESS.getErrorMsg(), new Object());
        return JsonUtils.objectToJson(data);
    }


    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/7/3 14:53
     * @return
     */
    @RequestMapping("/unLogin")
    public String unAuth() {
        ResultData data = new ResultData(ErrorEnum.NO_AUTH.getErrorCode(), ErrorEnum.NO_AUTH.getErrorMsg(), new Object());
        return JsonUtils.objectToJson(data);
    }

    /**
     * 未授权，无权限，此处返回未授权状态信息由前端控制跳转页面
     * create by: leigq
     * create time: 2019/7/3 14:53
     * @return
     */
    @RequestMapping("/unauthorized")
    public String unauthorized() {
        ResultData data = new ResultData(ErrorEnum.NO_PERMISSION.getErrorCode(),ErrorEnum.NO_PERMISSION.getErrorMsg(), new Object());
        return JsonUtils.objectToJson(data);
    }

}
