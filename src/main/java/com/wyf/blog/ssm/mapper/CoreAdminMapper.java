package com.wyf.blog.ssm.mapper;

import com.wyf.blog.ssm.pojo.domain.CoreAdmin;
import com.wyf.blog.tk.mybatis.mapper.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CoreAdminMapper extends MyMapper<CoreAdmin> {

    List<CoreAdmin> selectList(int i, int i1);

    List<Long> selectAllOfIds();
}