package com.wyf.blog.ssm.mapper;

import com.wyf.blog.ssm.pojo.domain.CorePost;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface CorePostMapper extends Mapper<CorePost> {
}