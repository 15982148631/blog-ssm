package com.wyf.blog.ssm.config;

import com.wyf.blog.ssm.pojo.domain.CoreAdmin;
import com.wyf.blog.ssm.service.api.CoreAdminService;
import com.wyf.blog.ssm.service.api.LoginService;
import com.wyf.blog.ssm.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author wyf
 * @ClassName CustomRealm
 * @Description shiro作用域
 * @Date 2021/2/22 9:34
 * @Version 1.0.0
 */
public class MyCustomRealm extends AuthorizingRealm {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;

    @Autowired
    private CoreAdminService coreAdminService;


    /***
     * @Author wyf
     * @Description 获取权限组-鉴权
     * @Date  2021/2/22 10:01
     * @Param [principalCollection]
     * @return org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("开始执行授权操作.......");
        CoreAdmin admin = (CoreAdmin) SecurityUtils.getSubject().getPrincipal();

        List<String> userAuth = loginService.getUserAuth(admin.getNickname());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> stringSet = new HashSet<>();
        for (String auth:userAuth) {
            stringSet.add(auth);
        }
        info.setStringPermissions(stringSet);
        logger.info("授权操作完成.......");
        return info;
    }

    /**
     * 获取即将需要认证的信息-校验
     * 对登录用户进行权限识别
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("-------身份认证方法--------");
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());

        if (userName == null) {
            throw new AccountException("用户名不正确！");
        } else if (!userPwd.equals(userPwd)) {
            throw new AccountException("密码不正确！");
        }

        //根据用户名从数据库获取密码
        CoreAdmin admin = new CoreAdmin();
        admin.setNickname(userName);
        admin.setPassword(ShiroUtils.MD5Pwd(userName,userPwd));
        CoreAdmin user = coreAdminService.getAdminByExample(admin);

        if (user == null) {
            // 这里返回后会报出对应异常
            return null;
        } else {
            // 这里验证authenticationToken和simpleAuthenticationInfo的信息
            //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配

            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
                    ByteSource.Util.bytes(userName + "salt"), getName());
            return simpleAuthenticationInfo;
        }
    }

}
