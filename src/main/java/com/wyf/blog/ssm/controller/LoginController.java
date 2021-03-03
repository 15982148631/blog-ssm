package com.wyf.blog.ssm.controller;

import com.wyf.blog.ssm.config.ThreadPoolConfig;
import com.wyf.blog.ssm.exception.ErrorEnum;
import com.wyf.blog.ssm.pojo.domain.CoreAdmin;
import com.wyf.blog.ssm.pojo.vo.CacheUser;
import com.wyf.blog.ssm.pojo.vo.Request;
import com.wyf.blog.ssm.pojo.vo.ResultData;
import com.wyf.blog.ssm.service.api.LoginService;
import com.wyf.blog.ssm.utils.JsonUtils;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;


    /***
     * @Author wyf
     * @Description 登录
     * @Date  2021/3/2 16:29
     * @Param [username, password, remember]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultData login(@RequestBody Request req) {
        String username = req.getUsername();
        String password = req.getPassword();
        String remember = req.getRemember();
        logger.info("时间戳:" + System.currentTimeMillis() + "-->登录用户：" + username);
        if (StringUtils.isEmpty(username)) {
            return new ResultData(-1, "用户名未填写", null);
        }


        if (StringUtils.isEmpty(password)) {
            return new ResultData(-1, "密码未填写", null);
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
            return new ResultData(-1, "未知账户", null);
        } catch (IncorrectCredentialsException ice) {
            return new  ResultData(-1, "密码不正确",null);
        } catch (LockedAccountException lae) {
            return new ResultData(-1, "账户已锁定", null);
        } catch (ExcessiveAttemptsException eae) {
            return new ResultData(-1, "用户名或密码错误次数过多", null);
        } catch (AuthenticationException ae) {
            return new  ResultData(-1, "用户名或密码不正确！",null);
        }

        if (subject.isAuthenticated()) {
            CoreAdmin user = (CoreAdmin) subject.getPrincipals().getPrimaryPrincipal();
            cacheUser = CacheUser.builder().token(subject.getSession().getId().toString()).build();
            BeanUtils.copyProperties(user, cacheUser);
            return new ResultData(200, "登录成功", cacheUser);
        } else {
            token.clear();
            return new ResultData(-1, "登录失败", null);
        }

    }


    /**
     * description: 登出
     * create time: 2019/6/28 17:37
     */
    @GetMapping("/logout")
    public ResultData logOut() {
        //ThreadPoolConfig.executor.execute(()-> System.out.println("test"));

        loginService.logout();
        return new ResultData(ErrorEnum.SUCCESS.getErrorCode(), ErrorEnum.SUCCESS.getErrorMsg(), new Object());
    }


    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * <br/>
     * create by: wyf
     * <br/>
     * create time: 2019/7/3 14:53
     * @return
     */
    @RequestMapping("/unLogin")
    @ResponseBody
    public ResultData unLogin() {

        return  new ResultData(ErrorEnum.NO_AUTH.getErrorCode(), ErrorEnum.NO_AUTH.getErrorMsg(), new Object());
    }

    /**
     * 未授权，无权限，此处返回未授权状态信息由前端控制跳转页面
     * create by: wyf
     * create time: 2019/7/3 14:53
     * @return
     */
    @RequestMapping("/unauthorized")
    @ResponseBody
    public ResultData unauthorized() {

        return new ResultData(ErrorEnum.NO_PERMISSION.getErrorCode(),ErrorEnum.NO_PERMISSION.getErrorMsg(), new Object());
    }

}
