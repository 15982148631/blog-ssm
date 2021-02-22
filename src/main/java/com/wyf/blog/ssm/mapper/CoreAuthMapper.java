package com.wyf.blog.ssm.mapper;

import com.wyf.blog.ssm.pojo.domain.CoreAuth;
import com.wyf.blog.tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface CoreAuthMapper extends MyMapper<CoreAuth> {


    List<String> getUserAuth(Long id);
}