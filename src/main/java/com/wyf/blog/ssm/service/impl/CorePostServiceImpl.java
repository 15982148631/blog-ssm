package com.wyf.blog.ssm.service.impl;

import com.github.pagehelper.PageInfo;
import com.wyf.blog.ssm.pojo.domain.CorePost;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wyf.blog.ssm.mapper.CorePostMapper;
import com.wyf.blog.ssm.service.api.CorePostService;
import org.springframework.transaction.annotation.Transactional;


@Transactional//事务
@Service
public class CorePostServiceImpl implements CorePostService{

    @Resource
    private CorePostMapper corePostMapper;

    @Override
    public CorePost getCorePostByPrimerkey(long key) {

        return corePostMapper.selectByPrimaryKey(key);
    }

    @Override
    public PageInfo page(int i, int i1, CorePost post) {
        return new PageInfo(corePostMapper.selectAll());
    }

    @Override
    public boolean deletePostById(long id) {
        int i = corePostMapper.deleteByPrimaryKey(id);
        if (0 == i){
            return false;
        }
        return true;
    }

    @Override
    public int addPost(CorePost post) {
        return corePostMapper.insert(post);
    }

    @Override
    public int updatePost(CorePost post) {
        return corePostMapper.updateByPrimaryKey(post);
    }
}
