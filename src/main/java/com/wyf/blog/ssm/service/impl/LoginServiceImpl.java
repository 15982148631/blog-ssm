package com.wyf.blog.ssm.service.impl;

import com.wyf.blog.ssm.mapper.CoreAdminMapper;
import com.wyf.blog.ssm.mapper.CoreAuthMapper;
import com.wyf.blog.ssm.mapper.CoreRoleMapper;
import com.wyf.blog.ssm.pojo.domain.CoreAdmin;
import com.wyf.blog.ssm.pojo.vo.CacheUser;
import com.wyf.blog.ssm.service.api.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wyf
 * @ClassName LoginServiceImpl
 * @Description //TODO
 * @Date 2021/2/22 11:18
 * @Version 1.0.0
 */
@Transactional//事务
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private CoreRoleMapper coreRoleMapper;

    @Autowired
    private CoreAuthMapper coreAuthMapper;

    @Autowired
    private CoreAdminMapper coreAdminMapper;


    @Override
    public List<String> getUserAuth(String username) {
        Example example = new Example(CoreAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("nickname",username);
        List<CoreAdmin> list = coreAdminMapper.selectByExample(example);

        if (list== null || list.size() !=1){
            return new ArrayList<>();
        }

        return coreAuthMapper.getUserAuth(list.get(0).getId());
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            subject.logout();
        }
    }

    @Override
    public CacheUser getUserSession() {
        Subject subject = SecurityUtils.getSubject();
        CoreAdmin user = (CoreAdmin) subject.getPrincipals().getPrimaryPrincipal();
        CacheUser cacheUser = CacheUser.builder().token(subject.getSession().getId().toString()).build();
        BeanUtils.copyProperties(user, cacheUser);
        return cacheUser;
    }
}
