package com.wyf.blog.ssm.service.api;

import com.github.pagehelper.PageInfo;
import com.wyf.blog.ssm.pojo.domain.CoreAdmin;


public interface CoreAdminService{

    /***
     * @Author wyf
     * @Description 获取指定主键管理员数据
     * @Date  2020/11/13 15:47
     * @Param [key]
     * @return com.wyf.project.ssm.domain.CoreAdmin
     **/
    CoreAdmin getAdminByPrimerkey(long key);

    /***
     * @Author wyf
     * @Description 获取管理员列表
     * @Date  2020/11/13 16:50
     * @Param [i, i1, tbUser]
     * @return com.github.pagehelper.PageInfo
     **/
    PageInfo page(int i, int i1, CoreAdmin tbUser);

    /***
     * @Author wyf
     * @Description 删除指定管理员
     * @Date  2020/11/13 16:50
     * @Param [id]
     * @return boolean
     **/
    boolean deleteUserById(long id);

    /***
     * @Author wyf
     * @Description 新增管理员
     * @Date  2020/11/13 16:51
     * @Param [user]
     * @return int
     **/
    int addUser(CoreAdmin user);

    /***
     * @Author wyf
     * @Description 修改管理员
     * @Date  2020/11/13 17:04
     * @Param [user]
     * @return int
     **/
    int updateUser(CoreAdmin user);


    @Deprecated//过时使用注解
    int getAdmin(long id);


}
