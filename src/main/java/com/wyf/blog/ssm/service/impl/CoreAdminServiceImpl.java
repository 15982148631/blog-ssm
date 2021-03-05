package com.wyf.blog.ssm.service.impl;

import com.github.pagehelper.PageInfo;
import com.wyf.blog.ssm.pojo.dto.CoreAdminDto;
import com.wyf.blog.ssm.pojo.vo.ResultTabData;
import com.wyf.blog.ssm.utils.JsonUtils;
import com.wyf.blog.ssm.utils.enums.RedisStaticStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wyf.blog.ssm.mapper.CoreAdminMapper;
import com.wyf.blog.ssm.pojo.domain.CoreAdmin;
import com.wyf.blog.ssm.service.api.CoreAdminService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/***
 * @Author wyf
 * @Description 用户管理
 * @Date  2021/2/10 15:05
 * @Param
 * @return 
 **/
@Transactional//事务
@Service
public class CoreAdminServiceImpl implements CoreAdminService{


    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private CoreAdminMapper coreAdminMapper;


    @Override
    public CoreAdmin getAdminByPrimerkey(long key){
        String prikey = RedisStaticStr.ADMIN_USER_KEY.getKey() + key;
        Object obj = redisTemplate.opsForValue().get(prikey);
        if (obj == null){
            CoreAdmin admin = coreAdminMapper.selectByPrimaryKey(key);
            redisTemplate.opsForValue().set(prikey, JsonUtils.objectToJson(admin),RedisStaticStr.ADMIN_USER_KEY.getTimeNum(), TimeUnit.SECONDS);
            return admin;
        }
         redisTemplate.expire(prikey, RedisStaticStr.ADMIN_USER_KEY.getTimeNum(), TimeUnit.SECONDS);
        return JsonUtils.jsonToPojo(obj.toString(),CoreAdmin.class);
    }

    @Override
    public CoreAdmin getAdminByExample(CoreAdmin admin){
        Example example = new Example(CoreAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("nickname",admin.getNickname());
        List<CoreAdmin> coreAdmins = coreAdminMapper.selectByExample(example);
        if (coreAdmins == null || coreAdmins.size()!=1){
            return null;
        }
        return coreAdmins.get(0);
    }


    @Override
    public PageInfo page(int pageNum, int pageSize, CoreAdmin tbUser) {

        PageInfo pageInfo=new PageInfo(coreAdminMapper.selectList((pageNum - 1) * pageSize, pageNum * pageSize));

        int count = coreAdminMapper.selectCount(tbUser);
        pageInfo.setSize(count);

        return pageInfo;
    }

    @Override
    public boolean deleteUserById(long id) {
        int i = coreAdminMapper.deleteByPrimaryKey(id);
        if (0 == i){
            return false;
        }
        return true;
    }

    @Override
    public int addUser(CoreAdmin user) {
        return coreAdminMapper.insert(user);
    }

    @Override
    public int updateUser(CoreAdmin user) {
        return coreAdminMapper.updateByPrimaryKey(user);
    }

    @Override
    public int getAdminCount(CoreAdmin admin) {

        return coreAdminMapper.selectCount(admin);
    }


    @Override
    public int getAdmin(long id) {
        return 0;
    }
}
