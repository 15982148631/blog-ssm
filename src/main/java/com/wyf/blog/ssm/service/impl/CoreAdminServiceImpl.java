package com.wyf.blog.ssm.service.impl;

import com.github.pagehelper.PageInfo;
import com.wyf.blog.ssm.utils.JsonUtils;
import com.wyf.blog.ssm.utils.enums.RedisStaticStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wyf.blog.ssm.mapper.CoreAdminMapper;
import com.wyf.blog.ssm.pojo.domain.CoreAdmin;
import com.wyf.blog.ssm.service.api.CoreAdminService;
import org.springframework.transaction.annotation.Transactional;

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


    /**需要调整成此redis序列化方式，否则会出现乱码key*/
    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

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
    public PageInfo page(int i, int i1, CoreAdmin tbUser) {
        PageInfo pageInfo=new PageInfo(coreAdminMapper.selectAll());
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
    public int getAdmin(long id) {
        return 0;
    }
}
